package model.locais;
import java.util.Set;
import model.Agenda.Agenda;

public abstract class Local {
    protected String nome;
    protected String descricao;
    protected Agenda agenda;
    protected String status;
    protected int capacidade;
    protected static final Set<String> STATUS_VALIDOS = Set.of("Ativo", "Inativo", "Em Uso");

    //metodo construtor

    public Local(String nome, String descricao, String status, int capacidade){
        setNome(nome);
        this.descricao = descricao;
        this.agenda = new Agenda();
        setStatus(status);
        setCapacidade(capacidade);

    }

    //getters

    public String getNome(){
        return this.nome;
    }
    
    public String getDescricao(){
        return this.descricao;
    }

    public Agenda getAgenda(){
        return this.agenda;
    }

    public String getStatus(){
        return this.status;
    }

    public int getCapacidade(){
        return this.capacidade;
    }

    //setters

    public void setCapacidade(int capacidade){
        if(capacidade <= 0){
            throw new IllegalArgumentException("A capacidade do local deve ser maior que 0");
        }
        this.capacidade = capacidade;
    }

    public void setStatus(String status) {
        if (STATUS_VALIDOS.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Status indisponível");
        }
    }

    public void setNome(String nome){
        if(nome == null){
            throw new IllegalArgumentException("O nome da sala não deve ser vazio");
        }
        this.nome = nome;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
}
