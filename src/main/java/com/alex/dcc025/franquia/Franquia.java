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
    private String endereco;
    private Gerente gerente;
    private List<Vendedor> vendedores;
    private List<Pedido> pedidos;
    private List<Produto> estoque;

    public Franquia(String nome, String endereco, Gerente gerente) {
        this.id = ID.getUUID();
        this.nome = nome;
        this.endereco = endereco;
        this.gerente = gerente;
        this.vendedores = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.estoque = new ArrayList<>();
        
        if (gerente != null) gerente.setFranquia(this);
    }

    // para desserialização
    public Franquia() {}

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void mostrarEstoque() {
        for (Produto p : estoque) {
            System.out.println(p);
        }
    }

    public void listarPedidos() {
        for (Pedido p : pedidos) {
            System.out.println(p);
        }
    }

    public void exibirResumo() {
        System.out.println("Franquia: " + nome);
        System.out.println("Gerente: " + (gerente != null ? gerente.getNome() : "Não definido"));
        System.out.println("Total de pedidos: " + pedidos.size());
        // Calcular receita, ticket médio, etc.
    }

    public void cadastrarVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    public void mostrarRankingVendedores() {
        Map<Vendedor, Double> vendasPorVendedor = new HashMap<>();
        for (Pedido p : pedidos) {
            vendasPorVendedor.put(p.getVendedor(),
                vendasPorVendedor.getOrDefault(p.getVendedor(), 0.0) + p.getValorTotal());
        }

        vendasPorVendedor.entrySet()
            .stream()
            .sorted(Map.Entry.<Vendedor, Double>comparingByValue().reversed())
            .forEach(entry -> System.out.println(entry.getKey().getNome() + ": R$ " + entry.getValue()));
    }

    public void cadastrarProduto(Produto produto) {
        estoque.add(produto);
    }

    public void mostrarRelatorio() {
        // histórico de vendas, clientes mais recorrentes, etc.
    }

    public String getId() {
        return this.id;
    }
    public String getNome() {
        return this.nome;
    }
    public String getEndereco() {
        return this.endereco;
    }
    public Gerente getGerente() {
        return this.gerente;
    }
    public List<Vendedor> getVendedores() {
        return this.vendedores;
    }
    public List<Pedido> getPedidos() {
        return this.pedidos;
    }
    public List<Produto> getEstoque() {
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

}
