/*
 * Alex Sandro de Macedo Pinto
 * 202465551C
 */
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

        for (ItemPedido item : pedido.getItens()) {

            int adicionar;

            if (itens.stream().noneMatch(i -> i.getProduto() == item.getProduto())) {
                adicionar = item.getQuantidade();
            }
            else {
                adicionar = item.getQuantidade() - itens.stream().filter(i -> i.getProduto() == item.getProduto()).toList().get(0).getQuantidade();
            }

            try {
                pedido.getVendedor().getFranquia().alterarProduto(item.getProduto(), item.getProduto().getNome(), item.getProduto().getPreco(), item.getProduto().getDescricao(), pedido.getVendedor().getFranquia().getEstoque().get(item.getProduto()) + adicionar);
            } catch (Exception e) {
                // isso não deve acontecer
            }
            
        }

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
