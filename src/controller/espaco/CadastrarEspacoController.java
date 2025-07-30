package controller.espaco;

import java.util.ArrayList;
import java.util.List;

import controller.app.AplicacaoController;
import model.dao.UnidadeFisicaDAO;
import model.entities.predios.UnidadeFisica;
import view.CadastroEspacoView;
import model.dto.EspacoDTO;

public class CadastrarEspacoController {
    private CadastroEspacoView telaCadastroEspaco;
    private AplicacaoController controller;

    public CadastrarEspacoController(CadastroEspacoView telaCadastroEspacoView, AplicacaoController controller){
        this.telaCadastroEspaco = telaCadastroEspacoView;
        this.controller = controller;

        carregarUnidadesDisponiveis();

        telaCadastroEspaco.adicionarCancelarButtonListener(e -> controller.mostrarTelaADMDashboard());
        telaCadastroEspaco.adicionarContinuarButtonListener(e -> continuarCadastro());
        telaCadastroEspaco.getComboUnidade().addActionListener(e ->carregarUnidadesDisponiveis());

    }

    private void continuarCadastro(){
        String nome = telaCadastroEspaco.getNome();
        if(!nome.isEmpty()){
            String tipoEspaco = telaCadastroEspaco.getTipo();
            String descricao = telaCadastroEspaco.getDescricao();
            String capacidade = telaCadastroEspaco.getCapacidade();
            String unidadeFIsica = telaCadastroEspaco.getUnidade();
            EspacoDTO espaco = new EspacoDTO(nome, tipoEspaco, descricao, capacidade, unidadeFIsica);
    
            controller.receberDadosBasicos(espaco);
        }else{
           telaCadastroEspaco.mostrarMensagem("Preencha o campo nome");
        }
    }

    private void carregarUnidadesDisponiveis(){
        List<UnidadeFisica> listaUnidadesFisicas = UnidadeFisicaDAO.listarTodas();
        List<String> listaUnidadeFisicaNome = new ArrayList<>();
        for(UnidadeFisica unidadeFisica : listaUnidadesFisicas){
            String nomeUnidade = unidadeFisica.getNome();
            listaUnidadeFisicaNome.add(nomeUnidade);
        }
        telaCadastroEspaco.atualizarComboUnidade(listaUnidadeFisicaNome);
    }
}
