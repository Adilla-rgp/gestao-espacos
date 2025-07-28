package model.services;

import model.entities.locais.*;
import model.enums.*;
import model.exceptions.*;
import model.entities.usuario.Usuario;
import model.entities.agenda.Reserva;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RegraEspacoService {

    // Horários de funcionamento :
    private static final LocalTime INICIO_SEMANA = LocalTime.of(7, 0);
    private static final LocalTime FIM_SEMANA = LocalTime.of(22, 0);
    private static final LocalTime INICIO_FDS = LocalTime.of(8, 0);
    private static final LocalTime FIM_FDS = LocalTime.of(18, 0);

    // Valida todas as regras específicas de um local para uma reserva
    public void validarRegrasLocal(Local local, Reserva reserva, Usuario usuario) throws LocaisException, ReservaException {

        // Validações gerais-
        validarLocalAtivo(local);
        validarHorarioFuncionamento(local, reserva);
        validarCapacidade(local, reserva);

        // Validações específicas por tipo de local
        if (local instanceof Sala) {
            validarSalaAula((Sala) local, reserva, usuario);
        } else if (local instanceof SalaReuniao) {
            validarSalaReuniao((SalaReuniao) local, reserva, usuario);
        } else if (local instanceof Laboratorio) {
            validarLaboratorio((Laboratorio) local, reserva, usuario);
        } else if (local instanceof Auditorio) {
            validarAuditorio((Auditorio) local, reserva, usuario);
        } else if (local instanceof Quadra) {
            validarQuadra((Quadra) local, reserva, usuario);
        } else if (local instanceof Campo) {
            validarCampo((Campo) local, reserva, usuario);
        }
    }

    //valida se o local desejado está disponível
    private void validarLocalAtivo(Local local) throws LocaisException.EspacoInativoException {
        if (!"Ativo".equalsIgnoreCase(local.getStatus())) {
            throw new LocaisException.EspacoInativoException(local.getNome()); //joga a mensagem de erro
        }
    }

    //valida o horario de funcionamento
    private void validarHorarioFuncionamento(Local local, Reserva reserva) throws LocaisException.HorarioFuncionamentoExcedidoException {

        LocalTime horaReserva = LocalTime.parse(reserva.getHorarioInicio()); //registra o horario da reserva
    
        if (horaReserva.isBefore(INICIO_SEMANA) || horaReserva.isAfter(FIM_SEMANA)) { //informa a incompatibilidade do horario
            String horarioPermitido = INICIO_SEMANA + " às " + FIM_SEMANA;
            throw new LocaisException.HorarioFuncionamentoExcedidoException(horarioPermitido);
        }
    }

    //valida a capacidade de pessoas no espaço
    private void validarCapacidade(Local local, Reserva reserva) throws LocaisException.CapacidadeInsuficienteException {
        if (local.getCapacidade() <= 0) {
            throw new LocaisException.CapacidadeInsuficienteException(local.getCapacidade(), 1); //se for menor que zero, dispara o warning
        }
    }

    //Validações específicas por tipo de local\\\\\\\\\\\\\\\\\\\

    //SALA DE AULA
    /*Validação: verifica se há ar condicionados e projetores */
    private void validarSalaAula(Sala sala, Reserva reserva, Usuario usuario) throws LocaisException.RecursosIndisponiveisException {
        if (sala.getQuantProjetor() <= 0) {
            throw new LocaisException.RecursosIndisponiveisException("Projetor");
        }
        if (sala.getQuantArCondicionado() <=0) {
            throw new LocaisException.RecursosIndisponiveisException("Ar-condicionado");
        }
    }

    //SALA DE REUNIAO:
    /*Validação: verifica se a sala possui aparelhos para video conferencia e a sua quantidade de projetores */
    private void validarSalaReuniao(SalaReuniao salaReuniao, Reserva reserva, Usuario usuario) throws LocaisException.RecursosIndisponiveisException {
      
        if (!salaReuniao.getPossuiVideoconferencia()) {
            throw new LocaisException.RecursosIndisponiveisException("Videoconferência");
        }
        if (salaReuniao.getQuantProjetor() <= 0) {
            throw new LocaisException.RecursosIndisponiveisException("Projetor");
        }
    }

    //LABORATORIO
    /*Validação: verifica se há equipamentos suficientes  para a quantidade desejada de alunos */
    private void validarLaboratorio(Laboratorio laboratorio, Reserva reserva, Usuario usuario) throws LocaisException.RecursosIndisponiveisException {
 
        if (!laboratorio.equipamentosSuficientes(laboratorio.getCapacidade())) {
            throw new LocaisException.RecursosIndisponiveisException("Equipamentos insuficientes");
        }
    }


    //AUDITORIO
    /*Validação: Verifica se possui sonoplastia e se há palco */
    private void validarAuditorio(Auditorio auditorio, Reserva reserva, Usuario usuario) throws LocaisException.RecursosIndisponiveisException {
        if (!auditorio.getPossuiSistemaDeSom()) {
            throw new LocaisException.RecursosIndisponiveisException("Sistema de som");
        }
        if (!auditorio.getPossuiPalco()) {
            throw new LocaisException.RecursosIndisponiveisException("Palco");
        }
    }

    //QUADRA
    /*Validação: Se o horario se enquadra no turno noturno, mas o local não possui estrutura pra isso */

    private void validarQuadra(Quadra quadra, Reserva reserva, Usuario usuario)
            throws LocaisException.RecursosIndisponiveisException {
    
        LocalTime hora = LocalTime.parse(reserva.getHorarioInicio());
        if (hora.isAfter(LocalTime.of(18, 0)) && !quadra.permiteUsoNoturno()) {
            throw new LocaisException.RecursosIndisponiveisException("Iluminação noturna");
        }
    }

    //CAMPO
    /*Validação: Se o horario se enquadra no turno noturno, mas o local não possui estrutura pra isso; se não possui vestiário */
    private void validarCampo(Campo campo, Reserva reserva, Usuario usuario) throws LocaisException.RecursosIndisponiveisException {

        LocalTime hora = LocalTime.parse(reserva.getHorarioInicio());
        if (hora.isAfter(LocalTime.of(18, 0)) && !campo.getPossuiIluminacao()) {
            throw new LocaisException.RecursosIndisponiveisException("Iluminação noturna");
        }
  
        if (!campo.getPossuiVestiario()) {
            throw new LocaisException.RecursosIndisponiveisException("Vestiário");
        }
    }

    //VERIFICA ANTECEDÊNCIA MÁXIMA: Barra os usuarios de reservar com mais de 30 dias

    public void validarAntecedenciaMaxima(LocalDateTime dataReserva) throws ReservaException {
        LocalDateTime agora = LocalDateTime.now();
        if (dataReserva.isAfter(agora.plusDays(30))) {
            throw new ReservaException("Não é possível reservar com mais de 30 dias de antecedência");
        }
    }
}