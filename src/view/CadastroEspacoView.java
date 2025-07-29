package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CadastroEspacoView extends JFrame {
    private JTextField nomeField;
    private JComboBox<String> tipoCombo;
    private JComboBox<String> capacidadeCombo;
    private JComboBox<String> unidadeCombo;
    private JComboBox<String> localizacaoCombo;
    private JTextArea descricaoArea;
    private JButton salvarButton;
    private JButton cancelarButton;
    private JButton limparButton;
    private JButton continuarButton;

    public CadastroEspacoView() {
        setTitle("Cadastro de Espaço");
        setSize(550, 550);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Font fontePadrao = new Font("SansSerif", Font.PLAIN, 14);
        UIManager.put("Label.font", fontePadrao);
        UIManager.put("Button.font", fontePadrao);
        UIManager.put("ComboBox.font", fontePadrao);
        UIManager.put("TextField.font", fontePadrao);
        UIManager.put("TextArea.font", fontePadrao);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 248, 250));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        nomeField = new JTextField(20);
        tipoCombo = new JComboBox<>(new String[]{
            "Sala de Aula", "Laboratório", "Auditório", "Quadra", "Sala de Reunião"
        });

        capacidadeCombo = new JComboBox<>(new String[]{
            "1-5 pessoas", "6-20 pessoas", "21-50 pessoas", "51-100 pessoas",
            "101-150 pessoas", "151-200 pessoas", "201-300 pessoas"
        });

        unidadeCombo = new JComboBox<>(new String[]{
            "Campus Centro", "Campus Sul", "Campus Norte", "Unidade Remota"
        });

        localizacaoCombo = new JComboBox<>(new String[]{
            "Bloco A", "Bloco B", "Bloco C", "Prédio Principal", "Anexo 1", "Outro"
        });

        descricaoArea = new JTextArea(3, 20);
        descricaoArea.setLineWrap(true);
        descricaoArea.setWrapStyleWord(true);
        JScrollPane descricaoScroll = new JScrollPane(descricaoArea);
        descricaoScroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        mainPanel.add(titulo("Informações Gerais"));
        mainPanel.add(criarLinha("Nome:", nomeField));
        mainPanel.add(criarLinha("Tipo de Espaço:", tipoCombo));
        mainPanel.add(criarLinha("Capacidade:", capacidadeCombo));

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(titulo("Localização"));
        mainPanel.add(criarLinha("Unidade Física:", unidadeCombo));
        mainPanel.add(criarLinha("Localização:", localizacaoCombo));

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(titulo("Descrição"));
        mainPanel.add(descricaoScroll);

        mainPanel.add(Box.createVerticalStrut(20));
        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        botoesPanel.setBackground(new Color(245, 248, 250));

        salvarButton = new JButton("Salvar");
        continuarButton = new JButton("Continuar Cadastro");
        limparButton = new JButton("Limpar");
        cancelarButton = new JButton("Cancelar");

        botoesPanel.add(salvarButton);
        botoesPanel.add(continuarButton);
        botoesPanel.add(limparButton);
        botoesPanel.add(cancelarButton);

        mainPanel.add(botoesPanel);
        add(mainPanel);
    }

    private JLabel titulo(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        return label;
    }

    private JPanel criarLinha(String labelTexto, JComponent campo) {
        JPanel linha = new JPanel(new BorderLayout(5, 5));
        linha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        linha.setBackground(new Color(245, 248, 250));
        JLabel label = new JLabel(labelTexto);
        linha.add(label, BorderLayout.WEST);
        linha.add(campo, BorderLayout.CENTER);
        return linha;
    }

    // Getters
    public String getNome() { return nomeField.getText(); }
    public String getTipo() { return (String) tipoCombo.getSelectedItem(); }
    public String getCapacidade() { return (String) capacidadeCombo.getSelectedItem(); }
    public String getUnidade() { return (String) unidadeCombo.getSelectedItem(); }
    public String getLocalizacao() { return (String) localizacaoCombo.getSelectedItem(); }
    public String getDescricao() { return descricaoArea.getText(); }

    public JButton getSalvarButton() { return salvarButton; }
    public JButton getCancelarButton() { return cancelarButton; }
    public JButton getLimparButton() { return limparButton; }
    public JButton getContinuarButton() { return continuarButton; }

    public void limparCampos() {
        nomeField.setText("");
        capacidadeCombo.setSelectedIndex(0);
        unidadeCombo.setSelectedIndex(0);
        localizacaoCombo.setSelectedIndex(0);
        descricaoArea.setText("");
        tipoCombo.setSelectedIndex(0);
    }

    public void adicionarSalvarButtonListener(ActionListener listener) {
        salvarButton.addActionListener(listener);
    }

    public void adicionarCancelarButtonListener(ActionListener listener) {
        cancelarButton.addActionListener(listener);
    }

    public void adicionarLimparButtonListener(ActionListener listener) {
        limparButton.addActionListener(listener);
    }

    public void adicionarContinuarButtonListener(ActionListener listener) {
        continuarButton.addActionListener(listener);
    }
}