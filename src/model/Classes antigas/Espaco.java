package model.Classes antigas;

public class Espaco {
    private String nome;
    private TipoEspaco tipo;
    private int capacidade;
    private String localizacao;
    private String descricao;
    private boolean ativo;

    public Espaco(String nome, TipoEspaco tipo, int capacidade, String localizacao, String descricao) {
        this.nome = nome;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.localizacao = localizacao;
        this.descricao = descricao;
        this.ativo = true;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public TipoEspaco getTipo() { return tipo; }
    public int getCapacidade() { return capacidade; }
    public String getLocalizacao() { return localizacao; }
    public String getDescricao() { return descricao; }
    public boolean isAtivo() { return ativo; }

    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}