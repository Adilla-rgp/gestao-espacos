package model.locais;

public class Sala extends Local {
    private int quantProjetor;
    private int quantArCondionado;


    // Construtor para novos objetos
    public Sala(String nome, String descricao, String status, int capacidade, int quantProjetor, int quantArCondionado) {
        super(nome, descricao, status, capacidade);
        setQuantProjetor(quantProjetor);
        setQuantArcondicionado(quantArCondionado);
    }

    // Construtor 
    public Sala(int id, String nome, String descricao, String status, int capacidade, int quantProjetor, int quantArCondionado) {
        super(id, nome, descricao, status, capacidade);
        setQuantProjetor(quantProjetor);
        setQuantArcondicionado(quantArCondionado);
    }

    //getters

    public int getQuantProjetor(){
        return this.quantProjetor;
    }

    public int getQuantArCondicionado(){
        return this.quantArCondionado;
    }

    //setters

    public void setQuantProjetor(int quantidadeProjetor){
        if(quantidadeProjetor < 0){
            throw new IllegalArgumentException("A quantidade de projetores deve ser maior ou igual a zero");
        }
        this.quantProjetor = quantidadeProjetor;
    }

    public void setQuantArcondicionado(int quantArCondionado){
        if(quantArCondionado < 0){
            throw new IllegalArgumentException("A quantidade de Ar(es)-condicionado(s) deve ser maior ou igual a 0");
        }
        this.quantArCondionado= quantArCondionado;
    }

}
