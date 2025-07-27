package model.exceptions;

public class LocaisException extends Exception {
    private String codErro;

    //constrtutores
    public LocaisException(String message) {super(message);}
   
    public LocaisException(String message, Throwable causa) {super(message, causa); }

    public LocaisException(String message, String codErro) {
        super(message); this.codErro = codErro;
    }

    //getter
    public String getCodErro() {return codErro; }

    //exceptions especificas

      // Espaço não encontrado
    public static class EspacoNaoEncontradoException extends LocaisException {
        public EspacoNaoEncontradoException(String espaco) {
            super("Espaço não encontrado: " + espaco, "ESPAÇO_NAO_ENCONTRADO");
        }
    }

    // Espaço inativo
    public static class EspacoInativoException extends LocaisException {
        public EspacoInativoException(String espaco) {
            super("Espaço inativo: " + espaco, "ESPAÇO_INATIVO");
        }
    }

      // Capacidade insuficiente
    public static class CapacidadeInsuficienteException extends LocaisException {
        public CapacidadeInsuficienteException(int capacidade, int solicitada) {
            super("Capacidade insuficiente: disponível " + capacidade + ", solicitada " + solicitada, "CAPACIDADE_INSUFICIENTE");
        }
    }

    // Recursos indisponíveis
    public static class RecursosIndisponiveisException extends LocaisException {
        public RecursosIndisponiveisException(String recurso) {
            super("Recursos indisponíveis: " + recurso, "RECURSOS_INDISPONIVEIS");
        }
    }

    // Horário de funcionamento excedido
    public static class HorarioFuncionamentoExcedidoException extends LocaisException {
        public HorarioFuncionamentoExcedidoException(String horario) {
            super("Horário de funcionamento excedido: " + horario, "HORARIO_FUNCIONAMENTO_EXCEDIDO");
        }
    }

    // Espaço já criado - não criar igual
    public static class EspacoJaCriadoException extends LocaisException {
        public EspacoJaCriadoException(String espaco) {
            super("Espaço já criado: " + espaco, "ESPAÇO_JA_CRIADO");
        }
    }

     // Tipo de espaço inválido
    public static class TipoEspacoInvalidoException extends LocaisException {
        public TipoEspacoInvalidoException(String tipo) {
            super("Tipo de espaço inválido: " + tipo, "TIPO_ESPACO_INVALIDO");
        }
    }