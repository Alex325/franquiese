package com.alex.dcc025.usuario;

import java.util.ArrayList;
import java.util.List;

import com.alex.dcc025.franquia.Franquia;

public class Dono extends Usuario {
    private List<Franquia> franquias;
    private List<Gerente> gerentes;

    public Dono(int id, String nome, String cpf, String email, String senha) {
        super(id, 0,nome, cpf, email, senha);
        this.franquias = new ArrayList<>();
        this.gerentes = new ArrayList<>();
    }

    public void cadastrarFranquia(String nome, String endereco, Gerente gerente) {
        Franquia f = new Franquia(nome, endereco, gerente);
        franquias.add(f);
    }

    public void removerFranquia(Franquia franquia) {
        franquias.remove(franquia);
    }

    public void listarFranquias() {
        for (Franquia f : franquias) {
            f.exibirResumo();
        }
    }

    public void cadastrarGerente(Gerente gerente) {
        gerentes.add(gerente);
    }

    public void removerGerente(Gerente gerente) {
        gerentes.remove(gerente);
    }

    public void consultarRankingVendedores(Franquia franquia) {
        franquia.mostrarRankingVendedores();
    }

    public final List<Franquia> getFranquias() {
        return this.franquias;
    }
    public final List<Gerente> getGerentes() {
        return this.gerentes;
    }
    
}
