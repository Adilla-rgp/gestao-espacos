package model.enums;

public enum TipoStatusReserva {
    AGENDADA("Agendada"),
    EM_ANDAMENTO("Em Andamento"),
    FINALIZADA("Confirmada"),
    CANCELADA("Cancelada");

    private final String descricao;

    TipoStatusReserva(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

    public static TipoStatusReserva fromDescricao(String descricao) {
        for (TipoStatusReserva tipo : TipoStatusReserva.values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Descrição inválida: " + descricao);
    }
}
