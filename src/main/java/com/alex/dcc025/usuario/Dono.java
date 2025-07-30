package com.alex.dcc025.usuario;

import java.util.List;

import com.alex.dcc025.franquia.Franquia;
import com.alex.dcc025.util.ID;
import com.alex.dcc025.util.Serializador;

public class Dono extends Usuario {
    private List<Franquia> franquias;
    private List<Gerente> gerentes;

    public Dono(String nome, String cpf, String email, String senha) {
        super(nome, cpf, email, senha);
        loadPropriedades();
    }

    public Dono(String id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        loadPropriedades();
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

    public void cadastrarGerente(String nome, String cpf, String email, String senha) {
        gerentes.add(new Gerente(nome, cpf, email, senha));
    }

    public void removerGerente(Gerente gerente) {
        gerentes.remove(gerente);
    }

    public Gerente getGerente(int i) {
        return gerentes.get(i);
    }

    public void loadPropriedades() {
        this.franquias = Serializador.loadFranquias();
        this.gerentes = Serializador.loadGerentes();
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
    
    @Override
    public int getTipo() {
        return 0;
    }

    public void savePropriedades() {
        Serializador.saveFranquias(franquias);
    }

    public Franquia getFranquia(int i) {
        return this.franquias.get(0);
    }

}
