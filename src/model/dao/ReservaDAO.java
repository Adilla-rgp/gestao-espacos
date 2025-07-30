package model.dao;

import database.ConnectionFactory;
import model.entities.agenda.Reserva;
import model.entities.agenda.Horario;
import model.entities.locais.Local;

import java.lang.foreign.SymbolLookup;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    // CREATE: Insere nova reserva 

    public static void inserirReserva(Reserva reserva) {
        String sql = "INSERT INTO reserva (id_usuario, id_espaco, nome, descricao, data, horario_inicio, horario_fim, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, reserva.getIdUsuario());
            stmt.setInt(2, reserva.getIdEspaco());
            stmt.setString(3, reserva.getNome());
            stmt.setString(4, reserva.getDescricao());
            
            // Converter LocalDate para String no formato "yyyy-MM-dd"
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            stmt.setString(5, reserva.getData().format(dateFormatter));
            
            // Converter LocalTime para String no formato "HH:mm"
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            stmt.setString(6, reserva.getHorario().getInicio().format(timeFormatter));
            stmt.setString(7, reserva.getHorario().getFim().format(timeFormatter));
            
            stmt.setString(8, reserva.getStatus());
            
            stmt.executeUpdate();
            System.out.println("Reserva criada com sucesso: " + reserva.getNome());
        } catch (SQLException e) {
            System.err.println("Erro ao inserir reserva: " + e.getMessage());
        }
    }

    // READ: Busca uma reserva pelo id
    public static Reserva buscarPorId(int idReserva) {
        String sql = "SELECT * FROM reserva WHERE id_reserva = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReserva);

            ResultSet rs = stmt.executeQuery();

            // rs.next() move o cursor para a primeira linha de resultado
            if (rs.next()) {
                Horario horarioEncontrado = mapearHorario(rs);

                // cria objeto reserva com todos os campos
                Reserva r = new Reserva(
                        rs.getInt("id_usuario"),
                        rs.getInt("id_espaco"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        horarioEncontrado,
                        rs.getDate("data").toLocalDate(),
                        rs.getString("status")
                );
                r.setId(rs.getInt("id_reserva"));

                return r;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar reserva: " + e.getMessage());
        }
        return null;
    }

    // READ: Lista todas as reservas do banco.
    public static List<Reserva> listarTodasReservas() {
        String sql = "SELECT * FROM reserva";
        List<Reserva> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Horario horarioEncontrado = mapearHorario(rs);
                LocalDate data = LocalDate.parse(rs.getString("data"));

                Reserva r = new Reserva(
                        rs.getInt("id_usuario"),
                        rs.getInt("id_espaco"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        horarioEncontrado,
                        data,
                        rs.getString("status")
                );
                r.setId(rs.getInt("id_reserva"));
                Local localSala = EspacoDAO.buscarPorId(rs.getInt("id_usuario"));
                r.setLocal(localSala);
                

                lista.add(r);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar reservas: " + e.getMessage());
        }
        return lista;
    }

    // READ: Lista todas as reservas de um usuário específico
    public static List<Reserva> listarReservasPorUsuario(int idUsuario) {
        String sql = "SELECT * FROM reserva WHERE id_usuario = ?";
        List<Reserva> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Horario horarioEncontrado = mapearHorario(rs);
                LocalDate data = LocalDate.parse(rs.getString("data"));

                Reserva r = new Reserva(
                        rs.getInt("id_usuario"),
                        rs.getInt("id_espaco"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        horarioEncontrado,
                        data,
                        rs.getString("status")
                );
                r.setId(rs.getInt("id_reserva"));
                Local localSala = EspacoDAO.buscarPorId(rs.getInt("id_usuario"));
                r.setLocal(localSala);

                lista.add(r);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar reservas do usuário: " + e.getMessage());
        }

        return lista;
    }

    // READ: Lista reservas de um local específico em um dia específico
    public static List<Reserva> listarReservasPorDiaELocal(int idEspaco, LocalDate data) {

        String sql = "SELECT * FROM reserva WHERE id_espaco = ? AND data = ?";
        List<Reserva> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEspaco);
            stmt.setString(2, data.toString()); // LocalDate.toString() retorna "YYYY-MM-DD"

            System.out.println("Data no banco de dados: " + data);

            ResultSet rs = stmt.executeQuery();

            System.out.println("Foi buscar");
            System.out.println("ResultSet tem dados: " + rs.isBeforeFirst()); // Verifica se há dados

            System.out.println("=== DEBUG CONSULTA ===");
            System.out.println("ID Espaço buscado: " + idEspaco);
            System.out.println("Data buscada: " + Date.valueOf(data));
            System.out.println("SQL com parâmetros: " + stmt.toString());
            System.out.println("====================");


            while (rs.next()) {
                Horario horarioEncontrado = mapearHorario(rs);
                LocalDate dataReserva = LocalDate.parse(rs.getString("data"));
                System.out.println("Encontou uma");

                Reserva r = new Reserva(
                    rs.getInt("id_usuario"),
                    rs.getInt("id_espaco"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    horarioEncontrado,
                    dataReserva,
                    rs.getString("status")
                );
                r.setId(rs.getInt("id_reserva"));

                lista.add(r);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar reservas por local e data: " + e.getMessage());
        }

        return lista;
    }


    // UPDATE: Atualiza uma reserva existente.
    public static void atualizarReserva(Reserva reserva) {
        String sql = "UPDATE reserva SET nome = ?, descricao = ?, data = ?, horario_inicio = ?, horario_fim = ?, status = ? " +
                     "WHERE id_reserva = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reserva.getNome());
            stmt.setString(2, reserva.getDescricao());
            stmt.setDate(3, Date.valueOf(reserva.getData()));
            stmt.setTime(4, Time.valueOf(reserva.getHorario().getInicio()));
            stmt.setTime(5, Time.valueOf(reserva.getHorario().getFim()));
            stmt.setString(6, reserva.getStatus());
            stmt.setInt(7, reserva.getId());

            stmt.executeUpdate();
            System.out.println("Reserva atualizada: " + reserva.getNome());

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar reserva: " + e.getMessage());
        }
    }
    
    // UPDATE: altera o status de uma reserva
    public static boolean atualizarStatusReserva(int idReserva, String novoStatus) {

        String sql = "UPDATE reserva SET status = ? WHERE id_reserva = ?";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoStatus);
            stmt.setInt(2, idReserva);

            int linhasAtualizadas = stmt.executeUpdate();   // retorna quantas linhas foram atualizadas

            if (linhasAtualizadas > 0) {
                System.out.println("Status da reserva " + idReserva + " atualizado para: " + novoStatus);
                return true; // pelo menos uma linha foi alterada
            } else {
                System.out.println("Nenhuma reserva encontrada com ID: " + idReserva);
                return false; // nenhuma linha alterada
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar status da reserva: " + e.getMessage());
            return false; 
        }
    }


    // DELETE: Remove uma reserva pelo ID.
    public void deletarReserva(int idReserva) {
        String sql = "DELETE FROM reserva WHERE id_reserva = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReserva);
            stmt.executeUpdate();
            System.out.println("Reserva removida ID: " + idReserva);

        } catch (SQLException e) {
            System.err.println("Erro ao deletar reserva: " + e.getMessage());
        }
    }

    // método que mapea os horários do banco para o enum Horario
    private static Horario mapearHorario(ResultSet rs) throws SQLException {
        String inicioStr = rs.getString("horario_inicio");
        String fimStr = rs.getString("horario_fim");

        System.out.println("DEBUG - horario_inicio: " + inicioStr);
        System.out.println("DEBUG - horario_fim: " + fimStr);

        LocalTime inicio = LocalTime.parse(inicioStr);
        LocalTime fim = LocalTime.parse(fimStr);

        for (Horario h : Horario.values()) {
            if (h.getInicio().equals(inicio) && h.getFim().equals(fim)) {
                return h;
            }
        }

        throw new SQLException("Horário não encontrado para " + inicio + " - " + fim);
    }

}
