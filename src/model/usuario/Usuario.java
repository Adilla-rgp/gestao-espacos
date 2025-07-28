package model.usuario;
import controller.autenticacao.SenhaUtils;

public class Usuario{
    private int id;
    private String nome;
    private String email;
    private String senhaHash;
    private boolean isAdm;

    //metodo construtor
    public Usuario(String nome, String email, String senha){
        this.nome = nome;
        this.isAdm = false;
        this.email = email;
        this.senhaHash = SenhaUtils.gerarHash(senha);
    }

    // método construtor para carregar um usuário já existente (com id do banco)
    public Usuario(int id, String nome, String email, String senhaHash, boolean isAdm) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
        this.isAdm = isAdm;
    }

    //getters

    public int getId() {
        return id; 
    }

    public void setId(int id) { 
        this.id = id; 
    }
    
    public String getNome(){
        return this.nome;
    }

    public boolean getStatusAdm(){
        return this.isAdm;
    }

    public String getEmail(){
        return this.email;
    }

    public String getSenhaHash(){
        return this.senhaHash;
    }

    //setters

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setStatusAdm(boolean status){
        this.isAdm = status;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setSenhaHash(String senhaHash){
        this.senhaHash = senhaHash;
    }
}