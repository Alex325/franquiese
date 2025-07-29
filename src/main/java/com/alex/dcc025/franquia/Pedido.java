package com.alex.dcc025.franquia;

import java.time.LocalDateTime;
import java.util.List;

import com.alex.dcc025.usuario.Vendedor;
import com.alex.dcc025.util.ID;

public class Pedido {

    private final String id;
    private final Vendedor vendedor;
    private final String cliente;
    private final String dataHora;
    private final List<ItemPedido> itens;
    private final String formaPagamento;
    private final double valorTotal;
    private final String modalidadeEntrega;

    public Pedido(Vendedor vendedor, String cliente, String dataHora,
                  List<ItemPedido> itens, String formaPagamento, String modalidadeEntrega) {
        this.id = ID.getUUID();
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

    public String getId() {
        return this.id;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}

