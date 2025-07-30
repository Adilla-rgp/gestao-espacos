package controller.usuario;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.app.AplicacaoController;
import model.dao.ReservaDAO;
import model.entities.agenda.Reserva;
import model.exceptions.ReservaException;
import model.exceptions.UsuarioException;
import model.services.ReservaService;
import view.MinhasReservasView;
import java.util.List;

public class MinhasReservasController {
    private MinhasReservasView telaMinhasReservas;
    private AplicacaoController controller;

    public MinhasReservasController(MinhasReservasView telaMinhasReservas, AplicacaoController controller){
        this.telaMinhasReservas = telaMinhasReservas;
        this.controller = controller;

        telaMinhasReservas.adicionarCancelarButtonListener(e -> cancelar());
        telaMinhasReservas.adicionarConfirmarButtonListener(e -> confirmar());
    }
    
    private void cancelar() {
        try {
            JTable tabela = telaMinhasReservas.getTabela();
            int linhaSelecionada = tabela.getSelectedRow();

            if (linhaSelecionada == -1) {
                throw new ReservaException("Selecione uma reserva para cancelar");
            }

            int idReserva = (int) tabela.getValueAt(linhaSelecionada, 0);
            String status = (String) tabela.getValueAt(linhaSelecionada, 4);
            
            // Verificações
            if ("Cancelada".equals(status)) {
                throw new ReservaException.ReservaJaCanceladaException();
            }
            
            // Simular cancelamento
            telaMinhasReservas.mostrarMensagem("Reserva cancelada com sucesso!");
            atualizarTabelaReservasUsuario();
            
        } catch (ReservaException.ReservaJaCanceladaException e) {
            telaMinhasReservas.mostrarMensagem("Esta reserva já foi cancelada");
        } catch (ReservaException e) {
            telaMinhasReservas.mostrarMensagem(e.getMessage());
        } catch (Exception e) {
            telaMinhasReservas.mostrarMensagem("Erro ao cancelar reserva");
        }
    } 

    private void confirmar(){
        try {
            JTable tabela = telaMinhasReservas.getTabela();
            int linhaSelecionada = tabela.getSelectedRow();

            if (linhaSelecionada == -1) {
                throw new ReservaException("Selecione uma reserva para confirmar");
            }

            String status = (String) tabela.getValueAt(linhaSelecionada, 4);
            
            if ("Confirmada".equals(status)) {
                telaMinhasReservas.mostrarMensagem("Esta reserva já está confirmada");
                return;
            }
            
            telaMinhasReservas.mostrarMensagem("Reserva confirmada com sucesso!");
            atualizarTabelaReservasUsuario();
            
        } catch (ReservaException e) {
            telaMinhasReservas.mostrarMensagem(e.getMessage());
        } catch (Exception e) {
            telaMinhasReservas.mostrarMensagem("Erro ao confirmar reserva");
        }
    }

    private void tabelaMinhasReservas(){    
        List<Reserva> listaDeReservas = ReservaDAO.listarReservasPorUsuario(controller.getUsuario().getId());
        DefaultTableModel model = new DefaultTableModel(
            new String[] { "ID", "Espaço", "Data", "Horário", "Status" }, 0
        );  

        for (Reserva r : listaDeReservas) {
            model.addRow(new Object[] {
                r.getId(),
                r.getEspacoNome(),
                r.getData().toString(),
                r.toStringHorario(),
                r.getStatus()
            });
        }
        telaMinhasReservas.setTabelaModel(model);
    }

    public void usuarioLogado() {
        tabelaMinhasReservas();
    }

    public void atualizarTabelaReservasUsuario(){
        tabelaMinhasReservas();
    }
}
