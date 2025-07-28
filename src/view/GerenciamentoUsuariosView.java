package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GerenciamentoUsuariosView extends JFrame {
    private JTable tabelaUsuarios;
    private JButton novoButton;
    private JButton editarButton;
    private JButton desativarButton;

    public GerenciamentoUsuariosView() {
        setTitle("Gerenciamento de Usuários");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        // Título
        JLabel titulo = new JLabel("Gerenciamento de Usuários", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Tabela
        String[] colunas = {"Nome", "Username", "Email", "Tipo"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        tabelaUsuarios = new JTable(model);
        tabelaUsuarios.setRowHeight(28);
        tabelaUsuarios.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tabelaUsuarios.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));
        tabelaUsuarios.getTableHeader().setBackground(new Color(200, 220, 250));
        tabelaUsuarios.setGridColor(Color.GRAY);

        // Zebra striping
        tabelaUsuarios.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? new Color(245, 250, 255) : Color.WHITE);
                return c;
            }
        });

        JScrollPane scroll = new JScrollPane(tabelaUsuarios);
        scroll.setBorder(BorderFactory.createTitledBorder("Lista de Usuários"));

        // Botões de ação
        novoButton = new JButton("Novo Usuário");
        // leva para a tela de cadastro
        desativarButton = new JButton("Excluir Usuário");

        for (JButton btn : new JButton[]{novoButton, editarButton, desativarButton}) {
            btn.setFont(new Font("SansSerif", Font.BOLD, 13));
            btn.setPreferredSize(new Dimension(140, 32));
        }

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botoesPanel.add(novoButton);
        botoesPanel.add(editarButton);
        botoesPanel.add(desativarButton);

        // Montagem da tela
        add(titulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(botoesPanel, BorderLayout.SOUTH);
    }

    // Getters para o Controller
    public JTable getTabelaUsuarios() {
        return tabelaUsuarios;
    }

    public JButton getNovoButton() {
        return novoButton;
    }

    public JButton getEditarButton() {
        return editarButton;
    }

    public JButton getDesativarButton() {
        return desativarButton;
    }

    public void setTabelaModel(DefaultTableModel model) {
        tabelaUsuarios.setModel(model);
    }

    public void mostrarMensagem(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}