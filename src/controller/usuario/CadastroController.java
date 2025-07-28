package controller.usuario;

import view.*;
import model.entities.usuario.Usuario;
import model.dao.UsuarioDAO;
import controller.app.AplicacaoController;

public class CadastroController {
    private CadastroView cadastroView;
    private AplicacaoController controller;

    public CadastroController(CadastroView cadastroView, AplicacaoController controller){
        this.cadastroView = cadastroView;
        this.controller = controller;

        cadastroView.acicionarConcluirButtonListener(e -> Cadastrar());
        cadastroView.adicionarVoltarButtonListener(e -> voltarTelaLogin());
    }

    private void voltarTelaLogin(){
        controller.mostrarTelaLogin();
    }

    public void Cadastrar(){
        String nome = cadastroView.getName();
        String email = cadastroView.getEmail();
        String senha = cadastroView.getSenha();
        try{
            Usuario user = new Usuario(nome, email, senha);
            UsuarioDAO.inserirUsuario(user);
            cadastroView.mostrarMensagem("Usuario cadastrado com sucesso");
        }catch(Exception e){
            cadastroView.mostrarMensagem("Não foi possivel cadastrar esse usuario");
        }
    }
}
