package model.agenda;
import java.util.ArrayList;
import java.util.List;

public class AgendaDia {
    private List<Reserva> ReservasDia;

    //metodo construtor
    public AgendaDia(){
        ReservasDia = new ArrayList<>();
    }

    public boolean adicionarReservaDia(Reserva novaReserva){
        for(Reserva atual: ReservasDia){
            if(novaReserva.conflita(atual)){
                return false;
            }
        }
        ReservasDia.add(novaReserva);
        return true;
    }

    public List<Reserva> getReservas(){
        return this.ReservasDia;
    }

    public boolean removerReserva(Reserva r){
        return ReservasDia.remove(r);
    }  
}