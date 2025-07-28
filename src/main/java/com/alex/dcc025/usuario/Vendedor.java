package com.alex.dcc025.usuario;

import java.util.ArrayList;
import java.util.List;

import com.alex.dcc025.franquia.Pedido;

public class Vendedor extends Usuario {
    private Gerente gerente;
    private List<Pedido> pedidos;

    public Vendedor(int id,String nome, String cpf, String email, String senha) {
        super(id, 2, nome, cpf, email, senha);
        this.pedidos = new ArrayList<>();
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public void cadastrarPedido(Pedido pedido) {
        pedidos.add(pedido);
        gerente.getFranquia().adicionarPedido(pedido);
    }

    public List<Pedido> visualizarPedidos() {
        return pedidos;
    }

    public void solicitarAlteracaoPedido(Pedido pedido) {
        // gerente.analisarSolicitacaoAlteracao(pedido);
    }

}

