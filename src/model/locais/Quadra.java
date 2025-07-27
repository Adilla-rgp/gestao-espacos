package model.locais;
import model.enums.TipoQuadra;

import java.util.Set;


public class Quadra extends Local {
    private TipoQuadra tipoQuadra; 
    private boolean ehCoberta;
    private boolean possuiIluminacao;

    //metodo construtor

    public Quadra(String nome,String descricao, String status, int capacidade, TipoQuadra tipoQuadra, boolean ehCoberta, boolean possuiIluminacao){
        super(nome, descricao, status, capacidade);
        this.tipoQuadra = tipoQuadra;
        this.ehCoberta = ehCoberta;
        this.possuiIluminacao = possuiIluminacao;
    }

    //getters

    public TipoQuadra getTipoDeQuadra(){
        return this.tipoQuadra;
    }

    public boolean getEhCoberta(){
        return this.ehCoberta;
    }

    public boolean getPossuiIluminacao(){
        return this.possuiIluminacao;
    }

    //setters

    public void setTipoDeQuadra(TipoQuadra tipoQuadra){
        this.tipoQuadra = tipoQuadra;
    }

    public void setEhCoberta(boolean status){
        this.ehCoberta = status;
    }

    public void setPossuiIluminacao(boolean status){
        this.possuiIluminacao = status;
    }

    //Métodos para a regra de negócios
    public boolean permiteUsoNoturno() {
        return possuiIluminacao;
    }

    public boolean adequadaPara(TipoQuadra esporte) {
        return this.tipoQuadra == esporte || this.tipoQuadra == TipoQuadra.POLIESPORTIVA;
    }

    @Override
    public String toString() {
        return String.format(
            "Quadra{nome='%s', descricao='%s', status='%s', capacidade=%d, tipoQuadra=%s, ehCoberta=%b, possuiIluminacao=%b}",
            getNome(), getDescricao(), getStatus(), getCapacidade(), tipoQuadra, ehCoberta, possuiIluminacao
        );
    }
}
