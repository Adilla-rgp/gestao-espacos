package model.entities.locais;
import model.enums.TipoEspaco;
import model.enums.TipoLaboratorio;
import java.util.Set;

public class Laboratorio extends Local {

    private int quantEquipamentos;
    private TipoLaboratorio tipoLaboratorio;

    // Construtor para novos objetos
    public Laboratorio(String nome, String descricao, String status, int capacidade, int quantEquipamentos, TipoLaboratorio tipoDeLaboratorio){
        super(nome, descricao, status, capacidade);
        setTipoDeLaboratorio(tipoLaboratorio);
        setQuantEquipamentos(quantEquipamentos);
    }

    // Construtor 
    public Laboratorio(int id, String nome, String descricao, String status, int capacidade, int quantEquipamentos, TipoLaboratorio tipoDeLaboratorio){
        super(id, nome, descricao, status, capacidade);
        setTipoDeLaboratorio(tipoDeLaboratorio);
        setQuantEquipamentos(quantEquipamentos);
    }

    //getters

    public int getQuantEquipamentos(){
        return this.quantEquipamentos;
    }

    public TipoLaboratorio getTipoDeLaboratorio() {
        return this.tipoLaboratorio;
    }

    //setters

    public void setTipoDeLaboratorio(TipoLaboratorio tipoLaboratorio) {
        if (tipoLaboratorio != null) {
            this.tipoLaboratorio = tipoLaboratorio;
        } else {
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

    public boolean equipamentosSuficientes(int quantAlunos) { 
        int quantEq = getQuantEquipamentos();
        return quantEq >= quantAlunos;
    }

    @Override
    public String toString() {
        return String.format(
            "Laboratorio -> Nome: %s, Descricao: %s, Status: %s, Capacidade: %d, Tipo: (CORRIGIR), TipoLaboratorio: %s, QuantEquipamentos: %d",
            getNome(),
            getDescricao(),
            getStatus(),
            getCapacidade(),
            tipoLaboratorio != null ? tipoLaboratorio.toString() : "N/A",
            quantEquipamentos
        );
    }
}
