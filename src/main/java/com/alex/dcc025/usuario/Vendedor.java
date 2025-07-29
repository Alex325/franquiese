package com.alex.dcc025.usuario;

import java.util.ArrayList;
import java.util.List;

import com.alex.dcc025.franquia.Franquia;
import com.alex.dcc025.franquia.Pedido;

public class Vendedor extends Usuario {
    private final Franquia franquia;
    private final List<Pedido> pedidos;

    public Vendedor(String nome, String cpf, String email, String senha, Franquia franquia) {
        super(nome, cpf, email, senha);
        this.franquia = franquia;
        this.pedidos = new ArrayList<>();
    }

    public Vendedor(String id, String nome, String cpf, String email, String senha, Franquia franquia, List<Pedido> pedidos) {
        super(id, nome, cpf, email, senha);
        this.franquia = franquia;
        this.pedidos = pedidos;
    }

    public void cadastrarPedido(Pedido pedido) {
        pedidos.add(pedido);
        franquia.adicionarPedido(pedido);
    }

    public List<Pedido> visualizarPedidos() {
        return pedidos;
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

