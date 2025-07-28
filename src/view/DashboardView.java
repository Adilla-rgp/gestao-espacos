package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DashboardView extends JFrame {
    private JLabel tituloLabel;
    private JButton novaReservaButton;
    private JTextArea estatisticasArea;

    public DashboardView(){

        setTitle("Dashboard - Gestão de Espaços");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel superior com título e botão
        tituloLabel = new JLabel("Bem-vindo!", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 18));

        novaReservaButton = new JButton("Nova Reserva");

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        topPanel.add(tituloLabel, BorderLayout.CENTER);
        topPanel.add(novaReservaButton, BorderLayout.EAST);

        // Área de estatísticas
        estatisticasArea = new JTextArea();
        estatisticasArea.setEditable(false);
        estatisticasArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        estatisticasArea.setMargin(new Insets(10, 10, 10, 10));
        estatisticasArea.setLineWrap(true);
        estatisticasArea.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(estatisticasArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Estatísticas do Sistema"));

        // Layout principal
        setLayout(new BorderLayout(0, 10));
        add(topPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    public void setTitulo(String titulo) {
        tituloLabel.setText(titulo);
    }

    public void setEstatisticas(String texto) {
        estatisticasArea.setText(texto);
    }

    public void adicionarNovaReservaListener(ActionListener listener) {
        novaReservaButton.addActionListener(listener);
    }

    public void mostrarMensagem(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}