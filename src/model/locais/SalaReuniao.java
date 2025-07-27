package model.locais;

public class SalaReuniao extends Local {

    private int quantProjetor;
    private boolean possuiVideoconferencia;
    private boolean possuiSistemaDeSom;

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

    public boolean getPossuiVideoconferencia(){
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

    public void setPossuiVideoconferencia(boolean status){
        this.possuiVideoconferencia = status;
    }

    public void setPossuiSistemaDeSom(boolean status){
        this.possuiSistemaDeSom = status;
    }

    //métodos para lógica de negócios
    public boolean temProjetor() { return quantProjetor > 0; }
    public boolean adequadaVideoConferencia() { return possuiVideoconferencia && temProjetor(); }

    public String toString() {
        return String.format("Sala de Reunião:\nNome: %s, capacidade: %d, projetores: %d, som: %s, videoconf: %s",
        getNome(), getCapacidade(), quantProjetor, possuiSistemaDeSom, possuiVideoconferencia);
    }
}
