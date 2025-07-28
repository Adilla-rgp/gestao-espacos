package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MinhasReservasView extends JFrame {
    private JTable tabela;
    private JButton cancelarButton;
    private JButton detalhesButton;

    public MinhasReservasView() {
        setTitle("Minhas Reservas");
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
        cancelarButton = new JButton("Cancelar Reserva");
        detalhesButton = new JButton("Confirmar Reserva");

        Font fonteBotao = new Font("SansSerif", Font.BOLD, 14);
        Dimension tamanhoBotao = new Dimension(180, 35);

        cancelarButton.setFont(fonteBotao);
        cancelarButton.setBackground(new Color(244, 67, 54));
        cancelarButton.setForeground(Color.WHITE);
        cancelarButton.setFocusPainted(false);
        cancelarButton.setPreferredSize(tamanhoBotao);

        detalhesButton.setFont(fonteBotao);
        detalhesButton.setBackground(new Color(33, 150, 243));
        detalhesButton.setForeground(Color.WHITE);
        detalhesButton.setFocusPainted(false);
        detalhesButton.setPreferredSize(tamanhoBotao);

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
        botoesPanel.setBackground(fundo);
        botoesPanel.add(cancelarButton);
        botoesPanel.add(detalhesButton);

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

    public JButton getCancelarButton() {
        return cancelarButton;
    }

    public JButton getDetalhesButton() {
        return detalhesButton;
    }

    public void setTabelaModel(DefaultTableModel model) {
        tabela.setModel(model);
    }
}
