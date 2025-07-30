package controller.autenticacao;

import view.*;
import model.dao.UsuarioDAO;
import model.entities.usuario.Usuario;
import controller.autenticacao.SenhaUtils;
import controller.app.AplicacaoController;

public class LoginController {
    private LoginView telaLogin;
    private AplicacaoController controller;


    public LoginController(LoginView loginView, AplicacaoController controller){
        this.telaLogin = loginView;
        this.controller = controller;

       loginView.adicionarLoginListener(e -> fazerLogin());

       telaLogin.adicionarCadastrarListener(e-> controller.mostrarTelaCadastroUsuario());
    }

    private void fazerLogin(){
        String email = telaLogin.getEmail();
        String senha = telaLogin.getSenha();

        try{
            Usuario user = UsuarioDAO.buscarPorEmail(email);
            if(user != null){        
                String senhaHash = SenhaUtils.gerarHash(senha);                 //verifica se encontrou o usuario pelo email
                if(senhaHash.equals(user.getSenhaHash())){                      //verifica se a senha está correta
                    controller.setUsuario(user);
                    if(controller.getUsuario().getStatusAdm()){
                        controller.mostrarTelaADMDashboard();
                    }else{
                        controller.mostrarTelaDashboard();
                    }
                }else{
                    telaLogin.mostrarMensagem("Dados invalidos");
                }
            }else{
                telaLogin.mostrarMensagem("Dados invalidos");
            }

        }catch(Exception e){
            telaLogin.mostrarMensagem("Erro ao buscar");
                e.printStackTrace();

        }
    }

}
