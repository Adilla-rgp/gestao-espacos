package controller;

import model.Reserva;
import model.Usuario;
import view.MinhasReservasView;

import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MinhasReservasController {
    private MinhasReservasView view;
    private Usuario usuario;
    private List<Reserva> reservas;
    private FluxoUsuarioController fluxoController;

    public MinhasReservasController(FluxoUsuarioController fluxoController, Usuario usuario) {
        this.fluxoController = fluxoController;
        this.usuario = usuario;
        this.view = new MinhasReservasView();

        // Dummy reservas para exibir
        reservas = new ArrayList<>();
        reservas.add(new Reserva(usuario, null, null, null, "Reunião"));

        carregarTabela();

        // Você pode adicionar botão voltar para dashboard aqui se quiser
        // Por exemplo:
        // view.getVoltarButton().addActionListener(e -> {
        //     fecharMinhasReservas();
        //     fluxoController.voltarDashboard(usuario);
        // });
    }

    private void carregarTabela() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Data", "Horário", "Espaço", "Finalidade", "Status"}, 0);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Reserva r : reservas) {
            model.addRow(new Object[]{
                    r.getData() != null ? r.getData().format(dtf) : "",
                    r.getHora() != null ? r.getHora().toString() : "",
                    r.getEspaco() != null ? r.getEspaco().getNome() : "",
                    r.getFinalidade(),
                    r.getStatus()
            });
        }

        view.setTabelaModel(model);
    }

    public void exibirMinhasReservas() {
        view.setVisible(true);
    }

    public void fecharMinhasReservas() {
        view.dispose();
    }
}