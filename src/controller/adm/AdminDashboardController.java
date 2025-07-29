package controller.adm;

import controller.app.AplicacaoController;
import view.AdminDashboardView;

public class AdminDashboardController {
    private AdminDashboardView telaDashboard;
    private AplicacaoController controller;

    public AdminDashboardController(AdminDashboardView telaDashboard, AplicacaoController controller){
        this.telaDashboard = telaDashboard;
        this.controller = controller;

        telaDashboard.adicionarMinhasReservasListener(e -> controller.mostrarTelaMinhasReservas());
        telaDashboard.adicionarCadastrarEspacosButtonListener(e -> controller.mostrarTelaCadastroEspaco());

    }
}
