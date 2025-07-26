package model;

public class Usuario {
    private String nome;
    private String username;
    private String email;
    private String senha;
    private String tipo; // "Admin" ou "Usuario"
    private boolean ativo;

    public Usuario(String nome, String username, String email, String senha, String tipo) {
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.ativo = true;
    }

    // Getters e Setters
    public String getNome() { 
        return nome; 
    }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getTipo() { return tipo; }
    public boolean isAtivo() { return ativo; }

    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}