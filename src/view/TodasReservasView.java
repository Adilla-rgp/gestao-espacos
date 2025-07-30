package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class TodasReservasView extends JFrame {
    private JTable tabela;
    private JButton voltarButton; // novo botão

    public TodasReservasView() {
        setTitle("Todas Reservas");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Fundo fluido azul claro
        Color fundo = new Color(235, 243, 255);
        getContentPane().setBackground(fundo);
        setLayout(new BorderLayout(10, 10));

        // Tabela configurada
        tabela = new JTable();
        tabela.setFillsViewportHeight(true);
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tabela.setRowHeight(26);
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Reservas Agendadas"));
        scrollPane.getViewport().setBackground(Color.WHITE); // mantém fundo da tabela branco
        scrollPane.setBackground(fundo);

        // Botões estilizados
        voltarButton = new JButton("Voltar"); // botão Voltar

        Font fonteBotao = new Font("SansSerif", Font.BOLD, 14);
        Dimension tamanhoBotao = new Dimension(180, 35);



        voltarButton.setFont(fonteBotao);
        voltarButton.setBackground(new Color(158, 158, 158));
        voltarButton.setForeground(Color.WHITE);
        voltarButton.setFocusPainted(false);
        voltarButton.setPreferredSize(tamanhoBotao);

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
        botoesPanel.setBackground(fundo);
        botoesPanel.add(voltarButton); // adiciona ao painel

        // Margem lateral mais confortável
        JPanel margemPanel = new JPanel(new BorderLayout());
        margemPanel.setBackground(fundo);
        margemPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        margemPanel.add(scrollPane, BorderLayout.CENTER);

        // Composição final
        add(margemPanel, BorderLayout.CENTER);
        add(botoesPanel, BorderLayout.SOUTH);
    }

    public JTable getTabela() {
        return tabela;
    }

    public JButton getVoltarButton() {
        return voltarButton;
    }

    public void setTabelaModel(DefaultTableModel model) {
        tabela.setModel(model);
    }

    public void adicionarVoltarButtonListener(ActionListener listener){
        voltarButton.addActionListener(listener);
    }

    public void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}