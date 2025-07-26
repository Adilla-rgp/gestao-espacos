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

        // Tabela configurada com rolagem e borda com título
        tabela = new JTable();
        tabela.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Reservas Agendadas"));

        // Painel de botões com espaçamento
        cancelarButton = new JButton("Cancelar Reserva");
        detalhesButton = new JButton("Ver Detalhes");

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botoesPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        botoesPanel.add(cancelarButton);
        botoesPanel.add(detalhesButton);

        // Layout principal
        setLayout(new BorderLayout(10, 10));
        add(scrollPane, BorderLayout.CENTER);
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