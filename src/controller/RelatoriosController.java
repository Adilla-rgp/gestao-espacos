package controller;

import view.RelatoriosView;

public class RelatoriosController {
    private RelatoriosView view;

    public RelatoriosController() {
        this.view = new RelatoriosView();

        view.getGerarButton().addActionListener(e -> {
            String tipo = (String) view.getTipoRelatorioCombo().getSelectedItem();
            // Dummy texto para mostrar
            String resultado = "Relatório: " + tipo + "\n\n[Dados de exemplo aqui]";
            view.getResultadoArea().setText(resultado);
        });

        view.setVisible(true);
    }
}