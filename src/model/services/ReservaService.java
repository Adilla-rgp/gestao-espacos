package model.services;

import model.locais.*;
import model.enums.*;
import model.exceptions.*;
import model.usuario.Usuario;
import model.Agenda.Reserva;
import model.Agenda.Horario;
import java.time.*;
import java.util.*;

public class ReservaService {
    
    //atributos
    private RegraEspacoService localValidator;
    private List<Reserva> reservas; 
    private List<Usuario> usuarios;
    private List<Local> locais;
    
    public ReservaService() {
        this.localValidator = new RegraEspacoService();
        this.reservas = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.locais = new ArrayList<>();
    }
    
    // Cria uma nova reserva aplicando todas as validações logicas
    public Reserva criarReserva(String nome, String descricao, Usuario usuario, Local local, Horario horario) throws ReservaException, LocaisException, UsuarioException {
        
        // Validações basicas:
        validarUsuarioAtivo(usuario); //se o usuario esta ativo no sistema
        validarHorarioReserva(horario); //valida o horario da reserva

        // Criar reserva temporária para validações
        Reserva novaReserva = new Reserva(nome, descricao, horario, local); 

        //valida as regras especificas do local
        try {
            localValidator.validarRegrasLocal(local, novaReserva, usuario);
        } catch (LocaisException e) {
            throw e;
        }
        
    
        // Validar disponibilidade de reservas
        validarDisponibilidade(local, horario);
        
        // Validar limites do usuário     
        validarLimitesUsuario(usuario, horario);
        
        // Validar reservas simultâneas
        validarReservasSimultaneas(usuario, horario);
        
        // Se passou por todas as etapas de validação, cria a reserva
        reservas.add(novaReserva);

        return novaReserva;
    }
    
   //CANCELAR RESERVA
    public void cancelarReserva(Reserva reserva, Usuario usuarioSolicitante) throws ReservaException, UsuarioException {
        
        // Verifica se a reserva existe
        if (!reservas.contains(reserva)) {
            throw new ReservaException.ReservaNaoEncontradaException( reserva.getEspaco() + " - " + reserva.getHorarioInicio()); 
        }
        
        // Verificar permissões de cancelamento
        if (!podeCancel(reserva, usuarioSolicitante)) {
            throw new UsuarioException.UsuarioNaoPermitidoException(usuarioSolicitante.getNome());
        }
       
        validarPrazoCancelamento(reserva); // Validar prazo de cancelamento
        reservas.remove(reserva);     // Cancelar (remove da lista)
    }
    
    // Lista locais disponíveis em um horário específico
    public List<Local> listarLocaisDisponiveis(Horario horario) {
        List<Local> disponiveis = new ArrayList<>(); //lista de locais dispoiveis
        
        for (Local local : locais) {
            if ("Ativo".equalsIgnoreCase(local.getStatus()) && isLocalDisponivel(local, horario)) {
                disponiveis.add(local); //add na lista os que tiverem com status ativo e com o mesmo horario
            }
        }
        
        return disponiveis;
    }
    
    // Lista todas as reservas de um usuário
    public List<Reserva> listarReservasUsuario(Usuario usuario) {
        List<Reserva> reservasUsuario = new ArrayList<>(); //cria list de todas as reservas feitas por um usuario
        
        for (Reserva reserva : reservas) {
            if (reserva.getNome().equals(usuario.getNome())) { //se i nome assinado na reserva for igual ao nome cadastrado do usuario,
                reservasUsuario.add(reserva); //add na lista
            }
        }
        
        return reservasUsuario;
    }
    
    //LISTAR TODAS AS RESERVAS DE UM LOCAL
    public List<Reserva> listarReservasLocal(Local local) {
        List<Reserva> reservasLocal = new ArrayList<>();
        
        for (Reserva reserva : reservas) {
            if (reserva.getEspaco().equals(local.getNome())) {
                reservasLocal.add(reserva);
            }
        }
        
        return reservasLocal;
    }
    
    //BUSCAR RESERVAS POR HORARIO
    public List<Reserva> buscarReservasPorHorario(Horario horario) {
        List<Reserva> reservasHorario = new ArrayList<>(); //cria uma arraylist com as reservas
        
        for (Reserva reserva : reservas) {
            if (reserva.getHorarioInicio().equals(horario.getInicio().toString())) { //se o horario do inicio da reserva for igual ao horario marcado
                reservasHorario.add(reserva); //add na lista
            }
        }
        
        return reservasHorario;
    }
    
    //CONFLITO ENTRE DUAS RESERVAS
    public boolean verificarConflito(Reserva reserva1, Reserva reserva2) {
        return reserva1.conflita(reserva2) && reserva1.getEspaco().equals(reserva2.getEspaco());
    }
    // MÉTODOS PRIVADOS DE VALIDAÇAO
    
