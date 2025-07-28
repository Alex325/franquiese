package com.alex.dcc025.usuario;

import java.util.ArrayList;
import java.util.List;

import com.alex.dcc025.franquia.Franquia;

public class Gerente extends Usuario {
    private Franquia franquia;
    private List<Vendedor> vendedores;

    public Gerente(int id, String nome, String cpf, String email, String senha) {
        super(id, 1, nome, cpf, email, senha);
        this.vendedores = new ArrayList<>();
    }

    public void setFranquia(Franquia franquia) {
        this.franquia = franquia;
    }

    public Franquia getFranquia() {
        return this.franquia;
    }

    public void cadastrarVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
        vendedor.setGerente(this);
    }

    public void removerVendedor(Vendedor vendedor) {
        vendedores.remove(vendedor);
    }

    public void editarVendedor(Vendedor vendedor, String novoNome) {
        // vendedor.setNome(novoNome);
    }

    public void controlarPedidos() {
        franquia.listarPedidos();
    }

    public void administrarEstoque() {
        franquia.mostrarEstoque();
    }

    public void acessarRelatorios() {
        franquia.mostrarRelatorio();
    }
}
