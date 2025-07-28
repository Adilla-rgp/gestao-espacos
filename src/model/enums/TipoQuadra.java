package model.enums;

public enum TipoQuadra{
    BASQUETE("Basquete"),
    FUTSAL("Futsal"),
    HANDEBOL("Handebol"),
    VOLE("Vôlei"),
    TENIS("Tênis"),
    POLIESPORTIVA("Poliesportiva");

    private final String descricao;

    TipoQuadra(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

    public static TipoQuadra fromDescricao(String descricao) {
        for (TipoQuadra tipo : TipoQuadra.values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Descrição inválida: " + descricao);
    }
}