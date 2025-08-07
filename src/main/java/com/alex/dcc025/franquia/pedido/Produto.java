/*
 * Alex Sandro de Macedo Pinto
 * 202465551C
 */
package com.alex.dcc025.franquia.pedido;

import com.alex.dcc025.exception.CampoTextoInvalidoException;
import com.alex.dcc025.exception.NumeroInvalidoException;
import com.alex.dcc025.util.ID;
import com.alex.dcc025.util.Validador;

public class Produto {

    private String id;
    private String nome;
    private double preco;
    private String descricao;

    public Produto(String nome, double preco, String descricao) {
        this.id = ID.getUUID();
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
    }

    public Produto() {}

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getId() {
        return this.id;
    }

    public double getPreco() {
        return this.preco;
    }

    @Override
    public String toString() {
        return nome + " - " + descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }    

    public static void validarProduto(String nome, String descricao, double preco) throws Exception {
        if (!Validador.validarCampoTexto(nome)) throw new CampoTextoInvalidoException("Nome deve ser composto de caracteres.");
        if (!Validador.validarCampoTexto(descricao)) throw new CampoTextoInvalidoException("Campo descrição deve ser composto de caracteres.");
        if (preco < 0.0) throw new NumeroInvalidoException("Preço deve ser maior ou igual a 0.");
    }
}

