package app;
import database.DatabaseInitializer;

// Inicializa o Banco de Dados
public class Main {
    public static void main(String[] args) {
        // Inicializa banco e cria tabelas
        DatabaseInitializer.init();

        System.out.println("Sistema iniciado.");
    }
}