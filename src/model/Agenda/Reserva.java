package model.Agenda;
package model.locais.*;

public class Reserva {
    private String nome;
    private String descricao;
    private Horario horario;
    private Local local;

    //metodos construtores

    public Reserva(String nome, String descricao, Horario horario,  Local local){
        this.nome = nome;
        this.descricao = descricao;
        this.horario = horario;
        this.local = local;
    }

    public Reserva(String nome, Horario horario){
        this.nome = nome;
        this.descricao = null;
        this.horario = horario;
    }

    //getters

    public String getNome(){
        return this.nome;
    }

    public String getDescricao(){
        return this.descricao;
    }

    // Retorna o horário de início
    public String getHorarioInicio() {
        return horario.getInicio().toString();
    }

    // Retorna o horário de fim
    public String getHorarioFim() {
        return horario.getFim().toString();
    }

    public String getEspaco() {
        return local.getNome();
    }

    //setters

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public void setHorario(Horario horario){
        this.horario = horario;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    // Verifica se esta reserva conflita com outra
    public boolean conflita(Reserva outro) {
        return this.horario == outro.horario;
    }
}   


