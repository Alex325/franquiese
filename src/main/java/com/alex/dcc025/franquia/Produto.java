package com.alex.dcc025.franquia;

public class Produto {
    private String nome;
    private double preco;
    private String descricao;
    private int quantidade;

    public Produto(String nome, double preco, String descricao, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public boolean isEstoqueBaixo() {
        return quantidade < 5;
    }

    public double getPreco() {
        return this.preco;
    }
}

