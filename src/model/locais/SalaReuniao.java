package model.locais;

public class SalaReuniao extends Local{

    private int quantProjetor;
    private boolean possuiVideoconferencia;
    private boolean possuiSistemaDeSom;

    //metodo construtor

    public SalaReuniao(String nome, String descricao, String status, int capacidade, int quantidadeProjetor, boolean possuiVideoconferencia, boolean possuiSistemaDeSom){
        super(nome, descricao, status, capacidade);
        setQuantProjetor(quantidadeProjetor);
        this.possuiSistemaDeSom = possuiSistemaDeSom;
        this.possuiVideoconferencia = possuiVideoconferencia;
    }


    //getters

    public int getQuantProjetor(){
        return this.quantProjetor;
    }

    public boolean getPossuiVideoConferencia(){
        return this.possuiVideoconferencia;
    }

    public boolean getPossuiSistemaDeSom(){
        return this.possuiSistemaDeSom;
    }
    
    //setters

    public void setQuantProjetor(int quantidadeProjetor){
        if(quantidadeProjetor < 0){
            throw new IllegalArgumentException("A quantidade de projetores deve ser maior ou igual a zero");
        }
        this.quantProjetor = quantidadeProjetor;
    }

    public void serPossuiVideoConferencia(Boolean status){
        this.possuiVideoconferencia = status;
    }

    public void serPossuiSistemaDeSom(Boolean status){
        this.possuiSistemaDeSom = status;
    }
}
