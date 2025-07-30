package controller.espaco;

import javax.sound.midi.SysexMessage;

import controller.app.AplicacaoController;
import view.AtributosEspacoView;
import model.dao.EspacoDAO;
import model.dao.UnidadeFisicaDAO;
import model.entities.locais.*;
import model.entities.predios.UnidadeFisica;
import model.enums.TipoLaboratorio;
import model.enums.TipoQuadra;
import model.exceptions.LocaisException.TipoEspacoInvalidoException;

public class AtributosEspacoController {
    private AtributosEspacoView telaAtributosEspaco;
    private AplicacaoController controller;

    public AtributosEspacoController(AtributosEspacoView telaAtributosEspaco, AplicacaoController controller){
        this.telaAtributosEspaco = telaAtributosEspaco;
        this.controller = controller;

        telaAtributosEspaco.adicionarCancelarButtonListener(e -> controller.mostrarTelaADMDashboard());
        telaAtributosEspaco.adicionarConcluirButtonListener(e -> concluirCadastro());
    }

    private void concluirCadastro(){

        String tipoSelecionado = controller.getTipoSelecionadoDTO();
        String nome = controller.getNomeDTO();
        String descricao = controller.getDescricaoDTO();
        int capacidade = Integer.valueOf(controller.getCapacidadeDTO());
        
        String nomeUnidadeFisica = controller.getUnidadeFisicaDTO();
        int id_unidadeFisica = UnidadeFisicaDAO.buscarIdPorNome(nomeUnidadeFisica);

        switch (tipoSelecionado){
            case "Sala":

                int quant_projetores = telaAtributosEspaco.getQuantProjetoresSala();
                int quant_arCondicionado = telaAtributosEspaco.getQuantArCondicionadoSala();
                Sala sala = new Sala(nome, descricao, "Ativo", capacidade, quant_projetores, quant_arCondicionado);
                if(sala != null){
                    try{
                        int id = EspacoDAO.inserirEspaco(sala, id_unidadeFisica);
                        if(id != -1){
                            System.out.println("Uma nova sala foi adicionada ao banco de dados. Nome:" + nome);
                        }
                    }catch(Exception e){
                        telaAtributosEspaco.mostrarMensagem("ERRO ao adicionar Espaco");
                    }
                }else{
                    telaAtributosEspaco.mostrarMensagem("Não foi possível inserir esse espaco");
                }
                break;
                    
            case "Laboratório":
                try {
                    int quant_equipamentos = telaAtributosEspaco.getQuantEquipamentosLab();
                    String tipoLaboraString = telaAtributosEspaco.getTipoLaboratorio();
                    
                    TipoLaboratorio tipoLaboratorio = TipoLaboratorio.fromDescricao(tipoLaboraString);
                    
                    Laboratorio laboratorio = new Laboratorio(nome, descricao, "Ativo", capacidade, quant_equipamentos, tipoLaboratorio);
                    
                    int id = EspacoDAO.inserirEspaco(laboratorio, id_unidadeFisica);
                    if(id != -1){
                        System.out.println("Novo laboratório adicionado. Nome: " + nome + " Tipo: " + tipoLaboraString);
                    } else {
                        telaAtributosEspaco.mostrarMensagem("Falha ao inserir no banco de dados");
                    }
                    
                } catch (IllegalArgumentException e) {
                    System.err.println("Erro no tipo de laboratório: " + e.getMessage());
                    telaAtributosEspaco.mostrarMensagem("Tipo de laboratório inválido: " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("Erro ao cadastrar laboratório: " + e.getMessage());
                    telaAtributosEspaco.mostrarMensagem("ERRO ao adicionar laboratório: " + e.getMessage());
                }
                break;
            
            case "Auditório":
                
                boolean possui_sistema_som = telaAtributosEspaco.isSistemaSomAuditorio();
                boolean possui_palco = telaAtributosEspaco.isPalcoAuditorio();

                Auditorio auditorio = new Auditorio(nome, descricao, "Ativo", capacidade, possui_sistema_som, possui_palco);
                if(auditorio != null){
                    try{
                        int id = EspacoDAO.inserirEspaco(auditorio, id_unidadeFisica);
                        if(id != -1){
                            System.out.println("Um novo laboratorio foi adicionada ao banco de dados. Nome:" + nome);
                        }
                    }catch(Exception e){
                        telaAtributosEspaco.mostrarMensagem("ERRO ao adicionar Espaco");
                    }
                }else{
                    telaAtributosEspaco.mostrarMensagem("Não foi possível inserir esse espaco");
                }
            case "Quadra":
                try {
                    boolean eh_coberta = telaAtributosEspaco.isCobertaQuadra();
                    boolean possui_iluminacao = telaAtributosEspaco.isIluminacaoQuadra();
                    
                    String tipoQuadraString = telaAtributosEspaco.getTipoQuadra();
                    
                    TipoQuadra tipoQuadra = TipoQuadra.fromDescricao(tipoQuadraString);
                    
                    Quadra quadra = new Quadra(nome, descricao, "Ativo", capacidade, tipoQuadra, eh_coberta, possui_iluminacao);
                    
                    int id = EspacoDAO.inserirEspaco(quadra, id_unidadeFisica);
                    
                    if(id != -1){
                        System.out.println("Nova quadra adicionada. Nome: " + nome + " Tipo: " + tipoQuadraString);
                    } else {
                        telaAtributosEspaco.mostrarMensagem("Falha ao inserir no banco de dados");
                    }
                    
                } catch (IllegalArgumentException e) {
                    System.err.println("Erro IllegalArgumentException: " + e.getMessage());
                    e.printStackTrace();
                    telaAtributosEspaco.mostrarMensagem("Tipo de quadra inválido: " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("Erro Exception: " + e.getMessage());
                    System.err.println("Tipo da exceção: " + e.getClass().getName());
                    telaAtributosEspaco.mostrarMensagem("ERRO ao adicionar quadra: " + e.getMessage());
                }
                break;

        case "Sala de Reunião":
                boolean possui_videoconferencia = telaAtributosEspaco.isVideoconferencia();
                boolean possui_sistemaSom = telaAtributosEspaco.isSistemaSomReuniao();
                int quant_projetor = telaAtributosEspaco.getQuantProjetoresReuniao();

                SalaReuniao salaReuniao = new SalaReuniao(nome, descricao, "Ativo", capacidade, quant_projetor, possui_videoconferencia, possui_sistemaSom);
                if(salaReuniao != null){
                        try{
                            int id = EspacoDAO.inserirEspaco(salaReuniao, id_unidadeFisica);
                            if(id != -1){
                                System.out.println("Uma nova Sala de Reuniao foi adicionada ao banco de dados. Nome:" + nome);
                            }
                        }catch(Exception e){
                            telaAtributosEspaco.mostrarMensagem("ERRO ao adicionar Espaco");
                        }
                    }else{
                        telaAtributosEspaco.mostrarMensagem("Não foi possível inserir esse espaco");
                    }
                break;
        case "Campo":
                boolean possui_Iluminacao = telaAtributosEspaco.isIluminacaoCampo();
                boolean possui_vestiario = telaAtributosEspaco.isVestiarioCampo();
                
                Campo campo = new Campo(nome, descricao, "Ativo", capacidade, possui_Iluminacao, possui_vestiario);
                if(campo != null){
                        try{
                            int id = EspacoDAO.inserirEspaco(campo, id_unidadeFisica);
                            if(id != -1){
                                System.out.println("Um nov campo foi adicionado ao banco de dados. Nome:" + nome);
                            }
                        }catch(Exception e){
                            telaAtributosEspaco.mostrarMensagem("ERRO ao adicionar Espaco");
                        }
                    }else{
                        telaAtributosEspaco.mostrarMensagem("Não foi possível inserir esse espaco");
                    }
                break;
        default:
            telaAtributosEspaco.mostrarMensagem("ERRO; voltando ao dashboard");
            controller.mostrarTelaADMDashboard();
            break;
        }
        controller.mostrarTelaADMDashboard();
    }
    
}
