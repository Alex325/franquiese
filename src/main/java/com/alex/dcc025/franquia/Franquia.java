package com.alex.dcc025.franquia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alex.dcc025.usuario.Gerente;
import com.alex.dcc025.usuario.Vendedor;
import com.alex.dcc025.util.ID;

public class Franquia {

    private String id;
    private String nome;
    private Endereco endereco;
    private Gerente gerente;
    private List<Vendedor> vendedores;
    private List<Pedido> pedidos;
    private Map<Produto, Integer> estoque;

    public Franquia(String nome, Endereco endereco, Gerente gerente) {
        this.id = ID.getUUID();
        this.nome = nome;
        this.endereco = endereco;
        this.gerente = gerente;
        this.vendedores = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.estoque = new HashMap<>();
        
        if (gerente != null) gerente.setFranquia(this);
    }

    public Franquia() {}

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public boolean isEstoqueBaixo(Produto produto) {
        return estoque.get(produto) < 5;
    }

    public void listarPedidos() {
        for (Pedido p : pedidos) {
            System.out.println(p);
        }
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

    public void mostrarRankingVendedores() {
        Map<Vendedor, Double> vendasPorVendedor = new HashMap<>();
        for (Pedido p : pedidos) {
            vendasPorVendedor.put(p.getVendedor(),
                vendasPorVendedor.getOrDefault(p.getVendedor(), 0.0) + p.calcularValorTotal());
        }

        vendasPorVendedor.entrySet()
            .stream()
            .sorted(Map.Entry.<Vendedor, Double>comparingByValue().reversed())
            .forEach(entry -> System.out.println(entry.getKey().getNome() + ": R$ " + entry.getValue()));
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
        gerente.setFranquia(this);
    }
    public List<Vendedor> getVendedores() {
        return this.vendedores;
    }
    public List<Pedido> getPedidos() {
        return this.pedidos;
    }
    public Map<Produto, Integer> getEstoque() {
        return this.estoque;
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

    public void alterarProduto(Produto produto, String nome, double preco, String descricao, int quantidade) {
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setDescricao(descricao);
        estoque.put(produto, quantidade);
    }

}
