package controller.espaco;

import controller.app.AplicacaoController;
import model.dao.UnidadeFisicaDAO;
import model.entities.predios.UnidadeFisica;
import model.entities.locais.Local;
import model.entities.agenda.Reserva;
import model.entities.agenda.Horario;
import model.exceptions.ReservaException;
import model.exceptions.LocaisException;
import model.exceptions.UsuarioException;
import model.services.ReservaService;
import view.TelaNovaReserva;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class NovaReservaController {
    private TelaNovaReserva telaNovaReserva;
    private AplicacaoController controller;
    private ReservaService reservaService; //reserva

    public NovaReservaController(TelaNovaReserva telaNovaReserva, AplicacaoController controller){
        this.telaNovaReserva = telaNovaReserva;
        this.controller = controller;
        this.reservaService = new ReservaService();

        carregarUnidades();

        telaNovaReserva.adicionarVoltarBottonListener(e -> controller.mostrarTelaDashboard());
        telaNovaReserva.adicionarConfirmarBottonListener(e -> confirmar());
        telaNovaReserva.getComboUnidade().addActionListener(e -> carregarComboLocais());
        telaNovaReserva.getComboSala().addActionListener(e -> carregarHorariosDisponiveis());
    }
    
    private void carregarUnidades(){

        try 
        {
            List<UnidadeFisica> listaUnidadesFisicas = UnidadeFisicaDAO.listarTodas();
            List<String> listaUnidadeFisicaNome = new ArrayList<>();

            for(UnidadeFisica unidadeFisica : listaUnidadesFisicas){
                String nomeUnidade = unidadeFisica.getNome();
                listaUnidadeFisicaNome.add(nomeUnidade);
            }
            telaNovaReserva.atualizarComboUnidade(listaUnidadeFisicaNome);

        } catch (Exception e) {
            telaNovaReserva.mostrarMensagem("Erro ao carregar unidades: " + e.getMessage());
        }
    }
    
    private void carregarComboLocais(){

        String unidadeSelecionada = (String) telaNovaReserva.getComboUnidade().getSelectedItem();
        if (unidadeSelecionada != null) {
            try {
                carregarSalas(unidadeSelecionada);
            } catch (Exception e) {
                telaNovaReserva.mostrarMensagem("ERRO ao carregar os locais: " + e.getMessage());
            }
        }     
    }
    
    private void carregarSalas(String unidadeSelecionada){
        
    }

    private void carregarHorariosDisponiveis(){
        try {
            String salaSelecionada = (String) telaNovaReserva.getComboSala().getSelectedItem();
            if (salaSelecionada != null) {
                // Lógica para carregar horários disponíveis
                List<String> horariosDisponiveis = obterHorariosDisponiveis(salaSelecionada);
                telaNovaReserva.atualizarComboHorarios(horariosDisponiveis);
            }
        } catch (Exception e) {
            telaNovaReserva.mostrarMensagem("Erro ao carregar horários: " + e.getMessage());
        }
    }

    private void confirmar(){
         try {
            // Obter dados da tela
            String nome = telaNovaReserva.getNomeReserva();
            String descricao = telaNovaReserva.getDescricaoReserva();
            String unidade = (String) telaNovaReserva.getComboUnidade().getSelectedItem();
            String sala = (String) telaNovaReserva.getComboSala().getSelectedItem();
            String horarioStr = (String) telaNovaReserva.getComboHorarios().getSelectedItem();
            
            // Validar campos obrigatórios
            validarCamposObrigatorios(nome, unidade, sala, horarioStr);
            
            // Buscar o local
            Local local = buscarLocal(sala);
            
            // Criar horário
            Horario horario = criarHorario(horarioStr);
            
            // Criar reserva usando o service
            Reserva reserva = reservaService.criarReserva(
                nome, 
                descricao, 
                controller.getUsuario(), 
                local, 
                horario
            );
            
            telaNovaReserva.mostrarMensagem("Reserva criada com sucesso!");
            controller.atualizarTabelaReservasUsuario();
            controller.mostrarTelaDashboard();
            
        } catch (ReservaException.HorarioIndisponivelException e) {
            telaNovaReserva.mostrarMensagem("Horário não disponível: " + e.getMessage());
            
        } catch (ReservaException.DataInvalidaException e) {
            telaNovaReserva.mostrarMensagem("Data inválida: " + e.getMessage());
            
        } catch (ReservaException.CapacidadeExcedidaException e) {
            telaNovaReserva.mostrarMensagem("Limite de reservas excedido: " + e.getMessage());
            
        } catch (LocaisException.EspacoInativoException e) {
            telaNovaReserva.mostrarMensagem("Espaço indisponível: " + e.getMessage());
            
        } catch (LocaisException.RecursosIndisponiveisException e) {
            telaNovaReserva.mostrarMensagem("Recursos insuficientes: " + e.getMessage());
            
        } catch (LocaisException.HorarioFuncionamentoExcedidoException e) {
            telaNovaReserva.mostrarMensagem("Horário fora do funcionamento: " + e.getMessage());
            
        } catch (UsuarioException e) {
            telaNovaReserva.mostrarMensagem("Erro de usuário: " + e.getMessage());
            
        } catch (Exception e) {
            telaNovaReserva.mostrarMensagem("Erro: " + e.getMessage());
            System.err.println("Erro ao criar reserva: " + e.getMessage());
        }
    }

      private void validarCamposObrigatorios(String nome, String unidade, String sala, String horario) 
            throws ReservaException.DadosObrigatoriosAusentesException {
        
        if (nome == null || nome.trim().isEmpty()) {
            throw new ReservaException.DadosObrigatoriosAusentesException("Nome da reserva");
        }
        if (unidade == null) {
            throw new ReservaException.DadosObrigatoriosAusentesException("Unidade");
        }
        if (sala == null) {
            throw new ReservaException.DadosObrigatoriosAusentesException("Sala");
        }
        if (horario == null) {
            throw new ReservaException.DadosObrigatoriosAusentesException("Horário");
        }
    }
    
    private Local buscarLocal(String nomeSala) throws LocaisException.EspacoNaoEncontradoException {
        try {
            return reservaService.buscarLocalPorNome(nomeSala);
        } catch (LocaisException.EspacoNaoEncontradoException e) {
            throw e;
        }
    }
    

    private Horario criarHorario(String horarioStr) throws ReservaException.DataInvalidaException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime inicio = LocalTime.parse(horarioStr.trim(), formatter);
            return Horario.fromInicio(inicio); 
        } catch (DateTimeParseException | IllegalArgumentException e) {
            throw new ReservaException.DataInvalidaException("Formato de horário inválido: " + horarioStr);
        }
    }

    
    private List<String> obterHorariosDisponiveis(String sala) {
        // Implementar lógica para obter horários disponíveis
        List<String> horarios = new ArrayList<>();
        for (int i = 7; i <= 21; i++) {
            horarios.add(String.format("%02d:00", i));
        }
        return horarios;
    }
}
