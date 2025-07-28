package com.alex.dcc025.usuario;

public abstract class Usuario {
    
    protected final int id;
    protected final int tipo;
    protected final String nome;
    protected final String cpf;
    protected final String email;
    protected final String senha;
    
    public Usuario(int id, int tipo, String nome, String cpf, String email, String senha) {
        this.id = id;
        this.tipo = tipo;
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

    public int getTipo() {
        return tipo;
    }

    
}
