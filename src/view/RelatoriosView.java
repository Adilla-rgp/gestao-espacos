package view;

import javax.swing.*;
import java.awt.*;

public class RelatoriosView extends JFrame {
    private JComboBox<String> tipoRelatorioCombo;
    private JTextField dataInicioField;
    private JTextField dataFimField;
    private JButton gerarButton;
    private JTable tabelaDados;
    private JButton exportarCSVButton;
    private JButton exportarPDFButton;

    public RelatoriosView() {
        setTitle("Relatórios e Estatísticas");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Filtros
        JPanel filtrosPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        tipoRelatorioCombo = new JComboBox<>(new String[]{
            "Ocupação por Espaço", "Usuários Mais Ativos",
            "Horários de Pico", "Espaços Subutilizados"
        });
        dataInicioField = new JTextField("dd/mm/aaaa");
        dataFimField = new JTextField("dd/mm/aaaa");
        gerarButton = new JButton("Gerar Relatório");

        filtrosPanel.setBorder(BorderFactory.createTitledBorder("Filtros"));
        filtrosPanel.add(new JLabel("Tipo de Relatório:"));
        filtrosPanel.add(tipoRelatorioCombo);
        filtrosPanel.add(new JLabel(""));
        filtrosPanel.add(new JLabel("Data Início:"));
        filtrosPanel.add(dataInicioField);
        filtrosPanel.add(dataFimField);

        // Tabela de dados
        tabelaDados = new JTable();
        JScrollPane scroll = new JScrollPane(tabelaDados);

        // Botões de exportação
        JPanel exportPanel = new JPanel(new FlowLayout());
        exportarCSVButton = new JButton("Exportar CSV");
        exportarPDFButton = new JButton("Exportar PDF");
        exportPanel.add(exportarCSVButton);
        exportPanel.add(exportarPDFButton);

        // Montagem final
        add(filtrosPanel, BorderLayout.NORTH);
        add(gerarButton, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);
        add(exportPanel, BorderLayout.PAGE_END);
    }

    // Getters para o Controller usar
    public JComboBox<String> getTipoRelatorioCombo() { return tipoRelatorioCombo; }
    public JTextField getDataInicioField() { return dataInicioField; }
    public JTextField getDataFimField() { return dataFimField; }
    public JButton getGerarButton() { return gerarButton; }
    public JTable getTabelaDados() { return tabelaDados; }
    public JButton getExportarCSVButton() { return exportarCSVButton; }
    public JButton getExportarPDFButton() { return exportarPDFButton; }
}