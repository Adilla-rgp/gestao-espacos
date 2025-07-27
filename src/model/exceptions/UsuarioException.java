package model.exceptions;

public class UsuarioException extends Exception {
    private String codErro; //código de erro

    // Construtores
    public UsuarioException(String codErro, String message) {
        super(message);
        this.codErro = codErro;
    }

    public UsuarioException(String message) { super(message); }

    public String getCodErro() { return codErro; }

    // Exceptions específicas

    // Usuário não encontrado
    public static class UsuarioNaoEncontradoException extends UsuarioException {
        public UsuarioNaoEncontradoException(String usuario) {
            super("USUARIO_NAO_ENCONTRADO", "Usuário não encontrado: " + usuario);
        }
    }

    // Usuário inativo
    public static class UsuarioInativoException extends UsuarioException {
        public UsuarioInativoException(String usuario) {
            super("USUARIO_INATIVO", "Usuário inativo: " + usuario);
        }
    }

    // Usuário não permitido a fazer tal ação
    public static class UsuarioNaoPermitidoException extends UsuarioException {
        public UsuarioNaoPermitidoException(String usuario) {
            super("USUARIO_NAO_PERMITIDO", "Usuário não tem permissão para esta ação: " + usuario);
        }
    }

    // Usuário já existente
    public static class UsuarioJaExistenteException extends UsuarioException {
        public UsuarioJaExistenteException(String usuario) {
            super("USUARIO_JA_EXISTENTE", "Usuário já existente: " + usuario);
        }
    }
}