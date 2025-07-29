package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaNovaReserva extends JFrame {

    private JComboBox<String> comboPredio;
    private JComboBox<String> comboSala;
    private JComboBox<String> comboHorario;
    private JTextField txtData; // campo de data adicionado
    private JButton btnConfirmar;
    private JButton btnVoltar;

    public TelaNovaReserva() {
        setTitle("Nova Reserva");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Fundo azul claro
        Color fundo = new Color(235, 243, 255);
        getContentPane().setBackground(fundo);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Fontes
        Font fonteLabel = new Font("SansSerif", Font.BOLD, 18);
        Font fonteCampo = new Font("SansSerif", Font.PLAIN, 14);

        // Labels e Combos
        JLabel lblPredio = new JLabel("Selecione o Prédio:");
        lblPredio.setFont(fonteLabel);
        lblPredio.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboPredio = new JComboBox<>(new String[] { "Prédio A", "Prédio B", "Prédio C" });
        comboPredio.setFont(fonteCampo);
        comboPredio.setMaximumSize(new Dimension(400, 30));
        comboPredio.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSala = new JLabel("Selecione a Sala:");
        lblSala.setFont(fonteLabel);
        lblSala.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboSala = new JComboBox<>(new String[] { "Sala 101", "Sala 102", "Sala 201", "Sala 202" });
        comboSala.setFont(fonteCampo);
        comboSala.setMaximumSize(new Dimension(400, 30));
        comboSala.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Campo de Data adicionado
        JLabel lblData = new JLabel("Selecione a Data:");
        lblData.setFont(fonteLabel);
        lblData.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtData = new JTextField("dd/mm/aaaa");
        txtData.setFont(fonteCampo);
        txtData.setMaximumSize(new Dimension(400, 30));
        txtData.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblHorario = new JLabel("Selecione o Horário:");
        lblHorario.setFont(fonteLabel);
        lblHorario.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboHorario = new JComboBox<>(new String[] {
            "08:00 - 09:00",
            "09:00 - 10:00",
            "10:00 - 11:00",
            "14:00 - 15:00",
            "15:00 - 16:00"
        });
        comboHorario.setFont(fonteCampo);
        comboHorario.setMaximumSize(new Dimension(400, 30));
        comboHorario.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botões
        btnConfirmar = new JButton("Confirmar Reserva");
        btnVoltar = new JButton("Voltar");

        btnConfirmar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnConfirmar.setBackground(new Color(76, 175, 80));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFocusPainted(false);
        btnConfirmar.setMaximumSize(new Dimension(200, 35));
        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnVoltar.setBackground(new Color(33, 150, 243));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFocusPainted(false);
        btnVoltar.setMaximumSize(new Dimension(200, 35));
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adiciona os componentes à janela
        add(Box.createVerticalStrut(40));
        add(lblPredio);
        add(Box.createVerticalStrut(8));
        add(comboPredio);
        add(Box.createVerticalStrut(20));
        add(lblSala);
        add(Box.createVerticalStrut(8));
        add(comboSala);
        add(Box.createVerticalStrut(20));
        add(lblData); // campo de data
        add(Box.createVerticalStrut(8));
        add(txtData);
        add(Box.createVerticalStrut(20));
        add(lblHorario);
        add(Box.createVerticalStrut(8));
        add(comboHorario);
        add(Box.createVerticalStrut(30));
        add(btnConfirmar);
        add(Box.createVerticalStrut(10));
        add(btnVoltar);
        add(Box.createVerticalGlue());

        // Evento do botão confirmar
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String predio = (String) comboPredio.getSelectedItem();
                String sala = (String) comboSala.getSelectedItem();
                String horario = (String) comboHorario.getSelectedItem();
                String data = txtData.getText();

                JOptionPane.showMessageDialog(null,
                    "Reserva feita para:\n" +
                    "Prédio: " + predio + "\n" +
                    "Sala: " + sala + "\n" +
                    "Data: " + data + "\n" +
                    "Horário: " + horario,
                    "Reserva Confirmada",
                    JOptionPane.INFORMATION_MESSAGE);

                // Tabela com a reserva confirmada
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Prédio");
                model.addColumn("Sala");
                model.addColumn("Data");
                model.addColumn("Horário");
                model.addColumn("Status");
                model.addRow(new Object[] { predio, sala, data, horario, "Agendada" });

                dispose();
                MinhasReservasView reservasView = new MinhasReservasView();
                reservasView.setTabelaModel(model);
                reservasView.setVisible(true);
            }
        });

        btnVoltar.addActionListener(e -> dispose());
    }
}
