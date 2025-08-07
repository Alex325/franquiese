/*
 * Alex Sandro de Macedo Pinto
 * 202465551C
 */
package com.alex.dcc025.util;

import org.apache.commons.validator.routines.EmailValidator;

import com.alex.dcc025.exception.CampoTextoInvalidoException;
import com.alex.dcc025.exception.CepInvalidoException;
import com.alex.dcc025.exception.NumeroInvalidoException;
import com.alex.dcc025.franquia.endereco.Endereco;

public class Validador {
    public static boolean validarSenha(String senha) {
        return !senha.isBlank();
    }

    public static boolean validarEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public static boolean validarCpf(String cpf) {
        if (!cpf.matches("[0-9]{11,11}")) return false;

        int acumulador1 = 0;

        for (int i = 0; i < 9; i++) {
            acumulador1 += (cpf.charAt(i) - '0') * (10 - i);
        }

        int digitoVerificador1 = acumulador1 % 11 < 2 ? 0 : 11 - (acumulador1 % 11);

        if ((cpf.charAt(9) - '0') != digitoVerificador1) return false;

        int acumulador2 = 0;

        for (int i = 0; i < 10; i++) {
            acumulador2 += (cpf.charAt(i) - '0') * (11 - i);
        }

        int digitoVerificador2 = acumulador2 % 11 < 2 ? 0 : 11 - (acumulador2 % 11);

        if ((cpf.charAt(10) - '0') != digitoVerificador2) return false;

        return true;

        
    }

    public static boolean validarCampoTexto(String texto) {
        return texto.matches("^[A-Za-z]+(?: [A-Za-z]+)*$");
    }

    public static void validarEndereco(Endereco endereco) throws Exception {
        if (!validarCep(endereco.getCep())) throw new CepInvalidoException("CEP deve ser composto por 8 dígitos.");
        if (!validarCampoTexto(endereco.getLogradouro())) throw new CampoTextoInvalidoException("Logradouro deve ser composto por caracteres.");
        if (!validarNumero(endereco.getNumero())) throw new NumeroInvalidoException("Número deve ser composto por dígitos.");
        if (!validarCampoTexto(endereco.getComplemento())) throw new CampoTextoInvalidoException("Complemento deve ser composto por caracteres.");
        if (!validarCampoTexto(endereco.getCidade())) throw new CampoTextoInvalidoException("Campo cidade deve ser composto por caracteres.");
        if (!validarCampoTexto(endereco.getBairro())) throw new CampoTextoInvalidoException("Bairro deve ser composto por caracteres.");
    }

    private static boolean validarNumero(String numero) {
        return numero.matches("^[1-9][0-9]*");
    }

    private static boolean validarCep(String cep) {
        return cep.matches("[0-9]{8,8}");
    }
}
