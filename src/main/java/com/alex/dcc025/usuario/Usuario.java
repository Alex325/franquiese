package com.alex.dcc025.usuario;

public abstract class Usuario {
    
    protected final String id;
    protected final String nome;
    protected final String cpf;
    protected final String email;
    protected final String senha;
    
    public Usuario(String id, String nome, String cpf, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return this.nome;
    }

    public boolean validar(String email, String senha) {
        if (!(email.equals(this.email) && (senha.equals(this.senha)))) return false;
        return true;
    }

    public abstract int getTipo();

    public abstract void savePropriedades();

    
}
