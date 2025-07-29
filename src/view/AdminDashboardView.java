package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminDashboardView extends JFrame {
    private JLabel tituloLabel;
    private JButton cadastrarEspacosButton;
    private JButton cadastrarEspacoIndividualButton; // Novo botão
    private JButton minhasReservasButton;
    private JButton verTodasReservasButton;

    private JLabel totalEspacosLabel;
    private JLabel reservasDiaLabel;
    private JLabel maisUsadosLabel;

    private JTable tabelaResumo;
    private JTextArea estatisticasArea;

    public AdminDashboardView() {
        setTitle("Dashboard - Gestão de Espaços (Admin)");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Título
        tituloLabel = new JLabel("Admin: Bem-vindo!", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("SansSerif", Font.BOLD, 22));

        // Botões
        cadastrarEspacosButton = new JButton("Cadastro de Espaços");
        cadastrarEspacoIndividualButton = new JButton("Cadastrar Espaço"); // Instanciando novo botão
        minhasReservasButton = new JButton("Minhas Reservas");
        verTodasReservasButton = new JButton("Ver Todas as Reservas");

        for (JButton botao : new JButton[]{
                cadastrarEspacosButton,
                cadastrarEspacoIndividualButton,
                minhasReservasButton,
                verTodasReservasButton
        }) {
            botao.setFont(new Font("SansSerif", Font.BOLD, 14));
            botao.setPreferredSize(new Dimension(180, 32));
        }

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        botoesPanel.add(cadastrarEspacosButton);
        botoesPanel.add(cadastrarEspacoIndividualButton); // Adicionando novo botão
        botoesPanel.add(minhasReservasButton);
        botoesPanel.add(verTodasReservasButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));
        topPanel.add(tituloLabel, BorderLayout.CENTER);
        topPanel.add(botoesPanel, BorderLayout.EAST);

        // Cards informativos
        totalEspacosLabel = new JLabel("—");
        reservasDiaLabel = new JLabel("—");
        maisUsadosLabel = new JLabel("—");

        JPanel cardPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        cardPanel.setBackground(Color.WHITE);

        cardPanel.add(criarCard("Total de Espaços", totalEspacosLabel, new Color(200, 230, 255)));
        cardPanel.add(criarCard("Reservas do Dia", reservasDiaLabel, new Color(210, 255, 210)));
        cardPanel.add(criarCard("Mais Utilizados", maisUsadosLabel, new Color(255, 225, 225)));

        // Tabela de resumo
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
        tabelaResumo.setDefaultRenderer(Object.class, new DefaultTableCellRenderer());

        // Estatísticas
        estatisticasArea = new JTextArea(8, 50);
        estatisticasArea.setEditable(false);
        estatisticasArea.setFont(new Font("SansSerif", Font.PLAIN, 13));
        estatisticasArea.setBorder(BorderFactory.createTitledBorder("Estatísticas Gerais"));
        JScrollPane estatisticasScroll = new JScrollPane(estatisticasArea);

        // Montagem geral
        JPanel centroPanel = new JPanel();
        centroPanel.setLayout(new BoxLayout(centroPanel, BoxLayout.Y_AXIS));
        centroPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));
        centroPanel.add(cardPanel);
        centroPanel.add(Box.createVerticalStrut(15));
        centroPanel.add(tabelaScroll);
        centroPanel.add(Box.createVerticalStrut(15));
        centroPanel.add(estatisticasScroll);

        add(topPanel, BorderLayout.NORTH);
        add(centroPanel, BorderLayout.CENTER);
    }

    private JPanel criarCard(String titulo, JLabel valorLabel, Color corFundo) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(corFundo);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel tituloLabel = new JLabel(titulo);
        tituloLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        valorLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        valorLabel.setHorizontalAlignment(SwingConstants.CENTER);

        card.add(tituloLabel, BorderLayout.NORTH);
        card.add(valorLabel, BorderLayout.CENTER);
        return card;
    }

    // Getters de botões
    public JButton getCadastrarEspacosButton() { return cadastrarEspacosButton; }
    public JButton getCadastrarEspacoIndividualButton() { return cadastrarEspacoIndividualButton; } // Getter do novo botão
    public JButton getMinhasReservasButton() { return minhasReservasButton; }
    public JButton getVerTodasReservasButton() { return verTodasReservasButton; }

    public JTable getTabelaResumo() { return tabelaResumo; }
    public JTextArea getEstatisticasArea() { return estatisticasArea; }

    // Atualizadores de dados
    public void setTotalEspacos(String texto) { totalEspacosLabel.setText(texto); }
    public void setReservasDia(String texto) { reservasDiaLabel.setText(texto); }
    public void setMaisUsados(String texto) { maisUsadosLabel.setText(texto); }

    // Listeners
    public void adicionarCadastrarEspacosListener(ActionListener listener) {
        cadastrarEspacosButton.addActionListener(listener);
    }

    public void adicionarCadastrarEspacoIndividualListener(ActionListener listener) {
        cadastrarEspacoIndividualButton.addActionListener(listener);
    }

    public void adicionarMinhasReservasListener(ActionListener listener) {
        minhasReservasButton.addActionListener(listener);
    }

    public void adicionarVerTodasReservasListener(ActionListener listener) {
        verTodasReservasButton.addActionListener(listener);
    }
}