package model.predios;
import java.util.HashMap;
import java.util.Map;
import model.locais.Sala;
import model.usuario.Usuario;
import model.locais.Quadra;
import model.locais.Campo;

public class NucleoEsportivo extends UnidadeFisica {
    private Map<String,Sala> salas;
    private Map<String,Quadra> quadras;
    private Map<String, Campo> campos;

    //metodo construtor
    public  NucleoEsportivo(String nome, String descricao){
        super(nome, descricao);
        this.salas = new HashMap<>();
        this.quadras = new HashMap<>();
        this.campos = new HashMap<>();    
    }

    //getters

    public Sala getSala(String nome){
        return salas.get(nome);
    }

    public Quadra getQuadra(String nome){
        return quadras.get(nome);
    }

    public Campo getCampo(String nome){
        return campos.get(nome);
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

    //adicionar quadra
    public boolean adicionarQuadra(String nome, String descricao, String status, int capacidade, String tipoDeQuadra, boolean ehCoberta, boolean possuiIluminacao, Usuario u){
        if(u == null){
            return false;
        }
        if (quadras.containsKey(nome)) {
            return false;
        }
        if(u.getStatusAdm()){
            Quadra novaQuadra = new Quadra(nome, descricao, status, capacidade, tipoDeQuadra, ehCoberta, possuiIluminacao);
            quadras.put(nome, novaQuadra);
            return true;
        }
        return false;
    }

    //adicionar campo
    public boolean adicionarCampo(String nome, String descricao, String status, int capacidade, boolean possuiIluminacao, boolean possuiVestiario, Usuario u){
        if(u == null){
            return false;
        }
        if (campos.containsKey(nome)) {
            return false;
        }
        if(u.getStatusAdm()){
            Campo novoCampo = new Campo(nome, descricao, status, capacidade, possuiIluminacao, possuiVestiario);
            campos.put(nome, novoCampo);
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

    //remover uma quadra
    public boolean removerQuadra(String nome, Usuario u) {
        if (u == null || !u.getStatusAdm()) {
            return false;
        }
        if (!quadras.containsKey(nome)) {
            return false;
        }
        quadras.remove(nome);
        return true;
    }

    //remover um campo
    public boolean removerCampo(String nome, Usuario u) {
        if (u == null || !u.getStatusAdm()) {
            return false;
        }
        if (!campos.containsKey(nome)) {
            return false;
        }
        campos.remove(nome);
        return true;
    }
}
