package controller;

import model.Espaco;
import view.ListagemEspacosView;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ListagemEspacosController {
    private ListagemEspacosView view;
    private List<Espaco> espacos;

    public ListagemEspacosController() {
        this.view = new ListagemEspacosView();

        // Dummy lista para teste
        espacos = new ArrayList<>();
        espacos.add(new Espaco("Sala 101", model.TipoEspaco.SALA_AULA, 30, "Prédio A", "Sala com projetor"));
        espacos.add(new Espaco("Lab 201", model.TipoEspaco.LABORATORIO, 20, "Prédio B", "Laboratório de informática"));

        carregarTabela("");

        view.getBuscarButton().addActionListener(e -> {
            String busca = view.getBuscaField().getText();
            carregarTabela(busca);
        });

        view.setVisible(true);
    }

    private void carregarTabela(String filtro) {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Nome", "Tipo", "Capacidade", "Localização", "Status"}, 0);

        for (Espaco e : espacos) {
            if (filtro.isEmpty() || e.getNome().toLowerCase().contains(filtro.toLowerCase())) {
                model.addRow(new Object[]{
                        e.getNome(),
                        e.getTipo().toString(),
                        e.getCapacidade(),
                        e.getLocalizacao(),
                        e.isAtivo() ? "Ativo" : "Inativo"
                });
            }
        }

        view.setTabelaModel(model);
    }
}