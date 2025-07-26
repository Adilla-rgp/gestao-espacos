package model.locais;

public class Campo extends Local{

    private boolean possuiIluminacao;
    private boolean possuiVestiario;
    //metodos construtores

    public Campo(String nome, String descricao, String status, int capacidade, boolean possuiIluminacao, boolean possuiVestiario){
        super(nome, descricao, status, capacidade);
        this.possuiIluminacao = possuiIluminacao;
        this.possuiVestiario = possuiVestiario;
    }

    //getters

    public boolean getPossuiIluminacao(){
        return this.possuiIluminacao;
    }

    public boolean getPossuiVestiario(){
        return this.possuiVestiario;
    }

    //setters

    public void setPossuiIluminacao(boolean status){
        this.possuiIluminacao = status;
    }

    public void setPossuiVestiario(boolean status){
        this.possuiVestiario = status;
    }
}
