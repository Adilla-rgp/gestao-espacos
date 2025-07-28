package controller;

import model.Usuario;
import view.DashboardView;

public class DashboardController {
    private DashboardView dashboardView;
    private Usuario usuario;
    private FluxoUsuarioController fluxoController;

    public DashboardController(FluxoUsuarioController fluxoController, Usuario usuario) {
        this.fluxoController = fluxoController;
        this.usuario = usuario;
        this.dashboardView = new DashboardView();

        dashboardView.setTitulo("Bem-vindo, " + usuario.getNome());

        dashboardView.adicionarNovaReservaListener(e -> {
            fluxoController.abrirSistemaReservas(usuario);
        });
    }

    public void exibirDashboard() {
        dashboardView.setVisible(true);
    }

    public void fecharDashboard() {
        dashboardView.dispose();
    }
}