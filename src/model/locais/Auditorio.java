package model.locais;

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
}
