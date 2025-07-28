package model.locais;

public class Auditorio extends Local {

    private boolean possuiSistemaDeSom;
    private boolean possuiPalco;

    // Construtor para novos objetos
    public Auditorio(String nome, String descricao, String status, int capacidade, boolean possuiSistemaDeSom, boolean possuiPalco){
        super(nome, descricao, status, capacidade);
        this.possuiSistemaDeSom = possuiSistemaDeSom;
        this.possuiPalco = possuiPalco;
    }

    // Construtor 
    public Auditorio(int id, String nome, String descricao, String status, int capacidade, boolean possuiSistemaDeSom, boolean possuiPalco){
        super(id, nome, descricao, status, capacidade);
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
