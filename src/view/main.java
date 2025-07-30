package view;

import view.*;
import controller.app.AplicacaoController;
import controller.autenticacao.SenhaUtils;
import database.DatabaseInitializer;
import controller.*;

public class main {
    public static void main(String[] args) {
        AtributosEspacoView tela = new AtributosEspacoView("Auditorio");
        tela.setVisible(true);
    }
}
