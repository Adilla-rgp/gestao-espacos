package controller;

import model.Usuario;

public class FluxoUsuarioController {
    private LoginController loginController;
    private DashboardController dashboardController;
    private SistemaReservasController sistemaReservasController;
    private MinhasReservasController minhasReservasController;

    public FluxoUsuarioController() {
        iniciarLogin();
    }

    private void iniciarLogin() {
        loginController = new LoginController(this);
        loginController.exibirLogin();
    }

    // Chamado pelo LoginController após login válido
    public void abrirDashboard(Usuario usuario) {
        if (loginController != null) loginController.fecharLogin();

        dashboardController = new DashboardController(this, usuario);
        dashboardController.exibirDashboard();
    }

    // Chamado pelo DashboardController ao clicar "Nova Reserva"
    public void abrirSistemaReservas(Usuario usuario) {
        if (dashboardController != null) dashboardController.fecharDashboard();

        sistemaReservasController = new SistemaReservasController(this, usuario);
        sistemaReservasController.exibirSistemaReservas();
    }

    // Chamado pelo SistemaReservasController após confirmar reserva
    public void abrirMinhasReservas(Usuario usuario) {
        if (sistemaReservasController != null) sistemaReservasController.fecharSistemaReservas();

        minhasReservasController = new MinhasReservasController(this, usuario);
        minhasReservasController.exibirMinhasReservas();
    }

    // Opcional: voltar do MinhasReservas para Dashboard
    public void voltarDashboard(Usuario usuario) {
        if (minhasReservasController != null) minhasReservasController.fecharMinhasReservas();

        dashboardController = new DashboardController(this, usuario);
        dashboardController.exibirDashboard();
    }
}