package controller.app;

import view.*;
import controller.autenticacao.*;
import controller.dashboard.*;
import controller.espaco.*;
import controller.usuario.*;

public class AplicacaoController {
    private LoginView telaLoginView;
    private CadastroView telaCadastroView;
    private DashboardView telaDashboardView;
    private CadastroEspacoView telaCadastroEspacoView;

    private LoginController loginController;
    private CadastroController cadastroController;
    private DashboardController dashboardController;
    private CadastroEspacoController cadastroEspacoController;

    public AplicacaoController(){
        this.telaLoginView = new LoginView();
        this.telaCadastroView = new CadastroView();
        this.telaDashboardView = new DashboardView();
        this.telaCadastroEspacoView = new CadastroEspacoView();

        this.loginController = new LoginController(telaLoginView, this);
        this.cadastroController = new CadastroController(telaCadastroView, this);
        this.dashboardController = new DashboardController(telaDashboardView, this);
        this.cadastroEspacoController = new CadastroEspacoController(telaCadastroEspacoView, this);

        mostrarTelaLogin();
    }


    public void mostrarTelaLogin(){
        esconderTodasTelas();
        telaLoginView.setVisible(true);
    }

    public void mostrarTelaCadastroUsuario(){
        esconderTodasTelas();
        telaCadastroView.setVisible(true);
    }

    public void mostrarTelaDashboard(){
        esconderTodasTelas();
        telaDashboardView.setVisible(true);

    }

    public void mostrarTelaCadastroEspaco(){
        esconderTodasTelas();
        telaCadastroEspacoView.setVisible(true);
    }

    public void esconderTodasTelas(){
        telaLoginView.setVisible(false);
        telaCadastroView.setVisible(false);
        telaDashboardView.setVisible(false);
        telaCadastroEspacoView.setVisible(false);
    }


    
}
