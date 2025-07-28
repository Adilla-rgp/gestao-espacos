package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CadastroEspacoView extends JFrame {
    private JTextField nomeField;
    private JComboBox<String> tipoCombo;
    private JTextField capacidadeField;
    private JTextField localizacaoField;
    private JTextArea descricaoArea;
    private JButton salvarButton;
    private JButton cancelarButton;
    private JButton limparButton;

    public CadastroEspacoView() {
        setTitle("Cadastro de Espaço");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com BoxLayout vertical
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25)); 

        // Nome
        mainPanel.add(criarLinha("Nome:", nomeField = new JTextField()));
        mainPanel.add(Box.createVerticalStrut(10));

        // Tipo
        tipoCombo = new JComboBox<>(new String[]{
            "Sala de Aula", "Laboratório", "Auditório", "Quadra", "Sala de Reunião"
        });
        mainPanel.add(criarLinha("Tipo:", tipoCombo));
        mainPanel.add(Box.createVerticalStrut(10));

        // Capacidade
        mainPanel.add(criarLinha("Capacidade:", capacidadeField = new JTextField()));
        mainPanel.add(Box.createVerticalStrut(10));

        // Localização
        mainPanel.add(criarLinha("Localização:", localizacaoField = new JTextField()));
        mainPanel.add(Box.createVerticalStrut(10));

        // Descrição
        JLabel descLabel = new JLabel("Descrição:");
        descricaoArea = new JTextArea(4, 20);
        JScrollPane scroll = new JScrollPane(descricaoArea);
        scroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(descLabel);
        mainPanel.add(scroll);
        mainPanel.add(Box.createVerticalStrut(15));

        // Botões alinhados horizontalmente
        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        salvarButton = new JButton("Salvar");
        cancelarButton = new JButton("Cancelar");
        limparButton = new JButton("Limpar");
        botoesPanel.add(salvarButton);
        botoesPanel.add(cancelarButton);
        botoesPanel.add(limparButton);
        mainPanel.add(botoesPanel);

        add(mainPanel);
    }

    private JPanel criarLinha(String labelTexto, JComponent campo) {
        JPanel linha = new JPanel(new BorderLayout(10, 5));
        JLabel label = new JLabel(labelTexto);
        linha.add(label, BorderLayout.WEST);
        linha.add(campo, BorderLayout.CENTER);
        return linha;
    }

    // Getters
    public String getNome() { return nomeField.getText(); }
    public String getTipo() { return (String) tipoCombo.getSelectedItem(); }
    public String getCapacidade() { return capacidadeField.getText(); }
    public String getLocalizacao() { return localizacaoField.getText(); }
    public String getDescricao() { return descricaoArea.getText(); }
    public JButton getSalvarButton() { return salvarButton; }
    public JButton getCancelarButton() { return cancelarButton; }
    public JButton getLimparButton() { return limparButton; }

    // Limpar campos
    public void limparCampos() {
        nomeField.setText("");
        tipoCombo.setSelectedIndex(0);
        capacidadeField.setText("");
        localizacaoField.setText("");
        descricaoArea.setText("");
    }

    //--------------------------------------------------------------//

    public void adicionarSalvarButtonListener(ActionListener listener){
        salvarButton.addActionListener(listener);
    }

    public void adicionarCancelarButtonListener(ActionListener listener){
        cancelarButton.addActionListener(listener);
    }

    public void adicionarLimparButtonListener(ActionListener listener){
        limparButton.addActionListener(listener);
    }
}   