package com.alex.dcc025.franquia;

import com.alex.dcc025.util.ID;

public class Produto {

    private String id;
    private String nome;
    private double preco;
    private String descricao;
    private int quantidade;

    public Produto(String nome, double preco, String descricao, int quantidade) {
        this.id = ID.getUUID();
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public Produto(String id, String nome, double preco, String descricao, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public boolean isEstoqueBaixo() {
        return quantidade < 5;
    }

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
}

