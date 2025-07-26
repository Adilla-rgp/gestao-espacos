package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
    private Usuario usuario;
    private Espaco espaco;
    private LocalDate data;
    private LocalTime hora;
    private String finalidade;
    private String status; // "Agendada", "Finalizada", "Cancelada"

    public Reserva(Usuario usuario, Espaco espaco, LocalDate data, LocalTime hora, String finalidade) {
        this.usuario = usuario;
        this.espaco = espaco;
        this.data = data;
        this.hora = hora;
        this.finalidade = finalidade;
        this.status = "Agendada";
    }

    // Getters e Setters
    public Usuario getUsuario() { return usuario; }
    public Espaco getEspaco() { return espaco; }
    public LocalDate getData() { return data; }
    public LocalTime getHora() { return hora; }
    public String getFinalidade() { return finalidade; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}