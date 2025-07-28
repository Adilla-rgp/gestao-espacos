package controller.autenticacao;
import java.util.HashMap;
import java.util.Map;
import model.entities.usuario.Usuario;

public class UsuarioRepositorio {
    private Map<String, Usuario> usuarios;

    //metodo construtor
    public UsuarioRepositorio(){
        this.usuarios = new HashMap<>();
    }

    public Usuario buscarPorEmail(String email){
        return usuarios.get(email);
    }

    public void adicionarUsuarioRepositorio(Usuario u){
        this.usuarios.put(u.getEmail(), u);
    }
}