    //valida se o usuario esta ativo
    private void validarUsuarioAtivo(Usuario usuario) throws UsuarioException.UsuarioInativoException {
        if (!usuario.isAtivo()) {
            throw new UsuarioException.UsuarioInativoException(usuario.getNome());
        }
    }
    
    private void validarHorarioReserva(Horario horario) throws ReservaException.DataInvalidaException {
        if (horario == null) {
            //se o horario for nulo, barra a reserva
            throw new ReservaException.DataInvalidaException("Horário não pode ser nulo");
        }
        
        // Validar antecedência máxima usando o horário atual
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime dataReserva = LocalDateTime.of(LocalDate.now(), horario.getInicio());
        
        //tenta ver o prazo de antecedencia
        try {
            localValidator.validarAntecedenciaMaxima(dataReserva);
        } catch (ReservaException e) {
            throw new ReservaException.DataInvalidaException(e.getMessage()); //passa o waning de data invalida
        }
    }
    
    //valida a disponibilidade do local
    private void validarDisponibilidade(Local local, Horario horario) throws ReservaException.HorarioIndisponivelException {
        
        if (!isLocalDisponivel(local, horario)) {
            throw new ReservaException.HorarioIndisponivelException(local.getNome(), horario.getInicio().toString());
        }
    }
    
    //verifica se o local esta disponivel
    private boolean isLocalDisponivel(Local local, Horario horario) {
        for (Reserva reserva : reservas) {
            if (reserva.getEspaco().equals(local.getNome()) && reserva.getHorarioInicio().equals(horario.getInicio().toString())) {
                return false;
            }
        }
        return true;
    }
    
    //LImita a quantidade de reservas feitas pelo usuario
    private void validarLimitesUsuario(Usuario usuario, Horario horario) throws ReservaException.CapacidadeExcedidaException {
        
        int reservasDoUsuario = 0;
        for (Reserva reserva : reservas) {
            if (reserva.getNome().equals(usuario.getNome())) {
                reservasDoUsuario++;
            }
        }
        
        if (reservasDoUsuario >= 3) { // Limite de 3 reservas por usuário
            throw new ReservaException.CapacidadeExcedidaException(3, reservasDoUsuario + 1);
        }
    }


    //processa reservas feitas simultaneamente se o o local e os horarios forem diferentes
    private void validarReservasSimultaneas(Usuario usuario, Horario horario) throws ReservaException.HorarioIndisponivelException {
        
        for (Reserva reserva : reservas) {
            if (reserva.getNome().equals(usuario.getNome()) && reserva.getHorarioInicio().equals(horario.getInicio().toString())) {
                throw new ReservaException.HorarioIndisponivelException("Usuario já possui reserva", horario.getInicio().toString());
            }
        }
    }
    
    // Verifica se é o mesmo usuário que fez a reserva ou se é admin
    private boolean podeCancel(Reserva reserva, Usuario usuario) {
        return reserva.getNome().equals(usuario.getNome()) || "Admin".equalsIgnoreCase(usuario.getTipo());
    }
    
    //Validação do prazo de cancelamento
    private void validarPrazoCancelamento(Reserva reserva) throws ReservaException {
    
        LocalTime horarioReserva = LocalTime.parse(reserva.getHorarioInicio());
        LocalTime agora = LocalTime.now();
        
        // Se o horário da reserva já passou, não pode cancelar
        if (agora.isAfter(horarioReserva)) {
            throw new ReservaException("Não é possível cancelar reserva que já iniciou");
        }
    }
    
    // Métodos para gerenciar listas
    public void adicionarLocal(Local local) { locais.add(local); }
    
    public void adicionarUsuario(Usuario usuario) { usuarios.add(usuario); }
    
    public List<Reserva> getReservas() { return new ArrayList<>(reservas); }
    
    public List<Local> getLocais() { return new ArrayList<>(locais); }
    
    public List<Usuario> getUsuarios() { return new ArrayList<>(usuarios); }
    
    // Método para buscar local por nome
    public Local buscarLocalPorNome(String nome) throws LocaisException.EspacoNaoEncontradoException {
        for (Local local : locais) {
            if (local.getNome().equals(nome)) {
                return local;
            }
        }
        throw new LocaisException.EspacoNaoEncontradoException(nome);
    }
    
    // Método para buscar usuário por nome
    public Usuario buscarUsuarioPorNome(String nome) throws UsuarioException.UsuarioNaoEncontradoException {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(nome)) {
                return usuario;
            }
        }
        throw new UsuarioException.UsuarioNaoEncontradoException(nome);
    }
}