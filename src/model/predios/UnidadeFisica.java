package model.predios;

public abstract class UnidadeFisica {
    protected String nome;
    protected String descricao;

    //metodo construtor
    
    public UnidadeFisica(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    //getters

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    //setters
    
    public String getNome(){
        return this.nome;
    }

    public String getDescricao(){
        return this.descricao;
    }
}
