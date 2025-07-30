package model.dto;

public class EspacoDTO {
    private String nome;
    private String tipo;
    private String descricao;
    private String capacidade;
    private String unidadeFisica;

    public EspacoDTO() {
    }

    public EspacoDTO(String nome, String tipo, String descricao, String capacidade, String unidadeFisica) {
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.capacidade = capacidade;
        this.unidadeFisica = unidadeFisica;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(String capacidade) {
        this.capacidade = capacidade;
    }

    public String getUnidadeFisica(){
        return unidadeFisica;
    }

    public void setUnidadeFisica(String unidadeFisica){
        this.unidadeFisica = unidadeFisica;
    }
}
