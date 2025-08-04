package com.alex.dcc025.usuario;

import java.util.List;

import com.alex.dcc025.Sistema;
import com.alex.dcc025.franquia.Franquia;
import com.alex.dcc025.franquia.Produto;

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

    public void cadastrarVendedor(String nome, String cpf, String email, String senha, Sistema sistema) {
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

    public void editarVendedor(Vendedor vendedor, String novoNome) {
        // vendedor.setNome(novoNome);
    }

    public void cadastrarProduto(String nome, double preco, String descricao, int quantidade) {
        this.franquia.cadastrarProduto(new Produto(nome, preco, descricao), quantidade);
    }

    public void alterarProduto(Produto produto, String nome, double preco, String descricao, int quantidade) {
        this.franquia.alterarProduto(produto, nome, preco, descricao, quantidade);
    }

    public void controlarPedidos() {
        franquia.listarPedidos();
    }

    @Override
    public int getTipo() {
        return 1;
    }


}
