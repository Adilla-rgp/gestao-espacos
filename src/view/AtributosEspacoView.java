package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AtributosEspacoView extends JFrame {
    private JPanel atributosPanel;
    private JButton concluirButton;
    private JButton cancelarButton;

    // Componentes específicos
    private JSpinner spinnerProjetoresSala;
    private JSpinner spinnerArCondicionadoSala;

    private JSpinner spinnerEquipamentosLab;
    private JComboBox<String> comboTipoLab;

    private JCheckBox checkSomAuditorio;
    private JCheckBox checkPalcoAuditorio;

    private JComboBox<String> comboTipoQuadra;
    private JCheckBox checkCobertaQuadra;
    private JCheckBox checkIluminacaoQuadra;

    private JCheckBox checkVideoconferencia;
    private JCheckBox checkSomReuniao;
    private JSpinner spinnerProjetoresReuniao;

    private JCheckBox checkIluminacaoCampo;
    private JCheckBox checkVestiarioCampo;

    public AtributosEspacoView() {
        setTitle("Selecionar Atributos do Espaço");
        setSize(450, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        atributosPanel = new JPanel();
        atributosPanel.setLayout(new BoxLayout(atributosPanel, BoxLayout.Y_AXIS));
        atributosPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        concluirButton = new JButton("Concluir");
        cancelarButton = new JButton("Cancelar");

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        botoesPanel.add(cancelarButton);
        botoesPanel.add(concluirButton);

        getContentPane().add(new JScrollPane(atributosPanel), BorderLayout.CENTER);
        getContentPane().add(botoesPanel, BorderLayout.SOUTH);
    }

    public void configurarPorTipo(String tipoSelecionado) {
        atributosPanel.removeAll();

        JLabel titulo = new JLabel("Atributos disponíveis para: " + tipoSelecionado);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        atributosPanel.add(titulo);
        atributosPanel.add(Box.createVerticalStrut(15));

        adicionarCamposEspecificos(tipoSelecionado.toLowerCase());

        atributosPanel.revalidate();
        atributosPanel.repaint();
    }

    private void adicionarCamposEspecificos(String tipo) {
        switch (tipo) {
            case "sala" -> {
                spinnerProjetoresSala = adicionarSpinner("Quantidade de projetores:", 0, 100);
                spinnerArCondicionadoSala = adicionarSpinner("Quantidade de ar-condicionados:", 0, 100);
            }
            case "laboratório" -> {
                spinnerEquipamentosLab = adicionarSpinner("Quantidade de equipamentos:", 0, 500);
                comboTipoLab = adicionarComboBox("Tipo de laboratório:", new String[]{"Informatica", "Quimica", "Biologia", "Fisica"});
            }
            case "auditório" -> {
                checkSomAuditorio = adicionarCheckBox("Possui sistema de som");
                checkPalcoAuditorio = adicionarCheckBox("Possui palco");
            }
            case "quadra" -> {
                comboTipoQuadra = adicionarComboBox("Tipo de quadra:", new String[]{"Basquete", "Futsal", "Handebol", "Volei", "Tenis", "Poliesportiva"});
                checkCobertaQuadra = adicionarCheckBox("É coberta");
                checkIluminacaoQuadra = adicionarCheckBox("Possui iluminação");
            }
            case "sala de reunião" -> {
                checkVideoconferencia = adicionarCheckBox("Possui videoconferência");
                checkSomReuniao = adicionarCheckBox("Possui sistema de som");
                spinnerProjetoresReuniao = adicionarSpinner("Quantidade de projetores:", 0, 10);
            }
            case "campo" -> {
                checkIluminacaoCampo = adicionarCheckBox("Possui iluminação");
                checkVestiarioCampo = adicionarCheckBox("Possui vestiário");
            }
            default -> atributosPanel.add(new JLabel("Tipo desconhecido. Nenhum campo adicional disponível."));
        }
    }

    // Helpers que retornam os componentes criados
    private JSpinner adicionarSpinner(String label, int min, int max) {
        atributosPanel.add(new JLabel(label));
        atributosPanel.add(Box.createVerticalStrut(5));
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(min, min, max, 1));
        atributosPanel.add(spinner);
        atributosPanel.add(Box.createVerticalStrut(10));
        return spinner;
    }

    private JCheckBox adicionarCheckBox(String label) {
        JCheckBox checkBox = new JCheckBox(label);
        checkBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        atributosPanel.add(checkBox);
        atributosPanel.add(Box.createVerticalStrut(10));
        return checkBox;
    }

    private JComboBox<String> adicionarComboBox(String label, String[] options) {
        atributosPanel.add(new JLabel(label));
        atributosPanel.add(Box.createVerticalStrut(5));
        JComboBox<String> comboBox = new JComboBox<>(options);
        atributosPanel.add(comboBox);
        atributosPanel.add(Box.createVerticalStrut(10));
        return comboBox;
    }

    // Getters por tipo
    public int getQuantProjetoresSala() {
        return (Integer) spinnerProjetoresSala.getValue();
    }

    public int getQuantArCondicionadoSala() {
        return (Integer) spinnerArCondicionadoSala.getValue();
    }

    public int getQuantEquipamentosLab() {
        return (Integer) spinnerEquipamentosLab.getValue();
    }

    public String getTipoLaboratorio() {
        return (String) comboTipoLab.getSelectedItem();
    }

    public boolean isSistemaSomAuditorio() {
        return checkSomAuditorio.isSelected();
    }

    public boolean isPalcoAuditorio() {
        return checkPalcoAuditorio.isSelected();
    }

    public String getTipoQuadra() {
        return (String) comboTipoQuadra.getSelectedItem();
    }

    public boolean isCobertaQuadra() {
        return checkCobertaQuadra.isSelected();
    }

    public boolean isIluminacaoQuadra() {
        return checkIluminacaoQuadra.isSelected();
    }

    public boolean isVideoconferencia() {
        return checkVideoconferencia.isSelected();
    }

    public boolean isSistemaSomReuniao() {
        return checkSomReuniao.isSelected();
    }

    public int getQuantProjetoresReuniao() {
        return (Integer) spinnerProjetoresReuniao.getValue();
    }

    public boolean isIluminacaoCampo() {
        return checkIluminacaoCampo.isSelected();
    }

    public boolean isVestiarioCampo() {
        return checkVestiarioCampo.isSelected();
    }

    public JButton getConcluirButton() {
        return concluirButton;
    }

    public JButton getCancelarButton() {
        return cancelarButton;
    }

    public void adicionarConcluirButtonListener(ActionListener listener) {
        concluirButton.addActionListener(listener);
    }

    public void adicionarCancelarButtonListener(ActionListener listener) {
        cancelarButton.addActionListener(listener);
    }

    public void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}
