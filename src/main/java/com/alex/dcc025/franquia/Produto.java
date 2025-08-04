package com.alex.dcc025.franquia;

import com.alex.dcc025.util.ID;

public class Produto {

    private String id;
    private String nome;
    private double preco;
    private String descricao;

    public Produto(String nome, double preco, String descricao) {
        this.id = ID.getUUID();
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
    }

    public Produto() {}

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getId() {
        return this.id;
    }

    public double getPreco() {
        return this.preco;
    }

    @Override
    public String toString() {
        return nome + " - " + descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }    
}

