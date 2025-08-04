package com.alex.dcc025.usuario;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.alex.dcc025.Sistema;
import com.alex.dcc025.franquia.Endereco;
import com.alex.dcc025.franquia.Franquia;
import java.util.ArrayList;

public class Dono extends Usuario {
    private List<Franquia> franquias;
    private List<Gerente> gerentes;

    public Dono(String nome, String cpf, String email, String senha) {
        super(nome, cpf, email, senha);
        franquias = new ArrayList<>();
        gerentes = new ArrayList<>();
    }

    public Dono() {
    }

    public void cadastrarFranquia(String nome, Endereco endereco, Gerente gerente) {
        Franquia f = new Franquia(nome, endereco, gerente);
        franquias.add(f);
    }

    public void removerFranquia(Franquia franquia, Sistema sistema) {
        franquias.remove(franquia);
        sistema.removerUsuarios(franquia.getVendedores().stream().map(v -> (Usuario) v).collect(Collectors.toList()));

        if (franquia.getGerente() != null) franquia.getGerente().setFranquia(null);
    }

    public void cadastrarGerente(String nome, String cpf, String email, String senha, Sistema sistema) {
        Gerente gerente = new Gerente(nome, cpf, email, senha);
        gerentes.add(gerente);
        sistema.cadastrarUsuario(gerente);
    }

    public void removerGerente(Gerente gerente, Sistema sistema) {
        gerentes.remove(gerente);
        sistema.removerUsuario(gerente);
        gerente.getFranquia().setGerente(null);
    }

    public Gerente getGerente(int i) {
        return gerentes.get(i);
    }
    
    public Franquia getFranquia(int i) {
        return this.franquias.get(0);
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


}
