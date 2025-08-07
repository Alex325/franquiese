/*
 * Alex Sandro de Macedo Pinto
 * 202465551C
 */
package com.alex.dcc025.sistema;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.alex.dcc025.Sistema;
import com.alex.dcc025.exception.LoginDuplicadoException;
import com.alex.dcc025.usuario.usuario.Dono;

public class SistemaTest {

    @Test
    public void testCheckConflitoSucesso() {
        Dono dono = new Dono("null", "11144477735", "null@f.com", "null");

        Sistema sistema = new Sistema();

        sistema.cadastrarUsuario(dono);
        
        assertDoesNotThrow(() -> {
            dono.cadastrarGerente("null", "11144477735", "null@fr.com", "null", sistema);
        });
    }

    @Test
    public void testCheckConflitoFalha() {
        Dono dono = new Dono("null", "11144477735", "null@f.com", "null");

        Sistema sistema = new Sistema();

        sistema.cadastrarUsuario(dono);
        
        assertThrows(LoginDuplicadoException.class, () -> {
            dono.cadastrarGerente("null", "11144477735", "null@f.com", "null", sistema);
        });
    }

}
