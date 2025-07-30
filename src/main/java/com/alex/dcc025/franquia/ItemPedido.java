package com.alex.dcc025.franquia;

public class ItemPedido {

    private final Franquia franquia;
    private Produto produto;
    private int quantidade;

    public ItemPedido(Franquia franquia, Produto produto, int quantidade) {
        this.franquia = franquia;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Franquia getFranquia() {
        return this.franquia;
    }

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

