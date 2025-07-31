package com.alex.dcc025.franquia;

public class ItemPedido {

    private Produto produto;
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ItemPedido() {}

    public Produto getProduto() {
        return this.produto;
    }

    public double getPreco() {
        return produto.getPreco();
    }

    public int getQuantidade() {
        return quantidade;
    }
}

