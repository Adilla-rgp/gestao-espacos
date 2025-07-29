package controller.espaco;

import controller.app.AplicacaoController;
import model.dao.UnidadeFisicaDAO;
import model.entities.predios.UnidadeFisica;
import view.TelaNovaReserva;
import java.util.ArrayList;
import java.util.List;


public class NovaReservaController {
    private TelaNovaReserva telaNovaReserva;
    private AplicacaoController controller;

    public NovaReservaController(TelaNovaReserva telaNovaReserva, AplicacaoController controller){
        this.telaNovaReserva = telaNovaReserva;
        this.controller = controller;

        carregarUnidades();

        telaNovaReserva.adicionarVoltarBottonListener(e -> controller.mostrarTelaDashboard());
        telaNovaReserva.adicionarConfirmarBottonListener(e -> confirmar());
        telaNovaReserva.getComboUnidade().addActionListener(e -> carregarComboLocais());
        telaNovaReserva.getComboSala().addActionListener(e -> carregarHorariosDisponiveis());
    }
    
    private void carregarUnidades(){
        List<UnidadeFisica> listaUnidadesFisicas = UnidadeFisicaDAO.listarTodas();
        List<String> listaUnidadeFisicaNome = new ArrayList<>();
        for(UnidadeFisica unidadeFisica : listaUnidadesFisicas){
            String nomeUnidade = unidadeFisica.getNome();
            listaUnidadeFisicaNome.add(nomeUnidade);
        }
        telaNovaReserva.atualizarComboUnidade(listaUnidadeFisicaNome);
    }
    
    private void carregarComboLocais(){
        String unidadeSelecionada = (String) telaNovaReserva.getComboUnidade().getSelectedItem();
        if (unidadeSelecionada != null) {
            carregarSalas(unidadeSelecionada);
        }     
    }
    
    private void carregarSalas(String unidadeSelecionada){
        //List<String> salas = SalaDAO.listarSalasPorUnidade(nomeUnidade);
        //telaNovaReserva.atualizarComboSala(salas);
    }

    private void carregarHorariosDisponiveis(){
        
    }

    private void confirmar(){
        
    }
}
