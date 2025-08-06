package com.alex.dcc025.franquia.pedido;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.alex.dcc025.exception.CampoTextoInvalidoException;
import com.alex.dcc025.exception.PedidoVazioException;
import com.alex.dcc025.usuario.usuario.Vendedor;
import com.alex.dcc025.util.ID;
import com.alex.dcc025.util.Validador;

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

    private boolean solicitacaoPendente = false;

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

    public boolean getPendente() {
        return this.solicitacaoPendente;
    }

    public void setPendente(boolean pendente) {
        this.solicitacaoPendente = pendente;
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
    
    public final String getDataHora() {
        return this.dataHora.format(DateTimeFormatter.ofPattern("d/MMM/uuuu HH:mm:ss"));
    }

    public final List<ItemPedido> getItens() {
        return List.copyOf(this.itens);
    }

    public final FormaPagamento getFormaPagamento() {
        return this.formaPagamento;
    }

    public final ModalidadeEntrega getModalidadeEntrega() {
        return this.modalidadeEntrega;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void setModalidadeEntrega(ModalidadeEntrega modalidadeEntrega) {
        this.modalidadeEntrega = modalidadeEntrega;
    }
    
    @Override
    public String toString() {
        return "Pedido de " + cliente + " - " + String.format(Locale.getDefault(), "R$ %,.2f", calcularValorTotal()) + " - " + this.getDataHora();
    }

    public static void validarPedido(String cliente, List<ItemPedido> itens) throws Exception {
        if (!Validador.validarCampoTexto(cliente)) throw new CampoTextoInvalidoException("Cliente deve ser composto de caracteres");
        if (itens.size() == 0) throw new PedidoVazioException("Pedido não pode ser vazio");
    }

    public static void validarPedido(List<ItemPedido> itens) throws Exception {
        if (itens.size() == 0) throw new PedidoVazioException("Pedido não pode ser vazio");
    }

}

