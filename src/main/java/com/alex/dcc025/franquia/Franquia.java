package com.alex.dcc025.franquia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alex.dcc025.usuario.Gerente;
import com.alex.dcc025.usuario.Vendedor;
import com.alex.dcc025.util.ID;

public class Franquia {

    private final String id;
    private final String nome;
    private final String endereco;
    private final Gerente gerente;
    private final List<Vendedor> vendedores;
    private final List<Pedido> pedidos;
    private final List<Produto> estoque;

    public Franquia(String nome, String endereco, Gerente gerente) {
        this.id = ID.getUUID();
        this.nome = nome;
        this.endereco = endereco;
        this.gerente = gerente;
        this.vendedores = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.estoque = new ArrayList<>();
        gerente.setFranquia(this);
    }

    // para desserialização
    public Franquia(String id, String nome, String endereco, Gerente gerente, List<Vendedor> vendedores, List<Pedido> pedidos, List<Produto> estoque) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.gerente = gerente;
        this.vendedores = vendedores;
        this.pedidos = pedidos;
        this.estoque = estoque;
    }

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

}
