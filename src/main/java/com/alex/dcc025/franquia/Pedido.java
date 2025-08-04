package com.alex.dcc025.franquia;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import com.alex.dcc025.usuario.Vendedor;
import com.alex.dcc025.util.ID;

public class Pedido {

    public enum FormaPagamento {
        CREDITO,
        DEBITO;

        public String toString() {
            return this.name().replace('_', ' ');
        }
    }

    public enum ModalidadeEntrega {
        ENTREGA,
        BUSCA_NA_LOJA;


        @Override
        public String toString() {
            return this.name().replace('_', ' ');
        }
    }

    public static transient final List<FormaPagamento> formas = List.of(FormaPagamento.values());
    public static transient final List<ModalidadeEntrega> modalidades = List.of(ModalidadeEntrega.values());


    private String id;
    private Vendedor vendedor;
    private String cliente;
    private LocalDateTime dataHora;
    private List<ItemPedido> itens;
    private FormaPagamento formaPagamento;
    private ModalidadeEntrega modalidadeEntrega;

    public Pedido(Vendedor vendedor, String cliente, List<ItemPedido> itens, FormaPagamento formaPagamento, ModalidadeEntrega modalidadeEntrega) {
        this.id = ID.getUUID();
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.dataHora = LocalDateTime.now();
        this.itens = itens;
        this.formaPagamento = formaPagamento;
        this.modalidadeEntrega = modalidadeEntrega;
    }

    public Pedido() {}

    public double calcularValorTotal() {
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
    
    @Override
    public String toString() {
        return "Pedido de " + cliente + " - " + String.format(Locale.getDefault(), "R$ %,.2f", calcularValorTotal());
    }

}

