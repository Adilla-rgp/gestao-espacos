package controller.dashboard;

import view.DashboardView;
import controller.app.AplicacaoController;;

public class DashboardController {
    private DashboardView dashboardView;
    private AplicacaoController controller;

    public DashboardController(DashboardView dashboardView, AplicacaoController controller){
        this.dashboardView = dashboardView;
        this.controller = controller;

        dashboardView.adicionarNovaReservaListener(e -> controller.mostrarTelaCadastroEspaco());
    }
}
