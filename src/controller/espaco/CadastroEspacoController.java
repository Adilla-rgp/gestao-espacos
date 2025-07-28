package controller.espaco;

import view.CadastroEspacoView;
import controller.app.AplicacaoController;

public class CadastroEspacoController {
    private CadastroEspacoView cadastroEspacoView;
    private AplicacaoController controller;

    public CadastroEspacoController(CadastroEspacoView cadastroEspacoView, AplicacaoController controller){
        this.cadastroEspacoView = cadastroEspacoView;
        this.controller = controller;

        cadastroEspacoView.adicionarCancelarButtonListener(e -> controller.mostrarTelaDashboard());
    }
}
