package model.usuario;
import controller.autenticacao.SenhaUtils;

public class Usuario{
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

    //getters
    
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