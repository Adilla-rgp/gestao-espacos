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

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        mensagemLabel = new JLabel("", SwingConstants.CENTER);
        mensagemLabel.setForeground(Color.RED);
        mensagemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usuarioLabel = new JLabel("Email:");
        usuarioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usuarioField = new JTextField();
        usuarioField.setMaximumSize(new Dimension(200, 22)); 

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        senhaField = new JPasswordField();
        senhaField.setMaximumSize(new Dimension(200, 22)); 
     
        entrarButton = new JButton("Entrar");
        entrarButton.setMaximumSize(new Dimension(100, 28));
        entrarButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setMaximumSize(new Dimension(100, 28));
        cadastrarButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(mensagemLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(usuarioLabel);
        panel.add(usuarioField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(senhaLabel);
        panel.add(senhaField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(entrarButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(cadastrarButton);

        add(panel);

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
        JOptionPane.showMessageDialog(this, msg);
    }
}