package model.dao;

import database.ConnectionFactory;
import model.entities.locais.*;
import model.enums.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspacoDAO {

    // insere espaço e dados da subclasse
    public int inserirEspaco(Local local, int idUnidade) throws SQLException {
        String sqlInserirEspaco = "INSERT INTO espaco (id_unidade, nome, descricao, status, capacidade) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtEspaco = conn.prepareStatement(sqlInserirEspaco, Statement.RETURN_GENERATED_KEYS)) {

                stmtEspaco.setInt(1, idUnidade);
                stmtEspaco.setString(2, local.getNome());
                stmtEspaco.setString(3, local.getDescricao());
                stmtEspaco.setString(4, local.getStatus());
                stmtEspaco.setInt(5, local.getCapacidade());

                int affectedRows = stmtEspaco.executeUpdate();
                if (affectedRows == 0) {
                    conn.rollback();
                    throw new SQLException("Falha ao inserir espaço, nenhuma linha afetada.");
                }

                try (ResultSet generatedKeys = stmtEspaco.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idEspaco = generatedKeys.getInt(1);
                        inserirDadosSubclasse(conn, idEspaco, local);

                        conn.commit();
                        System.out.println("Espaço inserido com sucesso: " + local.getNome());
                        return idEspaco;  
                    } else {
                        conn.rollback();
                        throw new SQLException("Falha ao obter ID do espaço inserido.");
                    }
                }
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    // insere os dados da subclasse em sua tabela específica
    private void inserirDadosSubclasse(Connection conn, int idEspaco, Local local) throws SQLException {
        String sqlSubclasse = null;
        PreparedStatement stmtSubclasse = null;

        if (local instanceof Sala) {
            Sala sala = (Sala) local;
            sqlSubclasse = "INSERT INTO sala (id_espaco, quant_projetor, quant_ar_condicionado) VALUES (?, ?, ?)";
            stmtSubclasse = conn.prepareStatement(sqlSubclasse);
            stmtSubclasse.setInt(1, idEspaco);
            stmtSubclasse.setInt(2, sala.getQuantProjetor());
            stmtSubclasse.setInt(3, sala.getQuantArCondicionado());

        } else if (local instanceof Laboratorio) {
            Laboratorio lab = (Laboratorio) local;
            sqlSubclasse = "INSERT INTO laboratorio (id_espaco, quant_equipamentos, tipo) VALUES (?, ?, ?)";
            stmtSubclasse = conn.prepareStatement(sqlSubclasse);
            stmtSubclasse.setInt(1, idEspaco);
            stmtSubclasse.setInt(2, lab.getQuantEquipamentos());
            stmtSubclasse.setString(3, lab.getTipoDeLaboratorio().getDescricao());

        } else if (local instanceof Auditorio) {
            Auditorio aud = (Auditorio) local;
            sqlSubclasse = "INSERT INTO auditorio (id_espaco, possui_sistema_som, possui_palco) VALUES (?, ?, ?)";
            stmtSubclasse = conn.prepareStatement(sqlSubclasse);
            stmtSubclasse.setInt(1, idEspaco);
            stmtSubclasse.setBoolean(2, aud.getPossuiSistemaDeSom());
            stmtSubclasse.setBoolean(3, aud.getPossuiPalco());

        } else if (local instanceof Quadra) {
            Quadra quadra = (Quadra) local;
            sqlSubclasse = "INSERT INTO quadra (id_espaco, tipo, eh_coberta, possui_iluminacao) VALUES (?, ?, ?, ?)";
            stmtSubclasse = conn.prepareStatement(sqlSubclasse);
            stmtSubclasse.setInt(1, idEspaco);
            stmtSubclasse.setString(2, quadra.getTipoDeQuadra().getDescricao());
            stmtSubclasse.setBoolean(3, quadra.getEhCoberta());
            stmtSubclasse.setBoolean(4, quadra.getPossuiIluminacao());

        } else if (local instanceof SalaReuniao) {
            SalaReuniao sr = (SalaReuniao) local;
            sqlSubclasse = "INSERT INTO sala_reuniao (id_espaco, quant_projetor, possui_videoconferencia, possui_sistema_som) VALUES (?, ?, ?, ?)";
            stmtSubclasse = conn.prepareStatement(sqlSubclasse);
            stmtSubclasse.setInt(1, idEspaco);
            stmtSubclasse.setInt(2, sr.getQuantProjetor());
            stmtSubclasse.setBoolean(3, sr.getPossuiVideoconferencia());
            stmtSubclasse.setBoolean(4, sr.getPossuiSistemaDeSom());

        } else if (local instanceof Campo) {
            Campo campo = (Campo) local;
            sqlSubclasse = "INSERT INTO campo (id_espaco, possui_iluminacao, possui_vestiario) VALUES (?, ?, ?)";
            stmtSubclasse = conn.prepareStatement(sqlSubclasse);
            stmtSubclasse.setInt(1, idEspaco);
            stmtSubclasse.setBoolean(2, campo.getPossuiIluminacao());
            stmtSubclasse.setBoolean(3, campo.getPossuiVestiario());

        } else {
            throw new SQLException("Tipo de espaço desconhecido: " + local.getClass().getSimpleName());
        }

        stmtSubclasse.executeUpdate();
        stmtSubclasse.close();
    }

    public Local buscarPorId(int idEspaco) throws SQLException {
        String sql = "SELECT * FROM espaco WHERE id_espaco = ?";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEspaco);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // dados base
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                String status = rs.getString("status");
                int capacidade = rs.getInt("capacidade");

                // detectando a subclasse
                return carregarSubclasse(conn, idEspaco, nome, descricao, status, capacidade);
            }
        }
        return null;
    }

    // carrega a tabela filha e retorna a subclasse correspondente
    private Local carregarSubclasse(Connection conn, int idEspaco, String nome, String descricao, String status, int capacidade) throws SQLException {

        // Sala
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sala WHERE id_espaco = ?")) {
            stmt.setInt(1, idEspaco);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Sala s = new Sala(idEspaco, nome, descricao, status, capacidade,
                        rs.getInt("quant_projetor"),
                        rs.getInt("quant_ar_condicionado"));
                return s;
            }
        }

        // Laboratório
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM laboratorio WHERE id_espaco = ?")) {
            stmt.setInt(1, idEspaco);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Laboratorio l = new Laboratorio(idEspaco, nome, descricao, status, capacidade,
                        rs.getInt("quant_equipamentos"),
                        TipoLaboratorio.fromDescricao(rs.getString("tipo")));
                return l;
            }
        }

        // Auditorio
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM auditorio WHERE id_espaco = ?")) {
            stmt.setInt(1, idEspaco);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Auditorio a = new Auditorio(idEspaco, nome, descricao, status, capacidade,
                        rs.getBoolean("possui_sistema_som"),
                        rs.getBoolean("possui_palco"));
                return a;
            }
        }

        // Quadra
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM quadra WHERE id_espaco = ?")) {
            stmt.setInt(1, idEspaco);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Quadra q = new Quadra(idEspaco, nome, descricao, status, capacidade,
                        TipoQuadra.fromDescricao(rs.getString("tipo")),
                        rs.getBoolean("eh_coberta"),
                        rs.getBoolean("possui_iluminacao"));
                return q;
            }
        }

        // Sala de Reunião
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sala_reuniao WHERE id_espaco = ?")) {
            stmt.setInt(1, idEspaco);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                SalaReuniao sr = new SalaReuniao(idEspaco, nome, descricao, status, capacidade,
                        rs.getInt("quant_projetor"),
                        rs.getBoolean("possui_videoconferencia"),
                        rs.getBoolean("possui_sistema_som"));
                return sr;
            }
        }

        return null;
    }

    public List<Local> listarTodos() throws SQLException {
        List<Local> lista = new ArrayList<>();
        String sql = "SELECT id_espaco FROM espaco";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idEspaco = rs.getInt("id_espaco");
                Local local = buscarPorId(idEspaco); 
                if (local != null) {
                    lista.add(local);
                }
            }
        }
        return lista;
    }

    public List<Local> listarPorStatus(String status) throws SQLException {
        List<Local> lista = new ArrayList<>();
        String sql = "SELECT id_espaco FROM espaco WHERE status = ?";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idEspaco = rs.getInt("id_espaco");
                Local local = buscarPorId(idEspaco);
                if (local != null) {
                    lista.add(local);
                }
            }
        }
        return lista;
    }

    public void atualizarEspaco(Local local, int idEspaco) throws SQLException {
        String sqlAtualizarEspaco = "UPDATE espaco SET nome = ?, descricao = ?, status = ?, capacidade = ? WHERE id_espaco = ?";

        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sqlAtualizarEspaco)) {
                stmt.setString(1, local.getNome());
                stmt.setString(2, local.getDescricao());
                stmt.setString(3, local.getStatus());
                stmt.setInt(4, local.getCapacidade());
                stmt.setInt(5, idEspaco);

                int affected = stmt.executeUpdate();
                if (affected == 0) {
                    conn.rollback();
                    throw new SQLException("Nenhum espaço encontrado para atualizar.");
                }

                atualizarDadosSubclasse(conn, idEspaco, local);

                conn.commit();
                System.out.println("Espaço atualizado com sucesso: " + local.getNome());
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    private void atualizarDadosSubclasse(Connection conn, int idEspaco, Local local) throws SQLException {
        String sql = null;
        PreparedStatement stmt = null;

        if (local instanceof Sala) {
            Sala s = (Sala) local;
            sql = "UPDATE sala SET quant_projetor = ?, quant_ar_condicionado = ? WHERE id_espaco = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, s.getQuantProjetor());
            stmt.setInt(2, s.getQuantArCondicionado());
            stmt.setInt(3, idEspaco);

        } else if (local instanceof Laboratorio) {
            Laboratorio l = (Laboratorio) local;
            sql = "UPDATE laboratorio SET quant_equipamentos = ?, tipo = ? WHERE id_espaco = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, l.getQuantEquipamentos());
            stmt.setString(2, l.getTipoDeLaboratorio().getDescricao());
            stmt.setInt(3, idEspaco);

        } else if (local instanceof Auditorio) {
            Auditorio a = (Auditorio) local;
            sql = "UPDATE auditorio SET possui_sistema_som = ?, possui_palco = ? WHERE id_espaco = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, a.getPossuiSistemaDeSom());
            stmt.setBoolean(2, a.getPossuiPalco());
            stmt.setInt(3, idEspaco);

        } else if (local instanceof Quadra) {
            Quadra q = (Quadra) local;
            sql = "UPDATE quadra SET tipo = ?, eh_coberta = ?, possui_iluminacao = ? WHERE id_espaco = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, q.getTipoDeQuadra().getDescricao());
            stmt.setBoolean(2, q.getEhCoberta());
            stmt.setBoolean(3, q.getPossuiIluminacao());
            stmt.setInt(4, idEspaco);

        } else if (local instanceof SalaReuniao) {
            SalaReuniao sr = (SalaReuniao) local;
            sql = "UPDATE sala_reuniao SET quant_projetor = ?, possui_videoconferencia = ?, possui_sistema_som = ? WHERE id_espaco = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, sr.getQuantProjetor());
            stmt.setBoolean(2, sr.getPossuiVideoconferencia());
            stmt.setBoolean(3, sr.getPossuiSistemaDeSom());
            stmt.setInt(4, idEspaco);

        } else {
            throw new SQLException("Tipo de espaço desconhecido: " + local.getClass().getSimpleName());
        }

        stmt.executeUpdate();
        stmt.close();
    }

    public void deletarEspaco(int idEspaco) throws SQLException {
        String sql = "DELETE FROM espaco WHERE id_espaco = ?";
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEspaco);
            stmt.executeUpdate();
            System.out.println("Espaço removido ID: " + idEspaco);
        }
    }

}
