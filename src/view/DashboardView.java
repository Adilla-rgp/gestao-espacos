package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DashboardView extends JFrame {
    private JLabel tituloLabel;
    private JButton novaReservaButton;
    private JButton minhasReservasButton;
    private JTextArea estatisticasArea;

    public DashboardView() {
        setTitle("Dashboard - Gestão de Espaços");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Título e botões
        tituloLabel = new JLabel("Bem-vindo!", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

        novaReservaButton = new JButton("Nova Reserva");
        novaReservaButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        novaReservaButton.setPreferredSize(new Dimension(160, 32));

        minhasReservasButton = new JButton("Minhas Reservas");
        minhasReservasButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        minhasReservasButton.setPreferredSize(new Dimension(160, 32));

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        botoesPanel.add(novaReservaButton);
        botoesPanel.add(minhasReservasButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));
        topPanel.add(tituloLabel, BorderLayout.CENTER);
        topPanel.add(botoesPanel, BorderLayout.EAST);

        // Painel de cards coloridos
        JPanel cardPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.add(criarCard("Total de Reservas", "—", new Color(210, 230, 255)));
        cardPanel.add(criarCard("Salas Disponíveis", "—", new Color(220, 255, 220)));
        cardPanel.add(criarCard("Horário Popular", "—", new Color(255, 230, 230)));

        estatisticasArea = new JTextArea();
        estatisticasArea.setEditable(false);
        estatisticasArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        estatisticasArea.setLineWrap(true);
        estatisticasArea.setWrapStyleWord(true);
        estatisticasArea.setMargin(new Insets(10, 10, 10, 10));
        estatisticasArea.setText("Nenhuma estatística disponível no momento.");

        JScrollPane scroll = new JScrollPane(estatisticasArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Estatísticas do Sistema"));

        add(topPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);
    }

    private JPanel criarCard(String titulo, String valor, Color corFundo) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(corFundo);
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        card.setPreferredSize(new Dimension(200, 100));

        JLabel tituloLabel = new JLabel(titulo, SwingConstants.CENTER);
        tituloLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel valorLabel = new JLabel(valor, SwingConstants.CENTER);
        valorLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        valorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(Box.createVerticalStrut(15));
        card.add(tituloLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(valorLabel);

        return card;
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

    public void adicionarMinhasReservasListener(ActionListener listener) {
        minhasReservasButton.addActionListener(listener);
    }

    public void mostrarMensagem(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}