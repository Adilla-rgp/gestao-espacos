package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CadastroUnidadeFisicaView extends JFrame {
    private JTextField nomeUnidadeFisicaField;
    private JTextArea descricaoTipoArea;
    private JButton confirmarButton;
    private JComboBox<String> tiposUnidadeFisica;
    private JButton cancelarButton;

    public CadastroUnidadeFisicaView() {
        setTitle("Cadastro de Unidade Fisica");
        setSize(400, 350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Font fontePadrao = new Font("SansSerif", Font.PLAIN, 14);
        UIManager.put("Label.font", fontePadrao);
        UIManager.put("Button.font", fontePadrao);
        UIManager.put("TextField.font", fontePadrao);
        UIManager.put("TextArea.font", fontePadrao);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(new Color(250, 250, 255));

        nomeUnidadeFisicaField = new JTextField(20);
        descricaoTipoArea = new JTextArea(4, 20);
        descricaoTipoArea.setLineWrap(true);
        descricaoTipoArea.setWrapStyleWord(true);
        JScrollPane descricaoScroll = new JScrollPane(descricaoTipoArea);
        descricaoScroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        tiposUnidadeFisica = new JComboBox<>(new String[]{
            "Predio", "Nucleo"
        });

        confirmarButton = new JButton("Confirmar");
        cancelarButton = new JButton("Cancelar");

        mainPanel.add(titulo("Nome do Espaço"));
        mainPanel.add(nomeUnidadeFisicaField);
        mainPanel.add(Box.createVerticalStrut(8));

        mainPanel.add(criarLinha("Tipo de Espaço:", tiposUnidadeFisica));
        mainPanel.add(titulo("Descrição do Tipo"));
        mainPanel.add(descricaoScroll);
        mainPanel.add(Box.createVerticalStrut(8));

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        botoesPanel.setBackground(mainPanel.getBackground());
        botoesPanel.add(confirmarButton);
        botoesPanel.add(cancelarButton);

        mainPanel.add(botoesPanel);
        add(mainPanel);
    }

    private JLabel titulo(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.BOLD, 15));
        return label;
    }

    // Getters
    public String getNomeUnidadeFIsica() { return nomeUnidadeFisicaField.getText(); }
    public String getDescricaoTipo() { return descricaoTipoArea.getText(); }
    public JButton getConfirmarButton() { return confirmarButton; }
    public JButton getCancelarButton() { return cancelarButton; }
    public String getTipo() { return (String) tiposUnidadeFisica.getSelectedItem(); }

    // Listeners
    public void adicionarConfirmarListener(ActionListener listener) {
        confirmarButton.addActionListener(listener);
    }

    public void adicionarCancelarListener(ActionListener listener) {
        cancelarButton.addActionListener(listener);
    }

    public void limparCampos() {
        nomeUnidadeFisicaField.setText("");
        descricaoTipoArea.setText("");
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

    public void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }

}
