package app;
import view.*;

import java.net.URI;
import java.time.LocalDate;

import javax.sound.midi.SysexMessage;

import controller.app.AplicacaoController;
import controller.autenticacao.SenhaUtils;
import database.DatabaseInitializer;
import model.dao.*;
import model.entities.agenda.Horario;
import model.entities.agenda.Reserva;
import model.entities.locais.*;
import model.enums.TipoQuadra;;

public class Main {
     public static void main(String[] args) {
        DatabaseInitializer.init();
        new AplicacaoController();
        System.out.println(SenhaUtils.gerarHash("adm"));
        System.out.println(SenhaUtils.gerarHash("ADM"));

    }
}
