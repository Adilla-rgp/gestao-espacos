package view;

import javax.swing.*;
import java.awt.*;

public class RelatoriosView extends JFrame {
    private JComboBox<String> tipoRelatorioCombo;
    private JButton gerarButton;
    private JTextArea resultadoArea;

    public RelatoriosView() {
        setTitle("Relatórios e Estatísticas");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Combo e botão no topo com margem
        JLabel tipoLabel = new JLabel("Tipo de Relatório:");
        tipoRelatorioCombo = new JComboBox<>(new String[]{
            "Ocupação por Espaço",
            "Usuários mais Ativos",
            "Horários de Pico",
            "Espaços Subutilizados"
        });

        gerarButton = new JButton("Gerar Relatório");

        JPanel topoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        topoPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 15));
        topoPanel.add(tipoLabel);
        topoPanel.add(tipoRelatorioCombo);
        topoPanel.add(gerarButton);

        // Área de resultado com borda e margens
        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        resultadoArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        resultadoArea.setMargin(new Insets(10, 10, 10, 10));
        resultadoArea.setLineWrap(true);
        resultadoArea.setWrapStyleWord(true);

        JScrollPane scrollArea = new JScrollPane(resultadoArea);
        scrollArea.setBorder(BorderFactory.createTitledBorder("Resultado do Relatório"));

        // Layout principal
        setLayout(new BorderLayout(10, 10));
        add(topoPanel, BorderLayout.NORTH);
        add(scrollArea, BorderLayout.CENTER);
    }

    public JComboBox<String> getTipoRelatorioCombo() {
        return tipoRelatorioCombo;
    }

    public JButton getGerarButton() {
        return gerarButton;
    }

    public JTextArea getResultadoArea() {
        return resultadoArea;
    }
}