package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionFactory;
import model.entities.usuario.Usuario;

public class UsuarioDAO {

    // CREATE: Insere um novo usuário na tabela usuario.

    public void inserirUsuario(Usuario usuario) {
        
        // comando sql com placeholders (?) para os parâmetros
        String sqlInserir = "INSERT INTO usuario (nome, email, senha_hash, is_adm, ativo) VALUES (?, ?, ?, ?, ?)";

        // tenta abrir conexão e preparar o comando
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmtInserir = conn.prepareStatement(sqlInserir)) {

            // preenchimento dos placeholders
            stmtInserir.setString(1, usuario.getNome());
            stmtInserir.setString(2, usuario.getEmail());
            stmtInserir.setString(3, usuario.getSenhaHash());
            stmtInserir.setBoolean(4, usuario.getStatusAdm());
            stmtInserir.setBoolean(5, true); // usuário sempre começa ativo

            // executa no banco
            stmtInserir.executeUpdate();
            System.out.println("Usuário inserido com sucesso: " + usuario.getNome());

        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    // READ: Busca um usuário pelo email (SELECT com WHERE).
    
    public Usuario buscarPorEmail(String email) {

        String sqlBuscar = "SELECT * FROM usuario WHERE email = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmtBuscar = conn.prepareStatement(sqlBuscar)) {

            stmtBuscar.setString(1, email);
            //Executa a consulta
            ResultSet rsBusca = stmtBuscar.executeQuery();

            // cria objeto Usuario a partir do ResultSet
            if (rsBusca.next()) {
                Usuario u = new Usuario(
                        rsBusca.getInt("id_usuario"),    
                        rsBusca.getString("nome"),
                        rsBusca.getString("email"),
                        rsBusca.getString("senha_hash"),
                        rsBusca.getBoolean("is_adm")
                );
                return u;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
        }

        return null; 
    }

    // READ: Lista todos os usuários.
    
    public List<Usuario> listarTodosUsuarios() {

        String sqlListar = "SELECT * FROM usuario";

        // Lista para armazenar usuario
        List<Usuario> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmtListar = conn.prepareStatement(sqlListar);
             ResultSet rsLista = stmtListar.executeQuery()) {

            while (rsLista.next()) {
                Usuario u = new Usuario(
                        rsLista.getInt("id_usuario"),
                        rsLista.getString("nome"),
                        rsLista.getString("email"),
                        rsLista.getString("senha_hash"),
                        rsLista.getBoolean("is_adm")
                );
                lista.add(u);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }

        return lista;
    }

    // UPDATE: Atualiza os dados de um usuário existente.
    
    public void atualizar(Usuario usuario) {

        String sqlAtualizar = "UPDATE usuario SET nome = ?, email = ?, senha_hash = ?, is_adm = ?, ativo = ? WHERE id_usuario = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmtAtualizar = conn.prepareStatement(sqlAtualizar)) {

            stmtAtualizar.setString(1, usuario.getNome());
            stmtAtualizar.setString(2, usuario.getEmail());
            stmtAtualizar.setString(3, usuario.getSenhaHash());
            stmtAtualizar.setBoolean(4, usuario.getStatusAdm());
            stmtAtualizar.setBoolean(5, true);
            stmtAtualizar.setInt(6, usuario.getId());

            stmtAtualizar.executeUpdate();
            System.out.println("Usuário atualizado com sucesso: " + usuario.getNome());

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    // DELETE: Remove um usuário pelo email.
    
    public void deletarPorEmail(String email) {
        String sqlDeletar = "DELETE FROM usuario WHERE email = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmtDeletar = conn.prepareStatement(sqlDeletar)) {

            stmtDeletar.setString(1, email);
            stmtDeletar.executeUpdate();
            System.out.println("Usuário removido: " + email);

        } catch (SQLException e) {
            System.err.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }
}

