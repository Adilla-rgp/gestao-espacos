package database; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // URL do banco de dados
    private static final String URL = "jdbc:sqlite:resources/gestao_espacos.db";

    // Método que abre a conexão com o banco de dados. Quando um DAO precisar conversar com o banco, chamar este método.
    public static Connection getConnection() throws SQLException {

        // Cria uma conexão 
        Connection conn = DriverManager.getConnection(URL);

        return conn;
    }
}
