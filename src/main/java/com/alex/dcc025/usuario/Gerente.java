package com.alex.dcc025.usuario;

import java.util.ArrayList;
import java.util.List;

import com.alex.dcc025.franquia.Franquia;
import com.alex.dcc025.util.ID;

public class Gerente extends Usuario {
    private Franquia franquia;

    public Gerente(String nome, String cpf, String email, String senha) {
        super(nome, cpf, email, senha);
    }

    public Gerente(String id, String nome, String cpf, String email, String senha, Franquia franquia) {
        super(nome, cpf, email, senha);
        this.franquia = franquia;
    }

    public void setFranquia(Franquia franquia) {
        this.franquia = franquia;
    }

    public Franquia getFranquia() {
        return this.franquia;
    }

    public void cadastrarVendedor(Vendedor vendedor) {
        franquia.cadastrarVendedor(vendedor);
    }

    public void removerVendedor(Vendedor vendedor) {
        franquia.removerVendedor(vendedor);
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

}
