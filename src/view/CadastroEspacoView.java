package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CadastroEspacoView extends JFrame {
    private JTextField nomeField;
    private JComboBox<String> tipoCombo;
    private JTextField capacidadeField;
    private JTextField localizacaoField;
    private JTextArea descricaoArea;
    private JButton salvarButton;
    private JButton cancelarButton;
    private JButton limparButton;

    // Painel de campos dinâmicos
    private JPanel painelExtras;

    // Campos extras por tipo
    private JCheckBox projetorCheck, arCondicionadoCheck;
    private JTextField tipoLabField, equipamentosLabField;
    private JCheckBox sistemaSomCheck, palcoCheck;
    private JTextField capacidadeEspecialField;
    private JTextField tipoEsporteField;
    private JCheckBox cobertaCheck;
    private JCheckBox mesaConferenciaCheck, tvProjetorCheck;

    public CadastroEspacoView() {
        setTitle("Cadastro de Espaço");
        setSize(500, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        // Campos comuns
        nomeField = new JTextField(20);
        capacidadeField = new JTextField(5);
        localizacaoField = new JTextField(20);
        descricaoArea = new JTextArea(3, 20);

        tipoCombo = new JComboBox<>(new String[]{
            "Sala de Aula", "Laboratório", "Auditório", "Quadra", "Sala de Reunião"
        });

        tipoCombo.addActionListener(e -> atualizarCamposExtras());

        mainPanel.add(criarLinha("Nome:", nomeField));
        mainPanel.add(criarLinha("Tipo:", tipoCombo));
        mainPanel.add(criarLinha("Capacidade:", capacidadeField));
        mainPanel.add(criarLinha("Localização:", localizacaoField));
        mainPanel.add(new JLabel("Descrição:"));
        mainPanel.add(new JScrollPane(descricaoArea));

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(new JLabel("Campos específicos:"));

        // Painel para campos específicos por tipo
        painelExtras = new JPanel();
        painelExtras.setLayout(new BoxLayout(painelExtras, BoxLayout.Y_AXIS));
        mainPanel.add(painelExtras);

        // Botões
        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        salvarButton = new JButton("Salvar");
        cancelarButton = new JButton("Cancelar");
        limparButton = new JButton("Limpar");

        botoesPanel.add(salvarButton);
        botoesPanel.add(cancelarButton);
        botoesPanel.add(limparButton);

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(botoesPanel);

        add(mainPanel);
        atualizarCamposExtras(); // inicia com campos da primeira opção
    }

    private void atualizarCamposExtras() {
        painelExtras.removeAll();
        String tipo = (String) tipoCombo.getSelectedItem();

        switch (tipo) {
            case "Sala de Aula":
                projetorCheck = new JCheckBox("Projetor");
                arCondicionadoCheck = new JCheckBox("Ar condicionado");
                painelExtras.add(projetorCheck);
                painelExtras.add(arCondicionadoCheck);
                break;
            case "Laboratório":
                tipoLabField = new JTextField(20);
                equipamentosLabField = new JTextField(20);
                painelExtras.add(criarLinha("Tipo de laboratório:", tipoLabField));
                painelExtras.add(criarLinha("Equipamentos especiais:", equipamentosLabField));
                break;
            case "Auditório":
                sistemaSomCheck = new JCheckBox("Sistema de som");
                palcoCheck = new JCheckBox("Palco");
                capacidadeEspecialField = new JTextField(5);
                painelExtras.add(sistemaSomCheck);
                painelExtras.add(palcoCheck);
                painelExtras.add(criarLinha("Capacidade especial:", capacidadeEspecialField));
                break;
            case "Quadra":
                tipoEsporteField = new JTextField(20);
                cobertaCheck = new JCheckBox("Coberta");
                painelExtras.add(criarLinha("Tipo de esporte:", tipoEsporteField));
                painelExtras.add(cobertaCheck);
                break;
            case "Sala de Reunião":
                mesaConferenciaCheck = new JCheckBox("Mesa de conferência");
                tvProjetorCheck = new JCheckBox("TV/Projetor");
                painelExtras.add(mesaConferenciaCheck);
                painelExtras.add(tvProjetorCheck);
                break;
        }

        painelExtras.revalidate();
        painelExtras.repaint();
    }

    private JPanel criarLinha(String labelTexto, JComponent campo) {
        JPanel linha = new JPanel(new BorderLayout(5, 5));
        linha.add(new JLabel(labelTexto), BorderLayout.WEST);
        linha.add(campo, BorderLayout.CENTER);
        return linha;
    }

    // Getters comuns
    public String getNome() { return nomeField.getText(); }
    public String getTipo() { return (String) tipoCombo.getSelectedItem(); }
    public String getCapacidade() { return capacidadeField.getText(); }
    public String getLocalizacao() { return localizacaoField.getText(); }
    public String getDescricao() { return descricaoArea.getText(); }

    // Getters campos específicos (exemplo)
    public boolean isProjetor() { return projetorCheck != null && projetorCheck.isSelected(); }
    public boolean isArCondicionado() { return arCondicionadoCheck != null && arCondicionadoCheck.isSelected(); }
    public String getTipoLaboratorio() { return tipoLabField != null ? tipoLabField.getText() : ""; }

    public JButton getSalvarButton() { return salvarButton; }
    public JButton getCancelarButton() { return cancelarButton; }
    public JButton getLimparButton() { return limparButton; }

    public void limparCampos() {
        nomeField.setText("");
        capacidadeField.setText("");
        localizacaoField.setText("");
        descricaoArea.setText("");
        tipoCombo.setSelectedIndex(0);
        atualizarCamposExtras();
    }

    public void adicionarSalvarButtonListener(ActionListener listener){
        salvarButton.addActionListener(listener);
    }

    public void adicionarCancelarButtonListener(ActionListener listener){
        cancelarButton.addActionListener(listener);
    }

    public void adicionarLimparButtonListener(ActionListener listener){
        limparButton.addActionListener(listener);
    }
}