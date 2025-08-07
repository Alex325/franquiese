/*
 * Alex Sandro de Macedo Pinto
 * 202465551C
 */
package com.alex.dcc025.usuario;

import com.alex.dcc025.exception.CpfInvalidoException;
import com.alex.dcc025.exception.EmailInvalidoException;
import com.alex.dcc025.exception.CampoTextoInvalidoException;
import com.alex.dcc025.exception.SenhaInvalidaException;
import com.alex.dcc025.util.ID;
import com.alex.dcc025.util.Validador;

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

    
    public void setEmail(String novoEmail) {
        this.email = novoEmail;
    }
    
    public void setSenha(String novaSenha) {
        this.senha = novaSenha;
    }
    
    public void setCpf(String novoCpf) {
        this.cpf = novoCpf;
    }
    
    public void setNome(String novoNome) {
        this.nome = novoNome;
    }
    
    public static void validarUsuario(String nome, String cpf, String email, String senha) throws Exception {
        
        if (!Validador.validarCampoTexto(nome)) throw new CampoTextoInvalidoException("Nome deve ser caracteres");
        if (!Validador.validarCpf(cpf)) throw new CpfInvalidoException("CPF deve ser válido");
        if (!Validador.validarEmail(email)) throw new EmailInvalidoException("Email deve ser válido");
        if (!Validador.validarSenha(senha)) throw new SenhaInvalidaException("Senha não deve ser vazia");
        


    }

}
