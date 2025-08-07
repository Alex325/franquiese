/*
 * Alex Sandro de Macedo Pinto
 * 202465551C
 */
package com.alex.dcc025.franquia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alex.dcc025.exception.CampoTextoInvalidoException;
import com.alex.dcc025.franquia.endereco.Endereco;
import com.alex.dcc025.franquia.pedido.ItemPedido;
import com.alex.dcc025.franquia.pedido.Pedido;
import com.alex.dcc025.franquia.pedido.Produto;
import com.alex.dcc025.franquia.solicitacao.Solicitacao;
import com.alex.dcc025.usuario.usuario.Gerente;
import com.alex.dcc025.usuario.usuario.Vendedor;
import com.alex.dcc025.util.ID;
import com.alex.dcc025.util.Validador;

public class Franquia {

    private String id;
    private String nome;
    private Endereco endereco;
    private Gerente gerente;
    private List<Vendedor> vendedores;
    private List<Pedido> pedidos;
    private Map<Produto, Integer> estoque;
    private List<Solicitacao> solicitacoes;


    public Franquia(String nome, Endereco endereco, Gerente gerente) {
        this.id = ID.getUUID();
        this.nome = nome;
        this.endereco = endereco;
        this.gerente = gerente;
        this.vendedores = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.solicitacoes = new ArrayList<>();
        this.estoque = new HashMap<>();
    }

    public Franquia() {}

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
        for (ItemPedido item : pedido.getItens()) {
            estoque.put(item.getProduto(), estoque.get(item.getProduto()) - item.getQuantidade());
        }
    }

    public void removerPedido(Pedido pedido) {
        pedidos.remove(pedido);
        pedido.getVendedor().removerPedido(pedido);

        for (ItemPedido item : pedido.getItens()) {
            estoque.put(item.getProduto(), estoque.get(item.getProduto()) + item.getQuantidade());
        }
    }

    public boolean isEstoqueBaixo(Produto produto) {
        return estoque.get(produto) < 5;
    }

    public double ticketMedio() {
        return faturamento()/numeroPedidos();
    }

    public double faturamento() {
        return this.pedidos.stream()
        .map(Pedido::calcularValorTotal).reduce(0.0, (i, p) -> i + p);
    }

    public int numeroPedidos() {
        return this.pedidos.size();
    }

    public void cadastrarVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    public void cadastrarProduto(Produto produto, int quantidade) {
        estoque.put(produto, quantidade);
    }

    public String getId() {
        return this.id;
    }
    public String getNome() {
        return this.nome;
    }
    public Endereco getEndereco() {
        return this.endereco;
    }
    public Gerente getGerente() {
        return this.gerente;
    }
    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }
    public List<Vendedor> getVendedores() {
        return List.copyOf(this.vendedores);
    }
    public List<Pedido> getPedidos() {
        return List.copyOf(this.pedidos);
    }
    public Map<Produto, Integer> getEstoque() {
        return Map.copyOf(this.estoque);
    }

    public void removerVendedor(Vendedor vendedor) {
        vendedores.remove(vendedor);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(this.nome);
        string.append(" - ");
        string.append(this.gerente != null ? this.gerente.getNome() : "sem gerente");
        return string.toString();
    }

    public void alterarProduto(Produto produto, String nome, double preco, String descricao, int quantidade) throws Exception {

        Produto.validarProduto(nome, descricao, preco);

        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setDescricao(descricao);
        estoque.put(produto, quantidade);
    }

    public List<Solicitacao> getSolicitacoes() {
        return List.copyOf(this.solicitacoes);
    }

    public void adicionarSolicitacao(Solicitacao solicitacao) {
        solicitacoes.add(solicitacao);
    }

    public void aceitarSolicitacao(Solicitacao solicitacao) {
        solicitacoes.remove(solicitacao);
        solicitacao.aceitar();
        
    }

    public void recusarSolicitacao(Solicitacao solicitacao) {
        solicitacoes.remove(solicitacao);
        solicitacao.recusar();
    }

    public static void validarFranquia(String nome, Endereco endereco) throws Exception {
        if (!Validador.validarCampoTexto(nome)) throw new CampoTextoInvalidoException("Nome deve ser composto de caracteres.");
        Validador.validarEndereco(endereco);
    }

    public void removerProduto(Produto produto) {
        estoque.remove(produto);
    }

}
