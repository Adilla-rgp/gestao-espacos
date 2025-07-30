package controller.usuario;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.app.AplicacaoController;
import model.dao.ReservaDAO;
import model.entities.agenda.Reserva;
import view.ReservasView;
import java.util.List;

public class MinhasReservasController {
    private ReservasView telaMinhasReservas;
    private AplicacaoController controller;

    public MinhasReservasController(ReservasView telaMinhasReservas, AplicacaoController controller){
        this.telaMinhasReservas = telaMinhasReservas;
        this.controller = controller;

        telaMinhasReservas.adicionarCancelarButtonListener(e -> cancelar());
        telaMinhasReservas.adicionarConfirmarButtonListener(e -> confirmar());
        telaMinhasReservas.adicionarVoltarButtonListener(e -> controller.mostrarTelaDashboard());
    }

    private void cancelar() {
        JTable tabela = telaMinhasReservas.getTabela();
        int linhaSelecionada = tabela.getSelectedRow();

        if (linhaSelecionada != -1) {

            int idReserva = (int) tabela.getValueAt(linhaSelecionada, 0);

            boolean sucesso = ReservaDAO.atualizarStatusReserva(idReserva, "Cancelada");

            if (sucesso) {
                telaMinhasReservas.mostrarMensagem("Reserva cancelada com sucesso!");
                atualizarTabelaReservasUsuario();                                                   // recarrega tabela
            }else{
                telaMinhasReservas.mostrarMensagem("Erro ao cancelar a reserva.");
            }

        }else{
            telaMinhasReservas.mostrarMensagem("Selecione uma reserva para cancelar.");
        }
    }   

    private void confirmar(){
        JTable tabela = telaMinhasReservas.getTabela();
        int linhaSelecionada = tabela.getSelectedRow();

        if (linhaSelecionada != -1) {

            int idReserva = (int) tabela.getValueAt(linhaSelecionada, 0);

            boolean sucesso = ReservaDAO.atualizarStatusReserva(idReserva, "Confirmada");

            if (sucesso) {
                telaMinhasReservas.mostrarMensagem("Reserva confirmada com sucesso!");
                atualizarTabelaReservasUsuario();                                                       //recarrega tabela
            }else{
                telaMinhasReservas.mostrarMensagem("Erro ao confirmar a reserva.");
            }

        }else{
            telaMinhasReservas.mostrarMensagem("Selecione uma reserva para confirmar.");
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