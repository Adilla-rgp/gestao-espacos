package model.agenda;
import java.time.LocalTime;

public enum Horario {
    HORARIO_07_08("07:00","08:00"),
    HORARIO_08_09("08:00", "09:00"),
    HORARIO_09_10("09:00", "10:00"),
    HORARIO_10_11("10:00", "11:00"),
    HORARIO_11_12("11:00","12:00"),
    HORARIO_14_15("14:00", "15:00"),
    HORARIO_15_16("15:00", "16,00"),
    HORARIO_16_17("16:00", "17:00"),
    HORARIO_17_18("17:00", "18:00"),
    HORARIO_18_19("18:00", "19:00");

    private final LocalTime inicio;
    private final LocalTime fim;

    Horario(String inicio, String fim){
        this.inicio = LocalTime.parse(inicio);
        this.fim = LocalTime.parse(fim);
    }

    public LocalTime getInicio() {
        return inicio;
    }

    public LocalTime getFim() {
        return fim;
    }
}
