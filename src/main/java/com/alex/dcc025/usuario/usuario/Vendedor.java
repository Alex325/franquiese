package com.alex.dcc025.usuario.usuario;

import java.util.ArrayList;
import java.util.List;

import com.alex.dcc025.franquia.Franquia;
import com.alex.dcc025.franquia.pedido.ItemPedido;
import com.alex.dcc025.franquia.pedido.Pedido;
import com.alex.dcc025.franquia.pedido.Pedido.FormaPagamento;
import com.alex.dcc025.franquia.pedido.Pedido.ModalidadeEntrega;
import com.alex.dcc025.franquia.solicitacao.PedidoAlteracao;
import com.alex.dcc025.franquia.solicitacao.PedidoExclusao;
import com.alex.dcc025.usuario.Usuario;

public class Vendedor extends Usuario {
    private Franquia franquia;
    private List<Pedido> pedidos;

    public Vendedor(String nome, String cpf, String email, String senha, Franquia franquia) {
        super(nome, cpf, email, senha);
        this.franquia = franquia;
        this.pedidos = new ArrayList<>();
    }

    public Vendedor() {}

    public void cadastrarPedido(String cliente, List<ItemPedido> itens, FormaPagamento formaPagamento, ModalidadeEntrega modalidadeEntrega) throws Exception {

        Pedido.validarPedido(cliente, itens);

        Pedido pedido = new Pedido(this, cliente, itens, formaPagamento, modalidadeEntrega);
        pedidos.add(pedido);
        franquia.adicionarPedido(pedido);
    }

    public void removerPedido(Pedido pedido) {
        pedidos.remove(pedido);
    }

    public List<Pedido> getPedidos() {
        return List.copyOf(pedidos);
    }

    public double getValor() {
        return pedidos.stream()
        .map(Pedido::calcularValorTotal).reduce(0.0, (i, p) -> i + p);
    }

    public int getVolume() {
        return pedidos.size();
    }

    public void pedirAlteracao(Pedido pedido, List<ItemPedido> itens, FormaPagamento forma, ModalidadeEntrega modalidade) throws Exception {

        Pedido.validarPedido(itens);

        franquia.getGerente().adicionarSolicitacao(new PedidoAlteracao(pedido, itens, forma, modalidade));

        pedido.setPendente(true);
    }

    public void pedirExclusao(Pedido pedido) {
        franquia.getGerente().adicionarSolicitacao(new PedidoExclusao(pedido));
        pedido.setPendente(true);
    }

    @Override
    public final int getTipo() {
        return 2;
    }


    public final Franquia getFranquia() {
        return this.franquia;
    }

}

