package model.entities.agenda;
import  model.entities.locais.*;
import model.enums.TipoStatusReserva;

import java.time.LocalDate;


public class Reserva {
    private int idReserva;           
    private int idUsuario;     // FK 
    private int idEspaco;      // FK
    private String nome;
    private String descricao;
    private Horario horario;
    private Local local;
    private LocalDate data;    
    private TipoStatusReserva status;     


    // Construtor vazio
    public Reserva() {}

    // Construtor completo 
    public Reserva(int idUsuario, int idEspaco, String nome, String descricao,
                   Horario horario, LocalDate data, String status) {
        this.idUsuario = idUsuario;
        this.idEspaco = idEspaco;
        this.nome = nome;
        this.descricao = descricao;
        this.horario = horario;
        this.data = data;
        this.status = TipoStatusReserva.fromDescricao(status);
        this.local = null;
    }

    // Construtores para lógica sem banco
    public Reserva(String nome, String descricao, Horario horario, Local local){
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

    // getters 

    public String getIdString(){
        return "a";
    }
    public int getId() {
        return idReserva;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public int getIdEspaco() {
        return idEspaco;
    }
    public String getNome(){
        return this.nome;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public Horario getHorario() {
        return horario;
    }
    public LocalDate getData() {
        return data;
    }
    public String getStatus() {
        return status.getDescricao();
    }
    // retorna o horário de início e fim usando o objeto horario
    public String getHorarioInicio() {
        return horario != null ? horario.getInicio().toString() : null;
    }

    public String getHorarioFim() {
        return horario != null ? horario.getFim().toString() : null;
    }

    public String getEspacoNome() {
        if(local != null){
            return local.getNome();
        }
        return "INDISPONIVEL";
    }

    // setters
    public void setId(int idReserva) {
        this.idReserva = idReserva;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdEspaco(int idEspaco) {
        this.idEspaco = idEspaco;
    }

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

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setStatus(String status) {
        this.status = TipoStatusReserva.fromDescricao(status);
    }

    // verifica a reserva conflita com outra
    public boolean conflita(Reserva outro) {
        return this.horario == outro.horario;
    }

    public String toStringHorario(){
        return getHorarioInicio() + " --> " + getHorarioFim();
    }
}
