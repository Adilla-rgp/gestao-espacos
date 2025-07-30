package controller.autenticacao;

import view.*;
import model.dao.UsuarioDAO;
import model.entities.usuario.Usuario;
import model.exceptions.UsuarioException; //importa o package de exceptions
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

            //validação de login e senha
            validarCamposLogin(email, senha);

            Usuario user = UsuarioDAO.buscarPorEmail(email);

            if(user != null){    

                String senhaHash = SenhaUtils.gerarHash(senha);  
       
                if(senhaHash.equals(user.getSenhaHash())){                      //verifica se a senha está correta
                    controller.setUsuario(user);

                    if(controller.getUsuario().getStatusAdm()){
                        controller.mostrarTelaADMDashboard();
                    }else{
                        controller.mostrarTelaDashboard();
                    }
                }else{
                    telaLogin.mostrarMensagem("Credenciais inválidas"); //caso a verificação dos dados nao der certo
                }
            }else{
                //se o usuario nao existir, joga a exception:
                throw new UsuarioException.UsuarioNaoEncontradoException(email);
            }

        }catch(Exception e){
            telaLogin.mostrarMensagem("Erro ao buscar");
        }
    }

    private void validarCamposLogin(String email, String senha) throws UsuarioException {
        if(email == null || email.trim().isEmpty()){
            throw new UsuarioException("Email é obrigatório!");
        }
        if(senha == null || senha.trim().isEmpty()){
            throw new UsuarioException("Senha é obrigatória!");
        }
        if(!isEmailValido(email)){
            throw new UsuarioException("Formato de email inválido!");
        }
    }

    private boolean isEmailValido(String email) {
        return email.contains("@") && email.contains(".");
    }



}
