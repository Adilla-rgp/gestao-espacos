package model.enums;

public enum TipoLaboratorio{
    INFORMATICA("Informática"),
    QUIMICA("Química"),
    BIOLOGIA("Biologia"),
    FISICA("Física");

    private final String descricao;

    TipoLaboratorio(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() { return descricao; }

    @Override
    public String toString() {return descricao; }

    public static TipoLaboratorio fromDescricao(String descricao) {
        for (TipoLaboratorio tipo : TipoLaboratorio.values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Descrição inválida: " + descricao);
    }
}