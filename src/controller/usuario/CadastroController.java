package controller.usuario;

import view.*;
import model.entities.usuario.Usuario;
import model.dao.UsuarioDAO;
import model.exceptions.UsuarioException; //importa exceptions
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
        String nome = cadastroView.getNome();
        String email = cadastroView.getEmail();
        String senha = cadastroView.getSenha();

        try{
            // Validar dados de entrada
            validarDadosCadastro(nome, email, senha);
            
            // Verificar usuario existente
            Usuario usuarioExistente = UsuarioDAO.buscarPorEmail(email);
            if(usuarioExistente != null){
                throw new UsuarioException.UsuarioJaExistenteException(email);
            }
            
            // Criar e inserir usuário
            Usuario user = new Usuario(nome, email, senha);
            UsuarioDAO.inserirUsuario(user);
            
            cadastroView.mostrarMensagem("Usuário cadastrado com sucesso!");
            
            //implementação das exceptions:
        } catch (UsuarioException.UsuarioJaExistenteException e) {
            cadastroView.mostrarMensagem("Este email já está cadastrado: " + e.getMessage());
            
        } catch (UsuarioException e) {
            cadastroView.mostrarMensagem("Erro de validação: " + e.getMessage());
            
        } catch (Exception e) {
            cadastroView.mostrarMensagem("Erro interno: Não foi possível cadastrar o usuário");
            System.err.println("Erro no cadastro: " + e.getMessage());
        }
    
    }

    private boolean isEmailValido(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }

    private void validarDadosCadastro(String nome, String email, String senha) throws UsuarioException {
        // Validar nome
        if(nome == null || nome.trim().isEmpty()){
            throw new UsuarioException("Nome é obrigatório");
        }
        //nome com menos de 2 caracteres
        if(nome.trim().length() < 2){
            throw new UsuarioException("Nome deve ter pelo menos 2 caracteres");
        }
        
        // Validar email: falta de email e formato invalido
        if(email == null || email.trim().isEmpty()){
            throw new UsuarioException("Email é obrigatório");
        }
        if(!isEmailValido(email)){
            throw new UsuarioException("Formato de email inválido");
        }
        
        // Validar senha
        if(senha == null || senha.trim().isEmpty()){
            throw new UsuarioException("Senha é obrigatória");
        }
    }
}
