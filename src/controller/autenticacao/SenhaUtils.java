package controller.autenticacao;

public class SenhaUtils {

    // Gera uma hash simples a partir da senha
    public static String gerarHash(String senha) {
        return String.valueOf(senha.hashCode());
    }

    // Compara a senha digitada com o hash salvo
    public static boolean verificarSenha(String senhaDigitada, String senhaHashSalva) {
        String hashDigitada = String.valueOf(senhaDigitada.hashCode());
        return hashDigitada.equals(senhaHashSalva);
    }
}
