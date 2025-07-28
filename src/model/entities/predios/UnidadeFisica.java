package model.entities.predios;

public abstract class UnidadeFisica {
    protected int idUnidade;   
    protected String nome;
    protected String descricao;

    // construtor para nova unidade
    public UnidadeFisica(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    // construtor completo
    public UnidadeFisica(int idUnidade, String nome, String descricao){
        this.idUnidade = idUnidade;
        this.nome = nome;
        this.descricao = descricao;
    }

    // getters e setters
    public int getIdUnidade() {
        return idUnidade;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public String getDescricao(){
        return this.descricao;
    }

    public void setIdUnidade(int idUnidade) {
        this.idUnidade = idUnidade;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
}
