package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaNovaReserva extends JFrame {

    private JComboBox<String> comboUnidade;
    private JComboBox<String> comboSala;
    private JComboBox<String> comboHorario;
    private JTextField txtData;
    private JTextField txtNomeReserva;
    private JTextField txtDescricaoReserva;
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

        // Campo nome da reserva
        JLabel lblNome = new JLabel("Nome da Reserva:");
        lblNome.setFont(fonteLabel);
        lblNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtNomeReserva = new JTextField();
        txtNomeReserva.setFont(fonteCampo);
        txtNomeReserva.setMaximumSize(new Dimension(400, 30));
        txtNomeReserva.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Campo descrição
        JLabel lblDescricao = new JLabel("Descrição:");
        lblDescricao.setFont(fonteLabel);
        lblDescricao.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtDescricaoReserva = new JTextField();
        txtDescricaoReserva.setFont(fonteCampo);
        txtDescricaoReserva.setMaximumSize(new Dimension(400, 30));
        txtDescricaoReserva.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Labels e Combos
        JLabel lblPredio = new JLabel("Selecione o Prédio:");
        lblPredio.setFont(fonteLabel);
        lblPredio.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboUnidade = new JComboBox<>(new String[] {});
        comboUnidade.setFont(fonteCampo);
        comboUnidade.setMaximumSize(new Dimension(400, 30));
        comboUnidade.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSala = new JLabel("Selecione a Sala:");
        lblSala.setFont(fonteLabel);
        lblSala.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboSala = new JComboBox<>(new String[] {});
        comboSala.setFont(fonteCampo);
        comboSala.setMaximumSize(new Dimension(400, 30));
        comboSala.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        comboHorario = new JComboBox<>(new String[] {});
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
        add(lblNome);
        add(Box.createVerticalStrut(8));
        add(txtNomeReserva);
        add(Box.createVerticalStrut(20));
        add(lblDescricao);
        add(Box.createVerticalStrut(8));
        add(txtDescricaoReserva);
        add(Box.createVerticalStrut(20));
        add(lblPredio);
        add(Box.createVerticalStrut(8));
        add(comboUnidade);
        add(Box.createVerticalStrut(20));
        add(lblSala);
        add(Box.createVerticalStrut(8));
        add(comboSala);
        add(Box.createVerticalStrut(20));
        add(lblData);
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
/*      btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String predio = (String) comboUnidade.getSelectedItem();
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

     */
    }


    public void adicionarVoltarBottonListener(ActionListener listener){
        btnVoltar.addActionListener(listener);
    }

    public void adicionarConfirmarBottonListener(ActionListener listener){
        btnConfirmar.addActionListener(listener);
    }

    public JComboBox<String> getComboUnidade() {
        return comboUnidade;
    }

    public JComboBox<String> getComboSala() {
        return comboSala;
    }

    public JComboBox<String> getComboHorarios() {
        return comboHorario;
    }

    public String getNomeReserva() {
        return txtNomeReserva.getText();
    }

    public String getDescricaoReserva() {
        return txtDescricaoReserva.getText();
    }

    public void atualizarComboUnidade(List<String> unidades) {
        comboUnidade.removeAllItems();
        for (String unidade : unidades) {
            comboUnidade.addItem(unidade);
        }
    }

    public void atualizarComboSala(List<String> salas) {
        comboSala.removeAllItems();
        for (String sala : salas) {
            comboSala.addItem(sala);
        }
    }

    public void atualizarComboHorarios(List<String> horarios) {
        comboHorario.removeAllItems();
        for (String horario : horarios) {
            comboHorario.addItem(horario);
        }
    }

    public void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }
}
