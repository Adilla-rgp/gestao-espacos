package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class AtributosEspacoView extends JFrame {
    private JPanel atributosPanel;
    private JButton concluirButton;
    private JButton voltarButton;
    private Map<String, String[]> atributosPorTipo;
    private JCheckBox[] checkBoxes;

    public AtributosEspacoView(String tipoSelecionado) {
        setTitle("Selecionar Atributos do Espaço");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        atributosPorTipo = carregarAtributosPorTipo();

        atributosPanel = new JPanel();
        atributosPanel.setLayout(new BoxLayout(atributosPanel, BoxLayout.Y_AXIS));
        atributosPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        JLabel titulo = new JLabel("Atributos disponíveis para: " + tipoSelecionado);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        atributosPanel.add(titulo);
        atributosPanel.add(Box.createVerticalStrut(10));

        String[] atributos = atributosPorTipo.getOrDefault(tipoSelecionado, new String[]{"Sem atributos disponíveis"});
        checkBoxes = new JCheckBox[atributos.length];
        for (int i = 0; i < atributos.length; i++) {
            checkBoxes[i] = new JCheckBox(atributos[i]);
            atributosPanel.add(checkBoxes[i]);
        }

        atributosPanel.add(Box.createVerticalStrut(20));
        concluirButton = new JButton("Concluir");
        voltarButton = new JButton("Voltar");

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botoesPanel.add(concluirButton);
        botoesPanel.add(voltarButton);
        atributosPanel.add(botoesPanel);

        add(atributosPanel);
    }

    private Map<String, String[]> carregarAtributosPorTipo() {
        Map<String, String[]> mapa = new HashMap<>();
        mapa.put("Sala de Aula", new String[]{"Projetor", "Quadro branco", "Ar condicionado"});
        mapa.put("Laboratório", new String[]{"Bancadas", "Equipamentos técnicos", "Exaustor"});
        mapa.put("Auditório", new String[]{"Sistema de som", "Palco", "Iluminação especial"});
        mapa.put("Quadra", new String[]{"Coberta", "Marcações esportivas", "Arquibancadas"});
        mapa.put("Sala de Reunião", new String[]{"Mesa de conferência", "TV/Projetor", "Sistema de videoconferência"});
        return mapa;
    }

    public JButton getConcluirButton() { return concluirButton; }
    public JButton getVoltarButton() { return voltarButton; }

    public java.util.List<String> getAtributosSelecionados() {
        java.util.List<String> selecionados = new java.util.ArrayList<>();
        for (JCheckBox box : checkBoxes) {
            if (box.isSelected()) {
                selecionados.add(box.getText());
            }
        }
        return selecionados;
    }

    public void adicionarConcluirListener(ActionListener listener) {
        concluirButton.addActionListener(listener);
    }

    public void adicionarVoltarListener(ActionListener listener) {
        voltarButton.addActionListener(listener);
    }
}