package com.alex.dcc025.franquia;

import java.time.LocalDateTime;
import java.util.List;

import com.alex.dcc025.usuario.Vendedor;

public class Pedido {
    private Vendedor vendedor;
    private String cliente;
    private String dataHora;
    private List<ItemPedido> itens;
    private String formaPagamento;
    private double valorTotal;
    private String modalidadeEntrega;

    public Pedido(Vendedor vendedor, String cliente, String dataHora,
                  List<ItemPedido> itens, String formaPagamento, String modalidadeEntrega) {
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.dataHora = dataHora;
        this.itens = itens;
        this.formaPagamento = formaPagamento;
        this.modalidadeEntrega = modalidadeEntrega;
        this.valorTotal = calcularValorTotal();
    }

    private double calcularValorTotal() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.getPreco() * item.getQuantidade();
        }
        // taxa de entrega etc. podem ser adicionadas
        return total;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}

