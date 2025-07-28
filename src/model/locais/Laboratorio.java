package model.locais;
import java.util.Set;

public class Laboratorio extends Local {

    private int quantEquipamentos;
    private String tipoDeLaboratorio;
    private static final Set<String> TIPOS_DE_LABORATORIO = Set.of("Informatica", "Quimica", "Biologia", "Fisica");

    // Construtor para novos objetos
    public Laboratorio(String nome, String descricao, String status, int capacidade, int quantEquipamentos, String tipoDeLaboratorio){
        super(nome, descricao, status, capacidade);
        setTipoDeLaboratorio(tipoDeLaboratorio);
        setQuantEquipamentos(quantEquipamentos);
    }
    // Construtor 
    public Laboratorio(int id, String nome, String descricao, String status, int capacidade, int quantEquipamentos, String tipoDeLaboratorio){
        super(id, nome, descricao, status, capacidade);
        setTipoDeLaboratorio(tipoDeLaboratorio);
        setQuantEquipamentos(quantEquipamentos);
    }


    //getters

    public int getQuantEquipamentos(){
        return this.quantEquipamentos;
    }

    public String getTipoDeLaboratorio(){
        return this.tipoDeLaboratorio;
    }

    //setters

    public void setTipoDeLaboratorio(String tipoDeLaboratorio){
        if(TIPOS_DE_LABORATORIO.contains(tipoDeLaboratorio)){
            this.tipoDeLaboratorio = tipoDeLaboratorio;
        }else{
            throw new IllegalArgumentException("Tipo de laboratorio indisponivel");
        }
    }

    public void setQuantEquipamentos(int quantidade){
        if(quantidade >= 0){
            this.quantEquipamentos = quantidade;
        }else{
            throw new IllegalArgumentException("O numero de equipamentos deve ser maior ou igual a zero");
        }
    }
}
