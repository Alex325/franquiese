package com.alex.dcc025.usuario;

import java.util.ArrayList;
import java.util.List;

import com.alex.dcc025.franquia.Franquia;
import com.alex.dcc025.franquia.ItemPedido;
import com.alex.dcc025.franquia.Pedido;
import com.alex.dcc025.franquia.Pedido.FormaPagamento;
import com.alex.dcc025.franquia.Pedido.ModalidadeEntrega;

public class Vendedor extends Usuario {
    private Franquia franquia;
    private List<Pedido> pedidos;

    public Vendedor(String nome, String cpf, String email, String senha, Franquia franquia) {
        super(nome, cpf, email, senha);
        this.franquia = franquia;
        this.pedidos = new ArrayList<>();
    }

    public Vendedor() {
    }

    public void cadastrarPedido(String cliente, List<ItemPedido> itens, FormaPagamento formaPagamento, ModalidadeEntrega modalidadeEntrega) {
        Pedido pedido = new Pedido(this, cliente, itens, formaPagamento, modalidadeEntrega);
        pedidos.add(pedido);
        franquia.adicionarPedido(pedido);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public double getValor() {
        return pedidos.stream()
        .map(Pedido::calcularValorTotal).reduce(0.0, (i, p) -> i + p);
    }

    public int getVolume() {
        return pedidos.size();
    }

    public void solicitarAlteracaoPedido(Pedido pedido) {
        // franquia.analisarSolicitacaoAlteracao(pedido);
    }

    @Override
    public final int getTipo() {
        return 2;
    }


    public final Franquia getFranquia() {
        return this.franquia;
    }

}

