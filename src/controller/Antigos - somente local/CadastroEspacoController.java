package controller;

import model.Espaco;
import model.TipoEspaco;
import view.CadastroEspacoView;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CadastroEspacoController {
    private CadastroEspacoView view;

    public CadastroEspacoController() {
        view = new CadastroEspacoView();

        view.getSalvarButton().addActionListener(e -> {
            try {
                String nome = view.getNome();
                TipoEspaco tipo = TipoEspaco.valueOf(view.getTipo().toUpperCase().replace(" ", "_"));
                int capacidade = Integer.parseInt(view.getCapacidade());
                String localizacao = view.getLocalizacao();
                String descricao = view.getDescricao();

                Espaco espaco = new Espaco(nome, tipo, capacidade, localizacao, descricao);

                JOptionPane.showMessageDialog(view, "Espaço cadastrado:\n" + espaco.getNome());
                view.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Erro ao salvar espaço: " + ex.getMessage());
            }
        });

        view.getCancelarButton().addActionListener(e -> view.dispose());

        view.getLimparButton().addActionListener(e -> view.limparCampos());

        view.setVisible(true);
    }
}