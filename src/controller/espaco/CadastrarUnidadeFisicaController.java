package controller.espaco;

import controller.app.AplicacaoController;
import view.CadastroUnidadeFisicaView;
import model.entities.predios.UnidadeFisica;
import model.dao.UnidadeFisicaDAO;
import java.util.ArrayList;
import java.util.List;

public class CadastrarUnidadeFisicaController {
    private CadastroUnidadeFisicaView telaCadastrarUnidFisica;
    private AplicacaoController controller;

    public CadastrarUnidadeFisicaController(CadastroUnidadeFisicaView telaCadastrarUnidFisica, AplicacaoController controller){
        this.telaCadastrarUnidFisica = telaCadastrarUnidFisica;
        this.controller = controller;

        telaCadastrarUnidFisica.adicionarCancelarListener(e -> controller.mostrarTelaADMDashboard());
        telaCadastrarUnidFisica.adicionarConfirmarListener(e -> confimar());
    }

    private void confimar(){
        String nomeUnidadeFisica = telaCadastrarUnidFisica.getNomeUnidadeFIsica();
        String descricao = telaCadastrarUnidFisica.getDescricaoTipo();
        String tipoUnidadeFisica = telaCadastrarUnidFisica.getTipo();
        if(!nomeUnidadeFisica.isEmpty()){
            UnidadeFisica unidadeFisca = new UnidadeFisica(nomeUnidadeFisica, descricao);
            int id = UnidadeFisicaDAO.inserir(unidadeFisca, tipoUnidadeFisica);
            if(id != -1){
                telaCadastrarUnidFisica.mostrarMensagem("Unidade fisica cadastrada com sucesso, id: " + id);
            }else{
                telaCadastrarUnidFisica.mostrarMensagem("Erro ao cadastrar unidadeFisica");
            }
        }else{
            telaCadastrarUnidFisica.mostrarMensagem("Preencha o campo de nome");
        }
        controller.mostrarTelaADMDashboard();
    }
}
