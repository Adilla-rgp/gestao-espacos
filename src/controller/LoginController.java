package controller;

import model.Usuario;
import view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView loginView;
    private FluxoUsuarioController fluxoController;

    public LoginController(FluxoUsuarioController fluxoController) {
        this.fluxoController = fluxoController;
        this.loginView = new LoginView();

        this.loginView.adicionarLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = loginView.getUsuario();
                String senha = loginView.getSenha();

                if (usuario.equalsIgnoreCase("admin") && senha.equals("1234")) {
                    loginView.mostrarMensagem("");
                    fluxoController.abrirDashboard(new Usuario("Admin", "admin", "admin@admin.com", "1234", "Admin"));
                } else if (usuario.equalsIgnoreCase("user") && senha.equals("1234")) {
                    loginView.mostrarMensagem("");
                    fluxoController.abrirDashboard(new Usuario("Usuário", "user", "user@user.com", "1234", "Usuario"));
                } else {
                    loginView.mostrarMensagem("Usuário ou senha inválidos!");
                }
            }
        });
    }

    public void exibirLogin() {
        loginView.setVisible(true);
    }

    public void fecharLogin() {
        loginView.dispose();
    }
}