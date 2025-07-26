package view;

import javax.swing.*;
import java.awt.*;

public class SistemaReservasView extends JFrame {
    private JComboBox<String> espacosCombo;
    private JTextField dataField;
    private JComboBox<String> horarioCombo;
    private JTextField finalidadeField;
    private JTextArea observacoesArea;
    private JButton confirmarButton;
    private JButton voltarButton;
    private JButton cancelarButton;

    public SistemaReservasView() {
        setTitle("Sistema de Reservas");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com BoxLayout vertical
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        // Espaço
        mainPanel.add(criarLinha("Espaço:", espacosCombo = new JComboBox<>()));
        mainPanel.add(Box.createVerticalStrut(10));

        // Data
        dataField = new JTextField("dd/mm/aaaa");
        mainPanel.add(criarLinha("Data:", dataField));
        mainPanel.add(Box.createVerticalStrut(10));

        // Horário
        horarioCombo = new JComboBox<>(new String[]{"8h-9h", "9h-10h", "10h-11h", "11h-12h"});
        mainPanel.add(criarLinha("Horário:", horarioCombo));
        mainPanel.add(Box.createVerticalStrut(10));

        // Finalidade
        finalidadeField = new JTextField();
        mainPanel.add(criarLinha("Finalidade:", finalidadeField));
        mainPanel.add(Box.createVerticalStrut(10));

        // Observações
        JLabel obsLabel = new JLabel("Observações:");
        observacoesArea = new JTextArea(3, 20);
        observacoesArea.setLineWrap(true);
        observacoesArea.setWrapStyleWord(true);
        JScrollPane scrollObs = new JScrollPane(observacoesArea);
        scrollObs.setAlignmentX(Component.LEFT_ALIGNMENT);

        mainPanel.add(obsLabel);
        mainPanel.add(scrollObs);
        mainPanel.add(Box.createVerticalStrut(15));

        // Painel de botões
        confirmarButton = new JButton("Confirmar");
        voltarButton = new JButton("Voltar");
        cancelarButton = new JButton("Cancelar");

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        botoesPanel.add(confirmarButton);
        botoesPanel.add(voltarButton);
        botoesPanel.add(cancelarButton);
        mainPanel.add(botoesPanel);

        add(mainPanel);
    }

    private JPanel criarLinha(String labelText, JComponent campo) {
        JPanel linha = new JPanel(new BorderLayout(10, 5));
        JLabel label = new JLabel(labelText);
        linha.add(label, BorderLayout.WEST);
        linha.add(campo, BorderLayout.CENTER);
        return linha;
    }

    public JComboBox<String> getEspacosCombo() { return espacosCombo; }
    public JTextField getDataField() { return dataField; }
    public JComboBox<String> getHorarioCombo() { return horarioCombo; }
    public JTextField getFinalidadeField() { return finalidadeField; }
    public JTextArea getObservacoesArea() { return observacoesArea; }
    public JButton getConfirmarButton() { return confirmarButton; }
    public JButton getVoltarButton() { return voltarButton; }
    public JButton getCancelarButton() { return cancelarButton; }
}