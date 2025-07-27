package model.locais;

import model.enums.TipoEspaco;

public class Auditorio extends Local {

    private boolean possuiSistemaDeSom;
    private boolean possuiPalco;

    //metodo construtor

    public Auditorio(String nome, String descricao, String status, int capacidade, boolean possuiSistemaDeSom, boolean possuiPalco){
        super(nome, descricao, status, capacidade);
        this.possuiSistemaDeSom = possuiSistemaDeSom;
        this.possuiPalco = possuiPalco;
    }

    //getters

    public boolean getPossuiSistemaDeSom(){
        return this.possuiSistemaDeSom;
    }

    public boolean getPossuiPalco(){
        return this.possuiPalco;
    }

    //setters

    public void setPossuiSistemaDeSom(boolean status){
        this.possuiSistemaDeSom = status;
    }

    public void setPossuiPalco(boolean status){
        this.possuiPalco = status;
    }

    //MÉTODOS para a lógica de negócios
    public boolean adequadoPalestras() {
        return possuiSistemaDeSom;
    }

    @Override
    public String toString() {
        return String.format(
            "Auditorio -> Nome: %s, Descricao: %s, Status: %s, Capacidade: %d, Sistema de Som: %s, Palco: %s",
            getNome(),
            getDescricao(),
            getStatus(),
            getCapacidade(),
            possuiSistemaDeSom ? "Sim" : "Nao",
            possuiPalco ? "Sim" : "Nao"
        );
    }
}
