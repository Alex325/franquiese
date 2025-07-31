package com.alex.dcc025.franquia;

import java.time.LocalDateTime;
import java.util.List;

import com.alex.dcc025.usuario.Vendedor;
import com.alex.dcc025.util.ID;

public class Pedido {

    private String id;
    private Vendedor vendedor;
    private String cliente;
    private LocalDateTime dataHora;
    private List<ItemPedido> itens;
    private FormaPagamento formaPagamento;
    private ModalidadeEntrega modalidadeEntrega;
    private double valorTotal;

    public Pedido(Vendedor vendedor, String cliente, List<ItemPedido> itens, FormaPagamento formaPagamento, ModalidadeEntrega modalidadeEntrega) {
        this.id = ID.getUUID();
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.dataHora = LocalDateTime.now();
        this.itens = itens;
        this.formaPagamento = formaPagamento;
        this.modalidadeEntrega = modalidadeEntrega;
        this.valorTotal = calcularValorTotal();
    }

    public Pedido() {}

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

    public final String getCliente() {
        return this.cliente;
    }
    public final LocalDateTime getDataHora() {
        return this.dataHora;
    }
    public final List<ItemPedido> getItens() {
        return this.itens;
    }
    public final FormaPagamento getFormaPagamento() {
        return this.formaPagamento;
    }
    public final ModalidadeEntrega getModalidadeEntrega() {
        return this.modalidadeEntrega;
    }
    

}

