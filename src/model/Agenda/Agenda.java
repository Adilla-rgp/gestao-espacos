package model.agenda;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Agenda {
    private Map<LocalDate, AgendaDia> dias;

    //metodo construtor
    public Agenda(){
        dias = new HashMap<>();
    }

    //tenta adicionar uma reserva a um determinado dia
    public boolean adicionarReserva(LocalDate data, Reserva c){

        if(data.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("A data a ser reservada não deve ser no passado.");
        }
        AgendaDia dia = dias.getOrDefault(data, new AgendaDia());
        boolean status = dia.adicionarReservaDia(c);
        dias.put(data, dia);
        return status;
    }

    //retorna a agenda de um dia especificado
    public AgendaDia getAgendaDia(LocalDate data){
        AgendaDia dia = dias.get(data);
        if(dia == null){
            dia = new AgendaDia();
            dias.put(data, dia);
        }
        return dia;
    }
}
