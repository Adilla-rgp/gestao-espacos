package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminDashboardView extends JFrame {
    private JLabel tituloLabel;
    private JButton cadastrarEspacosButton;
    private JButton minhasReservasButton;

    private JLabel totalEspacosLabel;
    private JLabel reservasDiaLabel;
    private JLabel maisUsadosLabel;

    private JTable tabelaResumo;
    private JTextArea estatisticasArea;

    public AdminDashboardView() {
        setTitle("Dashboard - Gestão de Espaços (Admin)");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Título e botões
        tituloLabel = new JLabel("Admin: Bem-vindo!", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("SansSerif", Font.BOLD, 22));

        cadastrarEspacosButton = new JButton("Cadastro de espaços");
        minhasReservasButton = new JButton("Minhas Reservas");

        for (JButton botao : new JButton[]{cadastrarEspacosButton, minhasReservasButton}) {
            botao.setFont(new Font("SansSerif", Font.BOLD, 14));
            botao.setPreferredSize(new Dimension(160, 32));
        }

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        botoesPanel.add(cadastrarEspacosButton);
        botoesPanel.add(minhasReservasButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));
        topPanel.add(tituloLabel, BorderLayout.CENTER);
        topPanel.add(botoesPanel, BorderLayout.EAST);

        // Cards personalizados
        totalEspacosLabel = new JLabel("—");
        reservasDiaLabel = new JLabel("—");
        maisUsadosLabel = new JLabel("—");

        JPanel cardPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        cardPanel.setBackground(Color.WHITE);

        cardPanel.add(criarCard("Total de Espaços", totalEspacosLabel, new Color(200, 230, 255)));
        cardPanel.add(criarCard("Reservas do Dia", reservasDiaLabel, new Color(210, 255, 210)));
        cardPanel.add(criarCard("Mais Utilizados", maisUsadosLabel, new Color(255, 225, 225)));

        // Tabela estilizada
        tabelaResumo = new JTable();
        JScrollPane tabelaScroll = new JScrollPane(tabelaResumo);
        tabelaScroll.setBorder(BorderFactory.createTitledBorder("Resumo de Ocupação"));
        tabelaScroll.setPreferredSize(new Dimension(800, 200));

        tabelaResumo.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tabelaResumo.getTableHeader().setBackground(new Color(190, 220, 250));
        tabelaResumo.getTableHeader().setForeground(Color.BLACK);
        tabelaResumo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabelaResumo.setRowHeight(26);
        tabelaResumo.setGridColor(Color.GRAY);

        tabelaResumo.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? new Color(245, 250, 255) : Color.WHITE);
                return c;
            }
        });

        // Área de estatísticas detalhadas
        estatisticasArea = new JTextArea("Nenhuma estatística disponível.");
        estatisticasArea.setEditable(false);
        estatisticasArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        estatisticasArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane estatisticasScroll = new JScrollPane(estatisticasArea);
        estatisticasScroll.setBorder(BorderFactory.createTitledBorder("Estatísticas Detalhadas"));
        estatisticasScroll.setPreferredSize(new Dimension(800, 150));

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));
        bottomPanel.add(tabelaScroll);
        bottomPanel.add(estatisticasScroll);

        // Monta a tela
        add(topPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel criarCard(String titulo, JLabel valorLabel, Color corFundo) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(corFundo);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(160, 160, 160), 1),
                BorderFactory.createEmptyBorder(15, 10, 15, 10)
        ));

        JLabel tituloCard = new JLabel(titulo, SwingConstants.CENTER);
        tituloCard.setFont(new Font("SansSerif", Font.BOLD, 16));
        tituloCard.setForeground(new Color(60, 60, 60));
        tituloCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        valorLabel.setFont(new Font("SansSerif", Font.PLAIN, 22));
        valorLabel.setForeground(new Color(40, 40, 40));
        valorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(tituloCard);
        card.add(Box.createVerticalStrut(10));
        card.add(valorLabel);
        return card;
    }

    // Getters e métodos públicos
    public void setTitulo(String titulo) {
        tituloLabel.setText("Admin: " + titulo);
    }

    public void setTotalEspacos(String texto) {
        totalEspacosLabel.setText(texto);
    }

    public void setReservasDia(String texto) {
        reservasDiaLabel.setText(texto);
    }

    public void setMaisUtilizados(String texto) {
        maisUsadosLabel.setText(texto);
    }

    public void setEstatisticas(String texto) {
        estatisticasArea.setText(texto);
    }

    public void setTabelaResumoModel(DefaultTableModel model) {
        tabelaResumo.setModel(model);
    }

    public void adicionarCadastrarEspacosButtonListener(ActionListener listener) {
        cadastrarEspacosButton.addActionListener(listener);
    }

    public void adicionarMinhasReservasListener(ActionListener listener) {
        minhasReservasButton.addActionListener(listener);
    }

    public void mostrarMensagem(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
