package controller.espaco;

import controller.app.AplicacaoController;
import model.dao.EspacoDAO;
import model.dao.ReservaDAO;
import model.dao.UnidadeFisicaDAO;
import model.entities.predios.UnidadeFisica;
import view.TelaNovaReserva;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.time.format.DateTimeFormatter;

import model.entities.locais.*;
import model.entities.agenda.*;


public class NovaReservaController {
    private TelaNovaReserva telaNovaReserva;
    private AplicacaoController controller;

    public NovaReservaController(TelaNovaReserva telaNovaReserva, AplicacaoController controller){
        this.telaNovaReserva = telaNovaReserva;
        this.controller = controller;

        carregarUnidades();

        telaNovaReserva.adicionarVoltarBottonListener(e -> controller.mostrarTelaDashboard());
        //telaNovaReserva.adicionarConfirmarBottonListener(e -> confirmar());
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
        try{
            String nomeUnidade = telaNovaReserva.getUnidadeSelecionada();
            int idUnidade = UnidadeFisicaDAO.buscarIdUnidadePorNome(nomeUnidade);
            List<Local> locais = EspacoDAO.listarLocaisPorUnidade(idUnidade);
            List<String> locaisNomes = new ArrayList<>();
            for(Local local : locais){
                locaisNomes.add(local.getNome());
            }
            telaNovaReserva.atualizarComboSala(locaisNomes);
        }catch(Exception e){
            telaNovaReserva.mostrarMensagem("Erro ao carregar as salas");
        }
    }

    private void carregarHorariosDisponiveis(){
        try {
            String dataString = telaNovaReserva.getDataSelecionada().trim();

            if (dataString.isEmpty() || dataString.equalsIgnoreCase("dd/mm/aaaa")) {
                telaNovaReserva.atualizarComboHorario(new ArrayList<>());
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(dataString, formatter);

            String nomeString = telaNovaReserva.getSalaSelecionada();

            if (nomeString == null || nomeString.trim().isEmpty()) {
                telaNovaReserva.atualizarComboHorario(new ArrayList<>());
                return;
            }

            System.out.println("Data selecionada: " + data);
            System.out.println("Sala selecionada: " + nomeString);

            Local local = EspacoDAO.buscarPorNome(nomeString);

            if (local == null) {
                telaNovaReserva.mostrarMensagem("Sala não encontrada.");
                return;
            }

            List<Reserva> reservasDia = ReservaDAO.listarReservasPorDiaELocal(local.getId(), data);

            Set<Horario> horariosOcupados = new HashSet<>();
            for (Reserva reserva : reservasDia) {
                horariosOcupados.add(reserva.getHorario());
            }

            List<String> horariosLivres = new ArrayList<>();
            for (Horario h : Horario.values()) {
                if (!horariosOcupados.contains(h)) {
                    horariosLivres.add(h.getInicio() + " - " + h.getFim());
                }
            }

            telaNovaReserva.atualizarComboHorario(horariosLivres);

        } catch (Exception e) {
            telaNovaReserva.mostrarMensagem("Erro ao carregar os horários disponíveis.");
            e.printStackTrace();
        }
    }


}
