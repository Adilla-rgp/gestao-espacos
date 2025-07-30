package model.dao;

import database.ConnectionFactory;
import model.entities.predios.Predio;
import model.entities.predios.NucleoEsportivo;
import model.entities.predios.UnidadeFisica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UnidadeFisicaDAO {

    // CREATE: insere uma unidade física (prédio ou núcleo) - retorna o id
    public static int inserir(UnidadeFisica unidade, String tipo) {

        String sqlVerificar = "SELECT COUNT(*) FROM unidade_fisica WHERE nome = ?"; // conta quantas unidades possuem o mesmo nome
        String sqlInserir = "INSERT INTO unidade_fisica (nome, descricao, tipo) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection()) {

            // Verifica se já existe uma unidade com o mesmo nome
            try (PreparedStatement stmtVerificarIgual = conn.prepareStatement(sqlVerificar)) {

                stmtVerificarIgual.setString(1, unidade.getNome());
                ResultSet rs = stmtVerificarIgual.executeQuery();


                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("Já existe uma unidade física com o nome: " + unidade.getNome());
                    return -1; 
                }
            }

            // não existe, insere unidade
            try (PreparedStatement stmt = conn.prepareStatement(sqlInserir, Statement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, unidade.getNome());
                stmt.setString(2, unidade.getDescricao());
                stmt.setString(3, tipo);

                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas == 0) {
                    throw new SQLException("Falha ao inserir unidade física, nenhuma linha afetada.");
                }

                try (ResultSet chavesGeradas = stmt.getGeneratedKeys()) {
                    if (chavesGeradas.next()) {
                        int idUnidade = chavesGeradas.getInt(1);
                        System.out.println("Unidade inserida com ID: " + idUnidade);
                        return idUnidade;
                    } else {
                        throw new SQLException("Falha ao obter ID da unidade inserida.");
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inserir unidade física: " + e.getMessage());
        }
        return -1; // erro
    }


    // READ: busca pelo id
    public UnidadeFisica buscarPorId(int idUnidade) {
        String sql = "SELECT * FROM unidade_fisica WHERE id_unidade = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUnidade);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                UnidadeFisica u = mapearUnidade(rs);
                u.setIdUnidade(rs.getInt("id_unidade")); 
                return u;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar unidade física: " + e.getMessage());
        }
        return null;
    }

    // READ: lista todas as unidades
    public static List<UnidadeFisica> listarTodas() {
        List<UnidadeFisica> lista = new ArrayList<>();
        String sql = "SELECT * FROM unidade_fisica";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UnidadeFisica unidade = mapearUnidade(rs);
                unidade.setIdUnidade(rs.getInt("id_unidade"));  
                lista.add(unidade);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar unidades físicas: " + e.getMessage());
        }
        return lista;
    }

    // UPDATE: atualiza unidade
    public void atualizar(int idUnidade, UnidadeFisica unidade) {
        String sql = "UPDATE unidade_fisica SET nome = ?, descricao = ? WHERE id_unidade = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, unidade.getNome());
            stmt.setString(2, unidade.getDescricao());
            stmt.setInt(3, idUnidade);

            stmt.executeUpdate();
            System.out.println("Unidade física atualizada: " + unidade.getNome());

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar unidade física: " + e.getMessage());
        }
    }

    // DELETE: deleta unidade
    public void deletar(int idUnidade) {
        String sql = "DELETE FROM unidade_fisica WHERE id_unidade = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUnidade);
            stmt.executeUpdate();
            System.out.println("Unidade física removida ID: " + idUnidade);

        } catch (SQLException e) {
            System.err.println("Erro ao deletar unidade física: " + e.getMessage());
        }
    }

    // mapeando ResultSet para o tipo correto
    private static UnidadeFisica mapearUnidade(ResultSet rs) throws SQLException {
        String tipo = rs.getString("tipo");
        String nome = rs.getString("nome");
        String descricao = rs.getString("descricao");
        int idUnidade = rs.getInt("id_unidade");

        switch (tipo) {
            case "Predio":
                return new Predio(idUnidade, nome, descricao);  
            case "Nucleo":
                return new NucleoEsportivo(idUnidade, nome, descricao);  
            default:
                throw new SQLException("Tipo de unidade física desconhecido: " + tipo);
        }
    }
}
