package com.alex.dcc025.usuario;

import java.util.ArrayList;
import java.util.List;

import com.alex.dcc025.franquia.Pedido;
import com.alex.dcc025.util.ID;

public class Vendedor extends Usuario {
    private Gerente gerente;
    private transient List<Pedido> pedidos;

    public Vendedor(String nome, String cpf, String email, String senha) {
        super(ID.getUUID(), nome, cpf, email, senha);
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

    @Override
    public int getTipo() {
        return 2;
    }

    @Override
    public void savePropriedades() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'savePropriedades'");
    }

}

