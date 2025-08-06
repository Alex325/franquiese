package com.alex.dcc025.usuario.usuario;

import java.util.List;

import com.alex.dcc025.Sistema;
import com.alex.dcc025.franquia.Franquia;
import com.alex.dcc025.franquia.pedido.ItemPedido;
import com.alex.dcc025.franquia.pedido.Pedido;
import com.alex.dcc025.franquia.pedido.Produto;
import com.alex.dcc025.franquia.pedido.Pedido.FormaPagamento;
import com.alex.dcc025.franquia.pedido.Pedido.ModalidadeEntrega;
import com.alex.dcc025.franquia.solicitacao.Solicitacao;
import com.alex.dcc025.usuario.Usuario;

public class Gerente extends Usuario {
    private Franquia franquia;

    public Gerente(String nome, String cpf, String email, String senha) {
        super(nome, cpf, email, senha);
        
    }

    public Gerente() {
    }

    public void setFranquia(Franquia franquia) {
        this.franquia = franquia;
    }

    public Franquia getFranquia() {
        return this.franquia;
    }

    public void cadastrarVendedor(String nome, String cpf, String email, String senha, Sistema sistema) throws Exception {

        Usuario.validarUsuario(nome, cpf, email, senha);

        Vendedor vendedor = new Vendedor(nome, cpf, email, senha, this.franquia);
        franquia.cadastrarVendedor(vendedor);
        sistema.cadastrarUsuario(vendedor);
    }

    public void removerVendedor(Vendedor vendedor, Sistema sistema) {
        franquia.removerVendedor(vendedor);
        sistema.removerUsuario(vendedor);
    }

    public void removerProduto(Produto produto) {
        franquia.getEstoque().remove(produto);
    }

    public List<Vendedor> getVendedores() {
        return this.franquia.getVendedores();
    }

    public Vendedor getVendedor(int i) {
        return this.franquia.getVendedores().get(i);
    }

    public void cadastrarProduto(String nome, double preco, String descricao, int quantidade) throws Exception {

        Produto.validarProduto(nome, descricao, preco);

        this.franquia.cadastrarProduto(new Produto(nome, preco, descricao), quantidade);
    }

    public void alterarProduto(Produto produto, String nome, double preco, String descricao, int quantidade) throws Exception {
        
        Produto.validarProduto(nome, descricao, preco);

        this.franquia.alterarProduto(produto, nome, preco, descricao, quantidade);
    }

    public void alterarPedido(Pedido pedido, List<ItemPedido> itens, FormaPagamento forma, ModalidadeEntrega modalidade) throws Exception {

        Pedido.validarPedido(itens);

        pedido.setItens(itens);
        pedido.setFormaPagamento(forma);
        pedido.setModalidadeEntrega(modalidade);
    }

    public void removerPedido(Pedido pedido) {
        franquia.removerPedido(pedido);
        
    }

    public List<Solicitacao> getSolicitacoes() {
        return this.franquia.getSolicitacoes();
    }

    public void adicionarSolicitacao(Solicitacao solicitacao) {
        this.getFranquia().adicionarSolicitacao(solicitacao);
    }

    public void aceitarSolicitacao(Solicitacao solicitacao) {
        this.franquia.aceitarSolicitacao(solicitacao);
        
    }

    public void recusarSolicitacao(Solicitacao solicitacao) {
        this.franquia.recusarSolicitacao(solicitacao);
    }

    @Override
    public int getTipo() {
        return 1;
    }



}
