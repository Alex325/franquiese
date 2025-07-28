package com.alex.dcc025.usuario;

import java.util.ArrayList;
import java.util.List;

import com.alex.dcc025.franquia.Franquia;
import com.alex.dcc025.util.ID;

public class Gerente extends Usuario {
    private Franquia franquia;
    private List<Vendedor> vendedores;

    public Gerente(String nome, String cpf, String email, String senha) {
        super(ID.getUUID(), nome, cpf, email, senha);
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

    @Override
    public int getTipo() {
        return 1;
    }

    @Override
    public void savePropriedades() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'savePropriedades'");
    }
}
