package model.exceptions;

public class ReservaException extends Exception {
    private String codErro;

    // Construtores
    public ReservaException(String message) { super(message); }
    public ReservaException(String message, String codErro) {
        super(message);
        this.codErro = codErro;
    }
    public ReservaException(String message, Throwable cause) { super(message, cause); }

    // Getter
    public String getCodErro() { return codErro; }

    // -------Exceções específicas

    // Horário indisponível
    public static class HorarioIndisponivelException extends ReservaException {
        public HorarioIndisponivelException(String local, String dataHora) {
            super("Horário indisponível para a reserva do local '" + local + "' em " + dataHora, "HORARIO_INDISPONIVEL");
        }
    }

    // Reserva não encontrada
    public static class ReservaNaoEncontradaException extends ReservaException {
        public ReservaNaoEncontradaException(String identificadorReserva) {
            super("Reserva não encontrada: " + identificadorReserva, "RESERVA_NAO_ENCONTRADA");
        }
    }

    // Data inválida
    public static class DataInvalidaException extends ReservaException {
        public DataInvalidaException(String data) {
            super("Data inválida para reserva: " + data, "DATA_INVALIDA");
        }
    }

    // Capacidade excedida
    public static class CapacidadeExcedidaException extends ReservaException {
        public CapacidadeExcedidaException(int capacidade, int solicitada) {
            super("Capacidade excedida: capacidade máxima " + capacidade + ", solicitada " + solicitada, "CAPACIDADE_EXCEDIDA");
        }
    }

    // Usuário não autorizado
    public static class UsuarioNaoAutorizadoException extends ReservaException {
        public UsuarioNaoAutorizadoException(String usuario) {
            super("Usuário não autorizado para realizar esta operação: " + usuario, "USUARIO_NAO_AUTORIZADO");
        }
    }

    // Espaço inativo
    public static class EspacoInativoException extends ReservaException {
        public EspacoInativoException(String espaco) {
            super("Espaço inativo ou indisponível: " + espaco, "ESPACO_INATIVO");
        }
    }

    // Dados obrigatórios ausentes
    public static class DadosObrigatoriosAusentesException extends ReservaException {
        public DadosObrigatoriosAusentesException(String campo) {
            super("Campo obrigatório ausente: " + campo, "DADOS_OBRIGATORIOS_AUSENTES");
        }
    }

    // Limite de reservas excedido
    public static class LimiteReservasExcedidoException extends ReservaException {
        public LimiteReservasExcedidoException(String usuario, int limite) {
            super("Limite de reservas excedido para o usuário '" + usuario + "'. Limite: " + limite, "LIMITE_RESERVAS_EXCEDIDO");
        }
    }

    // Prazo de cancelamento excedido
    public static class PrazoCancelamentoExcedidoException extends ReservaException {
        public PrazoCancelamentoExcedidoException(String prazo) {
            super("Prazo para cancelamento excedido. Prazo mínimo: " + prazo, "PRAZO_CANCELAMENTO_EXCEDIDO");
        }
    }

    // Reserva ja cancelada
    public static class ReservaJaCanceladaException extends ReservaException {
        public ReservaJaCanceladaException() {
            super("Esta reserva já foi cancelada anteriormente", "RESERVA_JA_CANCELADA");
        }
    }
}
