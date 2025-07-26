package model.locais;
import java.util.Set;

public class Quadra extends Local {
    private String tipoDeQuadra;
    private boolean ehCoberta;
    private boolean possuiIluminacao;
    private static final Set<String> TIPOS_DE_QUADRA = Set.of("Basquete", "Futsal", "Handbol", "vôlei", "tênis", "poliesportiva");

    //metodo construtor

    public Quadra(String nome,String descricao, String status, int capacidade, String tipoDeQuadra, boolean ehCoberta, boolean possuiIluminacao){
        super(nome, descricao, status, capacidade);
        setTipoDeQuadra(tipoDeQuadra);
        this.ehCoberta = ehCoberta;
        this.possuiIluminacao = possuiIluminacao;
    }

    //getters

    public String getTipoDeQuadra(){
        return this.tipoDeQuadra;
    }

    public boolean getEhCoberta(){
        return this.ehCoberta;
    }

    public boolean getPossuiIluminacao(){
        return this.possuiIluminacao;
    }

    //setters

    public void setTipoDeQuadra(String tipoDeQuadra){
        if(TIPOS_DE_QUADRA.contains(tipoDeQuadra)){
            this.tipoDeQuadra= tipoDeQuadra;
        }else{
            throw new IllegalArgumentException("O tipo de quadra informado não é permitido");
        }
    }

    public void setEhCoberta(boolean status){
        this.ehCoberta = status;
    }

    public void setPossuiIluminacao(boolean status){
        this.possuiIluminacao = status;
    }
}
