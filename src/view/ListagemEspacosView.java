package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ListagemEspacosView extends JFrame {
    private JTable tabela;
    private JTextField buscaField;
    private JButton buscarButton;
    private JButton editarButton;
    private JButton excluirButton;

    public ListagemEspacosView() {
        setTitle("Gerenciamento de Espaços");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel buscaLabel = new JLabel("Buscar por nome:");
        buscaField = new JTextField(25);
        buscarButton = new JButton("Buscar");

        JPanel buscaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        buscaPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        buscaPanel.add(buscaLabel);
        buscaPanel.add(buscaField);
        buscaPanel.add(buscarButton);

        tabela = new JTable();
        tabela.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createTitledBorder("📌 Espaços Cadastrados"));

        editarButton = new JButton("Editar");
        excluirButton = new JButton("Excluir");

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botoesPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        botoesPanel.add(editarButton);
        botoesPanel.add(excluirButton);

        setLayout(new BorderLayout(10, 10));
        add(buscaPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(botoesPanel, BorderLayout.SOUTH);
    }

    public JTable getTabela() { return tabela; }
    public JTextField getBuscaField() { return buscaField; }
    public JButton getBuscarButton() { return buscarButton; }
    public JButton getEditarButton() { return editarButton; }
    public JButton getExcluirButton() { return excluirButton; }

    public void setTabelaModel(DefaultTableModel model) {
        tabela.setModel(model);
    }
}
