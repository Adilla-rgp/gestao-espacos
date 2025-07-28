package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CadastroView extends JFrame {
    private JTextField nomeField;
    private JTextField emailField;
    private JPasswordField senhaField;
    private JButton concluirButton;
    private JButton voltarButton;
    private JLabel mensagemLabel;

    public CadastroView() {
        setTitle("Cadastro - Gestão de Espaços");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Color fundoPrincipal = new Color(240, 245, 255);   // azul claro
        Color fundoFormulario = Color.WHITE;
        Color verdeBtn = new Color(76, 175, 80);
        Color azulBtn = new Color(33, 150, 243);
        Font fonteLabel = new Font("SansSerif", Font.BOLD, 18);
        Font fonteCampo = new Font("SansSerif", Font.PLAIN, 15);

        // Painel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(fundoPrincipal);

        // Painel do formulário
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(fundoFormulario);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(30, 100, 30, 100)
        ));

        mensagemLabel = new JLabel("", SwingConstants.CENTER);
        mensagemLabel.setForeground(new Color(0, 128, 0));
        mensagemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nomeLabel = new JLabel("Nome do Usuário:");
        nomeLabel.setFont(fonteLabel);
        nomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nomeField = new JTextField();
        nomeField.setMaximumSize(new Dimension(320, 32));
        nomeField.setFont(fonteCampo);
        nomeField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        nomeField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(fonteLabel);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(320, 32));
        emailField.setFont(fonteCampo);
        emailField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(fonteLabel);
        senhaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        senhaField = new JPasswordField();
        senhaField.setMaximumSize(new Dimension(320, 32));
        senhaField.setFont(fonteCampo);
        senhaField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        senhaField.setAlignmentX(Component.CENTER_ALIGNMENT);

        concluirButton = new JButton("Concluir Cadastro");
        concluirButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        concluirButton.setBackground(verdeBtn);
        concluirButton.setForeground(Color.WHITE);
        concluirButton.setFocusPainted(false);
        concluirButton.setMaximumSize(new Dimension(180, 36));
        concluirButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        voltarButton = new JButton("Voltar para Login");
        voltarButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        voltarButton.setBackground(azulBtn);
        voltarButton.setForeground(Color.WHITE);
        voltarButton.setFocusPainted(false);
        voltarButton.setMaximumSize(new Dimension(180, 36));
        voltarButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(20));
        panel.add(mensagemLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(Box.createVerticalStrut(20));
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(Box.createVerticalStrut(20));
        panel.add(senhaLabel);
        panel.add(senhaField);
        panel.add(Box.createVerticalStrut(30));
        panel.add(concluirButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(voltarButton);
        panel.add(Box.createVerticalGlue());

        mainPanel.add(panel);
        add(mainPanel);
    }

    public String getNome() {
        return nomeField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getSenha() {
        return new String(senhaField.getPassword());
    }

    public void adicionarVoltarButtonListener(ActionListener listener) {
        voltarButton.addActionListener(listener);
    }

    public void acicionarConcluirButtonListener(ActionListener listener) {
        concluirButton.addActionListener(listener);
    }

    public void mostrarMensagem(String msg) {
        mensagemLabel.setText(msg); // exibe no topo da tela
    }
}