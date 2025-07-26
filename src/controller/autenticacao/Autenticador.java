package controller.autenticacao;
import model.usuario.Usuario;

public class Autenticador {
    private UsuarioRepositorio repositorio;

    //metodo construtor
    public Autenticador(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public boolean autenticar(String email, String senha){
        Usuario u = repositorio.buscarPorEmail(email);
        if(u == null){
            return false;
        }
        return SenhaUtils.verificarSenha(senha, u.getSenhaHash());
    }
}
