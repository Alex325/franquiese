package com.alex.dcc025.franquia.solicitacao;

import com.alex.dcc025.franquia.pedido.Pedido;

public class PedidoExclusao implements Solicitacao {

    private Pedido pedido;
    
    public PedidoExclusao(Pedido pedido) {
        this.pedido = pedido;
    }
    
    public PedidoExclusao() {}
 
    public void aceitar() {
        this.pedido.getVendedor().getFranquia().removerPedido(this.pedido);
        this.pedido.setPendente(false);
    }

    public void recusar() {
        this.pedido.setPendente(false);
    }

    @Override
    public String toString() {
        return "Exclusão de " + this.pedido.toString().toLowerCase();
    }
    
}
