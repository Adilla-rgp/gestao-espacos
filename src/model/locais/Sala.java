package model.locais;

public class Sala extends Local {
    private int quantProjetor;
    private int quantArCondicionado;


    //metodo construtor

    public Sala(String nome, String descricao, String status, int capacidade, int quantProjetor, int quantArCondicionado){
        super(nome, descricao, status, capacidade);
        setQuantProjetor(quantProjetor);
        setQuantArCondicionado(quantArCondicionado);

    }

    //getters

    public int getQuantProjetor(){
        return this.quantProjetor;
    }

    public int getQuantArCondicionado(){
        return this.quantArCondicionado;
    }

    //setters

    public void setQuantProjetor(int quantidadeProjetor){
        if(quantidadeProjetor < 0){
            throw new IllegalArgumentException("A quantidade de projetores deve ser maior ou igual a zero");
        }
        this.quantProjetor = quantidadeProjetor;
    }

    public void setQuantArCondicionado(int quantArCondicionado){
        if(quantArCondicionado < 0){
            throw new IllegalArgumentException("A quantidade de Ar(es)-condicionado(s) deve ser maior ou igual a 0");
        }
        this.quantArCondicionado = quantArCondicionado;
    }

    /*Métodos para as regras de negócios: */

    public boolean temProjetor()
    {
        return quantProjetor > 0;
    }

    public boolean capacidadeAceita(int pessoasEsperadas) {
        return pessoasEsperadas <= getCapacidade();
    }


    public String toString() {
        return String.format(
            "Sala: %s, Descrição: %s, Status: %s, Capacidade: %d, Projetores: %d, Ar-condicionado: %d",
            getNome(),
            getDescricao(),
            getStatus(),
            getCapacidade(),
            quantProjetor,
            quantArCondicionado
        );
    }

}
