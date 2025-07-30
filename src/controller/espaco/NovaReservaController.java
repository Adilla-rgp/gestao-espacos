package controller.espaco;

import controller.app.AplicacaoController;
import model.dao.EspacoDAO;
import model.dao.ReservaDAO;
import model.dao.UnidadeFisicaDAO;
import model.entities.predios.UnidadeFisica;
import view.TelaNovaReserva;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.time.format.DateTimeFormatter;

import model.entities.locais.*;
import model.entities.agenda.*;


public class NovaReservaController {
    private TelaNovaReserva telaNovaReserva;
    private AplicacaoController controller;

    public NovaReservaController(TelaNovaReserva telaNovaReserva, AplicacaoController controller){
        this.telaNovaReserva = telaNovaReserva;
        this.controller = controller;

        carregarUnidades();

        telaNovaReserva.adicionarVoltarBottonListener(e -> controller.mostrarTelaDashboard());
        telaNovaReserva.adicionarConfirmarBottonListener(e -> confirmar());
        telaNovaReserva.getComboUnidade().addActionListener(e -> carregarComboLocais());
        telaNovaReserva.getComboSala().addActionListener(e -> carregarHorariosDisponiveis());
    }
    
    private void carregarUnidades(){
        List<UnidadeFisica> listaUnidadesFisicas = UnidadeFisicaDAO.listarTodas();
        List<String> listaUnidadeFisicaNome = new ArrayList<>();
        for(UnidadeFisica unidadeFisica : listaUnidadesFisicas){
            String nomeUnidade = unidadeFisica.getNome();
            listaUnidadeFisicaNome.add(nomeUnidade);
        }
        telaNovaReserva.atualizarComboUnidade(listaUnidadeFisicaNome);
    }
    
    private void carregarComboLocais(){
        String unidadeSelecionada = (String) telaNovaReserva.getComboUnidade().getSelectedItem();
        if (unidadeSelecionada != null) {
            carregarSalas(unidadeSelecionada);
        }     
    }
    
    private void carregarSalas(String unidadeSelecionada){
        try{
            String nomeUnidade = telaNovaReserva.getUnidadeSelecionada();
            int idUnidade = UnidadeFisicaDAO.buscarIdUnidadePorNome(nomeUnidade);
            List<Local> locais = EspacoDAO.listarLocaisPorUnidade(idUnidade);
            List<String> locaisNomes = new ArrayList<>();
            for(Local local : locais){
                locaisNomes.add(local.getNome());
            }
            telaNovaReserva.atualizarComboSala(locaisNomes);
        }catch(Exception e){
            telaNovaReserva.mostrarMensagem("Erro ao carregar as salas");
        }
    }

private void carregarHorariosDisponiveis(){
    try {
        String dataString = telaNovaReserva.getDataSelecionada().trim();

        if (dataString.isEmpty() || dataString.equalsIgnoreCase("dd/mm/aaaa")) {
            telaNovaReserva.atualizarComboHorario(new ArrayList<>());
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dataString, formatter);

        String nomeString = telaNovaReserva.getSalaSelecionada();

        if (nomeString == null || nomeString.trim().isEmpty()) {
            telaNovaReserva.atualizarComboHorario(new ArrayList<>());
            return;
        }

        System.out.println("Data selecionada: " + data);
        System.out.println("Sala selecionada: " + nomeString);

        Local local = EspacoDAO.buscarPorNome(nomeString);

        if (local == null) {
            telaNovaReserva.mostrarMensagem("Sala não encontrada.");
            return;
        }

        System.out.println("-------------------------" + local.getId() + "--------------" + local.getNome());

        List<Reserva> reservasDia = ReservaDAO.listarReservasPorDiaELocal(local.getId(), data);

        System.out.println("Reservas encontradas: " + reservasDia.size());
        for (Reserva r : reservasDia) {
            Horario h = r.getHorario();
            System.out.println("Reserva: " + (h != null ? h.getInicio() + " - " + h.getFim() : "Horário null"));
        }

        System.out.println("Reservas encontradas: " + reservasDia.size());

        // Agora vamos comparar horários como strings para garantir a igualdade correta
        Set<String> horariosOcupados = new HashSet<>();
        for (Reserva reserva : reservasDia) {
            Horario h = reserva.getHorario();
            if (h != null) {
                String intervalo = h.getInicio().toString() + " - " + h.getFim().toString();
                horariosOcupados.add(intervalo);
                System.out.println("Horário ocupado: " + intervalo);
            }
        }

        List<String> horariosLivres = new ArrayList<>();
        for (Horario h : Horario.values()) {
            String intervalo = h.getInicio().toString() + " - " + h.getFim().toString();
            if (!horariosOcupados.contains(intervalo)) {
                horariosLivres.add(intervalo);
            }
        }

        telaNovaReserva.atualizarComboHorario(horariosLivres);

    } catch (Exception e) {
        telaNovaReserva.mostrarMensagem("Erro ao carregar os horários disponíveis.");
        e.printStackTrace();
    }
}

    private void confirmar() {
    try {
        // Obter dados da tela
        String dataString = telaNovaReserva.getDataSelecionada().trim();
        String salaSelecionada = telaNovaReserva.getSalaSelecionada();
        String horarioSelecionado = (String) telaNovaReserva.getComboHorario().getSelectedItem();

        // Validações básicas
        if (dataString.isEmpty() || salaSelecionada == null || horarioSelecionado == null) {
            telaNovaReserva.mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        // Converter data do formato dd/MM/yyyy para LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dataString, formatter);

        // Buscar Local pelo nome
        Local local = EspacoDAO.buscarPorNome(salaSelecionada);
        if (local == null) {
            telaNovaReserva.mostrarMensagem("Sala selecionada não encontrada.");
            return;
        }

        // Identificar o Horário pelo enum
        String[] partes = horarioSelecionado.split(" - ");
        if (partes.length != 2) {
            telaNovaReserva.mostrarMensagem("Horário inválido.");
            return;
        }
        String inicioStr = partes[0].trim();
        String fimStr = partes[1].trim();

        DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime inicio = LocalTime.parse(inicioStr, horaFormatter);
        LocalTime fim = LocalTime.parse(fimStr, horaFormatter);

        Horario horario = null;
        for (Horario h : Horario.values()) {
            if (h.getInicio().equals(inicio) && h.getFim().equals(fim)) {
                horario = h;
                break;
            }
        }

        if (horario == null) {
            telaNovaReserva.mostrarMensagem("Horário selecionado não corresponde a nenhum disponível.");
            return;
        }

        // Usuário logado
        int idUsuario = controller.getUsuario().getId();

        // Criar reserva
        Reserva reserva = new Reserva();
        reserva.setIdUsuario(idUsuario);
        reserva.setIdEspaco(local.getId());
        reserva.setNome("Reserva de " + salaSelecionada);
        reserva.setDescricao("Reserva feita via sistema");
        reserva.setData(data);
        reserva.setHorario(horario);
        reserva.setStatus("Agendada");
        
        ReservaDAO.inserirReserva(reserva);

        telaNovaReserva.mostrarMensagem("Reserva confirmada com sucesso!");
        controller.mostrarTelaDashboard();

    } catch (Exception e) {
        e.printStackTrace();
        telaNovaReserva.mostrarMensagem("Erro ao confirmar a reserva: " + e.getMessage());
    }
    }
}
