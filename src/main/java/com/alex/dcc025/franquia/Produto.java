package com.alex.dcc025.franquia;

import com.alex.dcc025.util.ID;

public class Produto {

    private final String id;
    private final String nome;
    private final double preco;
    private final String descricao;
    private int quantidade;

    public Produto(String nome, double preco, String descricao, int quantidade) {
        this.id = ID.getUUID();
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

