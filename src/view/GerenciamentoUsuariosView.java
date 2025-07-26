package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GerenciamentoUsuariosView extends JFrame {
    private JTable tabela;
    private JButton novoButton;
    private JButton editarButton;
    private JButton desativarButton;

    public GerenciamentoUsuariosView() {
        setTitle("Gerenciamento de Usuários");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tabela configurada com borda e rolagem
        tabela = new JTable();
        tabela.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createTitledBorder("👥 Lista de Usuários"));

        // Painel de botões com layout e espaçamento
        novoButton = new JButton("Novo Usuário");
        editarButton = new JButton("Editar Usuário");
        desativarButton = new JButton("Desativar Usuário");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        buttonPanel.add(novoButton);
        buttonPanel.add(editarButton);
        buttonPanel.add(desativarButton);

        // Layout principal com margens
        setLayout(new BorderLayout(10, 10));
        add(scroll, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Getters
    public JTable getTabela() { return tabela; }
    public JButton getNovoButton() { return novoButton; }
    public JButton getEditarButton() { return editarButton; }
    public JButton getDesativarButton() { return desativarButton; }

    // Atualizar modelo da tabela
    public void setTabelaModel(DefaultTableModel model) {
        tabela.setModel(model);
    }
}