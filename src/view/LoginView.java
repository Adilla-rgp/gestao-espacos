package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField usuarioField;
    private JPasswordField senhaField;
    private JButton entrarButton;
    private JButton cadastrarButton;
    private JLabel mensagemLabel;

    public LoginView() {
        setTitle("Login - Gestão de Espaços");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Cores personalizadas
        Color backgroundColor = new Color(240, 245, 255);     // azul bem claro
        Color panelColor = new Color(255, 255, 255);          // branco para contraste
        Color btnColor = new Color(76, 175, 80);              // verde
        Color btnText = Color.WHITE;

        // Painel principal com cor de fundo
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(backgroundColor);
        mainPanel.setLayout(new GridBagLayout());

        // Painel central com os componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(panelColor);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(30, 100, 30, 100)
        ));

        // Fonte maior para rótulos
        Font labelFont = new Font("SansSerif", Font.BOLD, 18);

        mensagemLabel = new JLabel("", SwingConstants.CENTER);
        mensagemLabel.setForeground(Color.RED);
        mensagemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usuarioLabel = new JLabel("Email:");
        usuarioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usuarioLabel.setFont(labelFont);

        usuarioField = new JTextField();
        usuarioField.setMaximumSize(new Dimension(300, 32));
        usuarioField.setFont(new Font("SansSerif", Font.PLAIN, 15));
        usuarioField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        usuarioField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        senhaLabel.setFont(labelFont);

        senhaField = new JPasswordField();
        senhaField.setMaximumSize(new Dimension(300, 32));
        senhaField.setFont(new Font("SansSerif", Font.PLAIN, 15));
        senhaField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        senhaField.setAlignmentX(Component.CENTER_ALIGNMENT);

        entrarButton = new JButton("Entrar");
        cadastrarButton = new JButton("Cadastrar");

        Dimension buttonSize = new Dimension(150, 35);
        Font btnFont = new Font("SansSerif", Font.BOLD, 15);

        entrarButton.setPreferredSize(buttonSize);
        entrarButton.setMaximumSize(buttonSize);
        entrarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        entrarButton.setFont(btnFont);
        entrarButton.setBackground(btnColor);
        entrarButton.setForeground(btnText);
        entrarButton.setFocusPainted(false);

        cadastrarButton.setPreferredSize(buttonSize);
        cadastrarButton.setMaximumSize(buttonSize);
        cadastrarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cadastrarButton.setFont(btnFont);
        cadastrarButton.setBackground(new Color(33, 150, 243)); // azul
        cadastrarButton.setForeground(btnText);
        cadastrarButton.setFocusPainted(false);

        // Adicionando componentes ao painel interno
        panel.add(Box.createVerticalStrut(20));
        panel.add(mensagemLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(usuarioLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(usuarioField);
        panel.add(Box.createVerticalStrut(20));
        panel.add(senhaLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(senhaField);
        panel.add(Box.createVerticalStrut(30));
        panel.add(entrarButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(cadastrarButton);
        panel.add(Box.createVerticalGlue());

        mainPanel.add(panel); // adiciona o painel central ao painel principal
        add(mainPanel);       // adiciona o painel principal ao frame
    }

    public String getEmail() {
        return usuarioField.getText();
    }

    public String getSenha() {
        return new String(senhaField.getPassword());
    }

    public void adicionarLoginListener(ActionListener listener) {
        entrarButton.addActionListener(listener);
    }

    public void adicionarCadastrarListener(ActionListener listener){
        cadastrarButton.addActionListener(listener);
    }

    public void mostrarMensagem(String msg) {
        mensagemLabel.setText(msg);
    }
}