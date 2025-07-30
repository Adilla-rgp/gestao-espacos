package controller.espaco;

import controller.app.AplicacaoController;
import view.CadastroEspacoView;

public class CadastrarEspacoController {
    private CadastroEspacoView telaCadastroEspaco;
    private AplicacaoController controller;

    public CadastrarEspacoController(CadastroEspacoView telaCadastroEspacoView, AplicacaoController controller){
        this.telaCadastroEspaco = telaCadastroEspacoView;
        this.controller = controller;

        telaCadastroEspaco.adicionarCancelarButtonListener(e -> controller.mostrarTelaADMDashboard());

    }
}
