package com.alex.dcc025.usuario;

import com.alex.dcc025.util.ID;

public abstract class Usuario {
    
    protected String id;
    protected String nome;
    protected String cpf;
    protected String email;
    protected String senha;
    
    public Usuario(String nome, String cpf, String email, String senha) {
        this.id = ID.getUUID();
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public Usuario() {

    }

    public final String getId() {
        return this.id;
    }
    public final String getNome() {
        return this.nome;
    }
    public final String getCpf() {
        return this.cpf;
    }
    public final String getEmail() {
        return this.email;
    }
    public final String getSenha() {
        return this.senha;
    }

    public boolean validar(String email, String senha) {
        if (!(email.equals(this.email) && (senha.equals(this.senha)))) return false;
        return true;
    }

    public abstract int getTipo();

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(this.nome);
        string.append(" - ");
        string.append(this.email);
        return string.toString();
    }
    
}
