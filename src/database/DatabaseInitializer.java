package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class DatabaseInitializer {

    // Caminho para o arquivo do banco de dados 
    private static final String DATABASE_URL = "jdbc:sqlite:resources/gestao_espacos.db";

    // Caminho para o script
    private static final String SCHEMA_FILE = "resources/create_schema.sql";

    // Método principal: inicializa o banco de dados 
    public static void init() {
        System.out.println("Inicializando banco de dados.");

        // Tentar abrir uma conexão com o banco de dados
        try (Connection conn = DriverManager.getConnection(DATABASE_URL)) {
            if (conn != null) {
                System.out.println("Conexão com banco de dados aberta com sucesso.");

                // lê o arquivo create_schema.sql 
                String sqlScript = lerArquivoSQL();

                // executa o conteúdo do script no banco
                executarScript(conn, sqlScript);

                System.out.println("Banco de dados inicializado com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco: " + e.getMessage());
        }
    }

    // método que lê o arquivo create_schema.sql 
    private static String lerArquivoSQL() {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(SCHEMA_FILE));
            String conteudo = new String(bytes);
            System.out.println("Arquivo de schema lido com sucesso.");
            return conteudo;
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo " + SCHEMA_FILE + ": " + e.getMessage());
            return "";
        }
    }

    // Método que executa o script SQL no banco de dados.
    private static void executarScript(Connection conn, String script) {
        try (Statement stmt = conn.createStatement()) {

            // divide comandos por ; (rodar todas ass tabelas)
            String[] comandos = script.split(";");

            for (String comando : comandos) {
                String trimmed = comando.trim();
                if (!trimmed.isEmpty()) {
                    stmt.execute(trimmed);
                    // System.out.println("Comando executado: " + trimmed);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao executar script SQL: " + e.getMessage());
        }
    }
}
