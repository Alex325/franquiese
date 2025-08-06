package com.alex.dcc025.franquia.solicitacao;

import java.util.List;

import com.alex.dcc025.franquia.pedido.ItemPedido;
import com.alex.dcc025.franquia.pedido.Pedido;
import com.alex.dcc025.franquia.pedido.Pedido.FormaPagamento;
import com.alex.dcc025.franquia.pedido.Pedido.ModalidadeEntrega;

public class PedidoAlteracao implements Solicitacao {

    private Pedido pedido;
    private List<ItemPedido> itens;
    private FormaPagamento forma;
    private ModalidadeEntrega modalidade;

    public PedidoAlteracao(Pedido pedido, List<ItemPedido> itens, FormaPagamento forma, ModalidadeEntrega modalidade) {
        this.pedido = pedido;
        this.itens = itens;
        this.forma = forma;
        this.modalidade = modalidade;
    }

    public PedidoAlteracao() {}


    @Override
    public void aceitar() {
        this.pedido.setFormaPagamento(forma);
        this.pedido.setItens(itens);
        this.pedido.setModalidadeEntrega(modalidade);
        this.pedido.setPendente(false);
    }


    @Override
    public void recusar() {
        this.pedido.setPendente(false);
    }

    @Override
    public String toString() {
        return "Alteração de " + this.pedido.toString().toLowerCase();
    }
    
}
