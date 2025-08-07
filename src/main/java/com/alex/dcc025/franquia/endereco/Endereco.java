/*
 * Alex Sandro de Macedo Pinto
 * 202465551C
 */
package com.alex.dcc025.franquia.endereco;

import java.util.List;

public class Endereco {

    public static enum UF {
        AC,
        AL,
        AP,
        AM,
        BA,
        CE,
        DF,
        ES,
        GO,
        MA,
        MG,
        MS,
        MT,
        PA,
        PB,
        PE,
        PI,
        PR,
        RJ,
        RN,
        RO,
        RR,
        RS,
        SC,
        SE,
        SP,
        TO
    }

    public static transient final List<UF> ufs = List.of(UF.values());

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private UF uf;

    public Endereco(String cep, String logradouro, String numero, String complemento, String bairro, String cidade, UF uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public Endereco() {}

    public String getCep() {
        return this.cep;
    }
    public String getLogradouro() {
        return this.logradouro;
    }
    public String getNumero() {
        return this.numero;
    }
    public String getComplemento() {
        return this.complemento;
    }
    public String getBairro() {
        return this.bairro;
    }
    public String getCidade() {
        return this.cidade;
    }
    public UF getUf() {
        return this.uf;
    }
}
