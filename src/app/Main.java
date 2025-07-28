package app;
import view.*;
import controller.app.AplicacaoController;
import database.DatabaseInitializer;
 

public class Main {
     public static void main(String[] args) {
        DatabaseInitializer.init();
        new AplicacaoController();
    }
}
