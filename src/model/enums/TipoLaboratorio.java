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
}