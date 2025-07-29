package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ListagemReservasAdminView extends JFrame {
    private JTable tabela;
    private JComboBox<String> statusCombo;
    private JComboBox<String> espacoCombo;
    private JComboBox<String> usuarioCombo;
    private JTextField dataInicioField;
    private JTextField dataFimField;
    private JButton filtrarButton;
    private JButton cancelarReservaButton;
    private JButton detalhesButton;
    private JButton voltarButton;

    public ListagemReservasAdminView() {
        setTitle("Todas as Reservas - Administração");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 10));

        // Filtros
        JPanel filtroPanel = new JPanel(new GridLayout(2, 5, 10, 10));
        filtroPanel.setBorder(BorderFactory.createTitledBorder("Filtros"));

        dataInicioField = new JTextField("dd/mm/aaaa");
        dataFimField = new JTextField("dd/mm/aaaa");

        statusCombo = new JComboBox<>(new String[] {
            "Todos", "Agendada", "Em andamento", "Finalizada", "Cancelada"
        });

        espacoCombo = new JComboBox<>();
        usuarioCombo = new JComboBox<>();

        filtroPanel.add(new JLabel("Data Início:"));
        filtroPanel.add(dataInicioField);
        filtroPanel.add(new JLabel("Data Fim:"));
        filtroPanel.add(dataFimField);
        filtroPanel.add(new JLabel("Status:"));
        filtroPanel.add(statusCombo);
        filtroPanel.add(new JLabel("Espaço:"));
        filtroPanel.add(espacoCombo);
        filtroPanel.add(new JLabel("Usuário:"));
        filtroPanel.add(usuarioCombo);

        filtrarButton = new JButton("Filtrar");
        JPanel filtroBotaoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        filtroBotaoPanel.add(filtrarButton);

        JPanel filtroContainer = new JPanel(new BorderLayout());
        filtroContainer.add(filtroPanel, BorderLayout.CENTER);
        filtroContainer.add(filtroBotaoPanel, BorderLayout.SOUTH);

        // Tabela
        tabela = new JTable();
        JScrollPane tabelaScroll = new JScrollPane(tabela);
        tabelaScroll.setBorder(BorderFactory.createTitledBorder("Reservas"));

        // Botões de ação
        cancelarReservaButton = new JButton("Cancelar Reserva");
        detalhesButton = new JButton("Ver Detalhes");
        voltarButton = new JButton("Voltar");

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botoesPanel.add(detalhesButton);
        botoesPanel.add(cancelarReservaButton);
        botoesPanel.add(voltarButton); // Adicionado botão Voltar

        // Montagem da tela
        add(filtroContainer, BorderLayout.NORTH);
        add(tabelaScroll, BorderLayout.CENTER);
        add(botoesPanel, BorderLayout.SOUTH);
    }

    // Getters públicos
    public JTable getTabela() { return tabela; }
    public JComboBox<String> getStatusCombo() { return statusCombo; }
    public JComboBox<String> getEspacoCombo() { return espacoCombo; }
    public JComboBox<String> getUsuarioCombo() { return usuarioCombo; }
    public JTextField getDataInicioField() { return dataInicioField; }
    public JTextField getDataFimField() { return dataFimField; }
    public JButton getFiltrarButton() { return filtrarButton; }
    public JButton getCancelarReservaButton() { return cancelarReservaButton; }
    public JButton getDetalhesButton() { return detalhesButton; }
    public JButton getVoltarButton() { return voltarButton; }

    public void setTabelaModel(DefaultTableModel model) {
        tabela.setModel(model);
    }

    // Listener para o botão Voltar
    public void adicionarVoltarListener(ActionListener listener) {
        voltarButton.addActionListener(listener);
    }
}