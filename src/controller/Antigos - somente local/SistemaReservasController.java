package controller;

import model.Espaco;
import model.Reserva;
import model.Usuario;
import view.SistemaReservasView;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SistemaReservasController {
    private SistemaReservasView view;
    private Usuario usuario;
    private FluxoUsuarioController fluxoController;

    public SistemaReservasController(FluxoUsuarioController fluxoController, Usuario usuario) {
        this.fluxoController = fluxoController;
        this.usuario = usuario;
        this.view = new SistemaReservasView();

        view.getEspacosCombo().addItem("Sala 101");
        view.getEspacosCombo().addItem("Lab 201");

        view.getConfirmarButton().addActionListener(e -> {
            try {
                String espacoSelecionado = (String) view.getEspacosCombo().getSelectedItem();
                String dataStr = view.getDataField().getText();
                LocalDate data = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String horarioStr = (String) view.getHorarioCombo().getSelectedItem();
                int hora = Integer.parseInt(horarioStr.split("h")[0]);
                LocalTime horario = LocalTime.of(hora, 0);
                String finalidade = view.getFinalidadeField().getText();

                Reserva reserva = new Reserva(usuario, new Espaco(espacoSelecionado, null, 0, "", ""), data, horario, finalidade);

                JOptionPane.showMessageDialog(view, "Reserva realizada com sucesso!");
                fluxoController.abrirMinhasReservas(usuario);
                fecharSistemaReservas();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Erro ao reservar: " + ex.getMessage());
            }
        });

        view.getVoltarButton().addActionListener(e -> {
            fecharSistemaReservas();
            fluxoController.abrirDashboard(usuario);
        });

        view.getCancelarButton().addActionListener(e -> fecharSistemaReservas());
    }

    public void exibirSistemaReservas() {
        view.setVisible(true);
    }

    public void fecharSistemaReservas() {
        view.dispose();
    }
}