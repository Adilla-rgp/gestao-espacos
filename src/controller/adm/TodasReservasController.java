package controller.adm;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import controller.app.AplicacaoController;
import model.dao.ReservaDAO;
import model.entities.agenda.Reserva;
import view.TodasReservasView;
import java.util.List;
import model.dao.UsuarioDAO;
import model.entities.usuario.Usuario;;

public class TodasReservasController {
    private TodasReservasView telaTodasReservas;
    private AplicacaoController controller;

    public TodasReservasController(TodasReservasView telaTodasReservas, AplicacaoController controller){
        this.telaTodasReservas = telaTodasReservas;
        this.controller = controller;

        telaTodasReservas.adicionarVoltarButtonListener(e -> controller.mostrarTelaADMDashboard());
    }

    private void tabelaTodasReserva(){
        List<Reserva> listaDeReservas = ReservaDAO.listarTodasReservas();
        DefaultTableModel model = new DefaultTableModel(
            new String[] { "ID", "Espaço", "Data", "Horário", "Status", "Solicitante"}, 0
        );  
        try{
            for (Reserva r : listaDeReservas) {
                System.out.println(r.getNome());
                Usuario user = UsuarioDAO.buscarPorID(r.getIdUsuario());
                model.addRow(new Object[] {
                    r.getId(),
                    r.getEspacoNome(),
                    r.getData().toString(),
                    r.toStringHorario(),
                    r.getStatus(),
                    user.getNome()
                });
            }

        }catch(Exception e){
            model.addRow( new String[] {"ERRO AO BUSCAR. ERRO: " + e.getMessage()});
        }
        telaTodasReservas.setTabelaModel(model);
    }

    public void usuarioLogado(){
        tabelaTodasReserva();
    }

    public void atualizarTabelaTodasReservas(){
        tabelaTodasReserva();
    }
}
