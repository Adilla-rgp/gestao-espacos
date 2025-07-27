package model.enums;

public enum TipoEspaco {
    SALA_AULA("Sala de Aula"),
    LABORATORIO("Laboratório"),
    AUDITORIO("Auditório"),
    QUADRA("Quadra"),
    SALA_REUNIAO("Sala de Reunião"),
    CAMPO("Campo");

    private final String descricao;

    TipoEspaco(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
