package model.entities.predios;
import java.util.HashMap;
import java.util.Map;
import model.entities.locais.Sala;
import model.entities.locais.Laboratorio;
import model.enums.TipoLaboratorio;
import model.entities.locais.Auditorio;
import model.entities.locais.SalaReuniao;
import model.entities.usuario.Usuario;

public class Predio extends UnidadeFisica{
    private Map<String,Sala> salas;
    private Map<String,Laboratorio> laboratorios;
    private Map<String,Auditorio> auditorios;
    private Map<String,SalaReuniao> salasDeReuniao;   
    
    public Predio(String nome, String descricao){
        super(nome, descricao);
        this.salas = new HashMap<>();
        this.laboratorios = new HashMap<>();
        this.auditorios = new HashMap<>();
        this.salasDeReuniao = new HashMap<>();
    }

    public Predio(int idUnidade, String nome, String descricao) {
        super(nome, descricao);
        this.idUnidade = idUnidade;
        this.salas = new HashMap<>();
        this.laboratorios = new HashMap<>();
        this.auditorios = new HashMap<>();
        this.salasDeReuniao = new HashMap<>();
    }

    //getters

    public Sala getSala(String nome){
        return salas.get(nome);
    }

    public Laboratorio getLaboratorio(String nome){
        return laboratorios.get(nome);
    }

    public Auditorio getAuditorio(String nome){
        return auditorios.get(nome);
    }

    public SalaReuniao getSalaReuniao(String nome){
        return salasDeReuniao.get(nome);
    }


    //adicionar sala
    public boolean adicionarSala(String nome, String descricao, String status, int capacidade, int quantProjetor,int quantArCondionado, Usuario u){
        if(u == null){
            return false;
        }
        if (salas.containsKey(nome)) {
            return false;
        }
        if(u.getStatusAdm()){
            Sala novaSala = new Sala(nome, descricao, status, capacidade, quantProjetor, quantArCondionado);
            salas.put(nome, novaSala);
            return true;
        }
        return false;
    }

    //adicionar laboratorio
    public boolean adicionarLaboratorio(String nome, String descricao, String status, int capacidade, int quantEquipamentos, TipoLaboratorio tipoDeLaboratorio,Usuario u){
        if(u == null){
            return false;
        }
        if (laboratorios.containsKey(nome)) {
            return false;
        }
        if(u.getStatusAdm()){
            Laboratorio novoLaboratorio = new Laboratorio(nome, descricao, status, capacidade, quantEquipamentos, tipoDeLaboratorio);
            laboratorios.put(nome, novoLaboratorio);
            return true;
        }
        return false;
    }

    //adicionar auditorio
    public boolean adicionarAuditorio(String nome, String descricao, String status, int capacidade, boolean possuiSistemaDeSom, boolean possuiPalco, Usuario u){
        if(u == null){
            return false;
        }

        if (auditorios.containsKey(nome)) {
            return false;
        }
        if(u.getStatusAdm()){
            Auditorio novoAuditorio = new Auditorio(nome, descricao, status, capacidade, possuiSistemaDeSom, possuiPalco);
            auditorios.put(nome, novoAuditorio);
            return true;
        }
        return false;
    }

    //adicionar sala de reuniao
    public boolean adicionarSalaDeReuniao(String nome, String descricao, String status, int capacidade, int quantidadeProjetor, boolean possuiVideoconferencia, boolean possuiSistemaDeSom, Usuario u){
        if(u == null){
            return false;
        }
        if (salasDeReuniao.containsKey(nome)) {
            return false;
        }
        if(u.getStatusAdm()){
            SalaReuniao novaSalaReuniao = new SalaReuniao(nome, descricao, status, capacidade, quantidadeProjetor, possuiVideoconferencia, possuiSistemaDeSom);
            salasDeReuniao.put(nome, novaSalaReuniao);
            return true;
        }
        return false;
    }

    //remover uma sala
    public boolean removerSala(String nome, Usuario u) {
        if (u == null || !u.getStatusAdm()) {
            return false;
        }
        if (!salas.containsKey(nome)) {
            return false;
        }
        salas.remove(nome);
        return true;
    }

    //remover uma laboratorio
    public boolean removerLaboratorio(String nome, Usuario u) {
        if (u == null || !u.getStatusAdm()) {
            return false;
        }
        if (!laboratorios.containsKey(nome)) {
            return false;
        }
        laboratorios.remove(nome);
        return true;
    }

    //remover uma auditorio
    public boolean removerAuditorio(String nome, Usuario u) {
        if (u == null || !u.getStatusAdm()) {
            return false;
        }
        if (!auditorios.containsKey(nome)) {
            return false;
        }
        auditorios.remove(nome);
        return true;
    }

    //remover uma sala de reuniao
    public boolean removerSalaDeReuniao(String nome, Usuario u) {
        if (u == null || !u.getStatusAdm()) {
            return false;
        }
        if (!salasDeReuniao.containsKey(nome)) {
            return false;
        }
        salasDeReuniao.remove(nome);
        return true;
    }
}
