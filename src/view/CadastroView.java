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

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        mensagemLabel = new JLabel("", SwingConstants.CENTER);
        mensagemLabel.setForeground(new Color(0, 128, 0));
        mensagemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nomeLabel = new JLabel("Nome do Usuário:");
        nomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nomeField = new JTextField();
        nomeField.setMaximumSize(new Dimension(200, 22));

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(200, 22));

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        senhaField = new JPasswordField();
        senhaField.setMaximumSize(new Dimension(200, 22));

        concluirButton = new JButton("Concluir Cadastro");
        concluirButton.setMaximumSize(new Dimension(180, 28));
        concluirButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        voltarButton = new JButton("Voltar para Login");
        voltarButton.setMaximumSize(new Dimension(180, 28));
        voltarButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(mensagemLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(senhaLabel);
        panel.add(senhaField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(concluirButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(voltarButton);

        add(panel);
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

    //------------------------------------------------------//

    public void adicionarVoltarButtonListener(ActionListener listener){
        voltarButton.addActionListener(listener);
    }

    public void acicionarConcluirButtonListener(ActionListener listener){
        concluirButton.addActionListener(listener);
    }

    public void mostrarMensagem(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
