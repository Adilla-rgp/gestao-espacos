package controller.app;

import java.awt.event.AdjustmentListener;

import controller.adm.AdminDashboardController;
import controller.adm.TodasReservasController;
import controller.autenticacao.*;
import controller.dashboard.*;
import controller.espaco.*;
import controller.usuario.*;
import model.entities.usuario.Usuario;
import view.*;

public class AplicacaoController {
    private Usuario user;

    private LoginView telaLoginView;
    private CadastroView telaCadastroView;
    private DashboardView telaDashboardView;
    private CadastroEspacoView telaCadastroEspacoView;
    private ReservasView telaMinhasReservas;
    private TelaNovaReserva telaNovaReserva;
    private AdminDashboardView telaADMDashboard;
    private CadastroUnidadeFisicaView telaCadastraUnidFisica;
    private TodasReservasView telaTodasReservas;

    private LoginController loginController;
    private CadastroController cadastroController;
    private DashboardController dashboardController;
    private CadastrarEspacoController cadastroEspacoController;
    private MinhasReservasController minhasReservasController;
    private NovaReservaController novaReservaController;
    private AdminDashboardController ADMDashboardController;
    private CadastrarUnidadeFisicaController cadastrarUnidadeFisicaController;
    private TodasReservasController todasReservasController;

    public AplicacaoController() {
        this.telaLoginView = new LoginView();
        this.telaCadastroView = new CadastroView();
        this.telaDashboardView = new DashboardView();
        this.telaCadastroEspacoView = new CadastroEspacoView();
        this.telaMinhasReservas = new ReservasView();
        this.telaNovaReserva = new TelaNovaReserva();
        this.telaADMDashboard = new AdminDashboardView();
        this.telaCadastraUnidFisica = new CadastroUnidadeFisicaView();
        this.telaTodasReservas = new TodasReservasView();

        this.loginController = new LoginController(telaLoginView, this);
        this.cadastroController = new CadastroController(telaCadastroView, this);
        this.dashboardController = new DashboardController(telaDashboardView, this);
        this.cadastroEspacoController = new CadastrarEspacoController(telaCadastroEspacoView, this);
        this.minhasReservasController = new MinhasReservasController(telaMinhasReservas, this);
        this.novaReservaController = new NovaReservaController(telaNovaReserva, this);
        this.ADMDashboardController = new AdminDashboardController(telaADMDashboard, this);
        this.cadastrarUnidadeFisicaController = new CadastrarUnidadeFisicaController(telaCadastraUnidFisica, this);
        this.todasReservasController = new TodasReservasController(telaTodasReservas, this);

        mostrarTelaLogin();
    }

    public void mostrarTelaLogin() {
        esconderTodasTelas();
        telaLoginView.setVisible(true);
    }

    public void mostrarTelaCadastroUsuario() {
        esconderTodasTelas();
        telaCadastroView.setVisible(true);
    }

    public void mostrarTelaDashboard() {
        esconderTodasTelas();
        if(user.getStatusAdm()){
            telaADMDashboard.setVisible(true);
        }else{
            telaDashboardView.setVisible(true);
        }
    }
    
    public void mostrarTelaMinhasReservas() {
        esconderTodasTelas();
        telaMinhasReservas.setVisible(true);
    }
    public void mostrarTelaCadastrarEspaco() {
        if(user.getStatusAdm()){
            esconderTodasTelas();
            telaCadastroEspacoView.setVisible(true);
        }else{
            mostrarTelaDashboard();
        }
    }

    public void mostrarTelaNovaReserva(){
        esconderTodasTelas();
        telaNovaReserva.setVisible(true);
    }

    public void mostrarTelaADMDashboard(){
        esconderTodasTelas();
        telaADMDashboard.setVisible(true);
    }

    public void mostrarTelaCadastroUnidFisica(){
        esconderTodasTelas();
        telaCadastraUnidFisica.setVisible(true);
    }

    public void mostrarTelaTodasReservas(){
        esconderTodasTelas();
        telaTodasReservas.setVisible(true);
    }
    
    public void esconderTodasTelas() {
        telaLoginView.setVisible(false);
        telaCadastroView.setVisible(false);
        telaDashboardView.setVisible(false);
        telaMinhasReservas.setVisible(false);
        telaCadastroEspacoView.setVisible(false);
        telaNovaReserva.setVisible(false);
        telaADMDashboard.setVisible(false);
        telaCadastraUnidFisica.setVisible(false);
        telaTodasReservas.setVisible(false);
    }

    public Usuario getUsuario(){
        return this.user;
    }

    public void setUsuario(Usuario usuario){
        this.user = usuario;
        minhasReservasController.usuarioLogado();
        todasReservasController.usuarioLogado();
    }

    public void atualizarTabelaReservasUsuario(){
        minhasReservasController.atualizarTabelaReservasUsuario();
    }
}