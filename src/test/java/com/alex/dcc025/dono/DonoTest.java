/*
 * Alex Sandro de Macedo Pinto
 * 202465551C
 */
package com.alex.dcc025.dono;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.alex.dcc025.Sistema;
import com.alex.dcc025.exception.CampoTextoInvalidoException;
import com.alex.dcc025.exception.CepInvalidoException;
import com.alex.dcc025.exception.CpfInvalidoException;
import com.alex.dcc025.exception.EmailInvalidoException;
import com.alex.dcc025.exception.NumeroInvalidoException;
import com.alex.dcc025.exception.SenhaInvalidaException;
import com.alex.dcc025.franquia.Franquia;
import com.alex.dcc025.franquia.endereco.Endereco;
import com.alex.dcc025.franquia.endereco.Endereco.UF;
import com.alex.dcc025.usuario.usuario.Dono;
import com.alex.dcc025.usuario.usuario.Gerente;

public class DonoTest {
    
    @Test
    public void testCriarFranquiaSucesso() {
        Dono dono = new Dono("null", "11144477735", "null@f.com", "null");

        assertDoesNotThrow(() -> dono.cadastrarFranquia("null", new Endereco("11111111", "null", "1", "null", "null", "null", UF.AC), null));
    }

    @Test
    public void testCriarFranquiaFalha() {
        Dono dono = new Dono("null", "11144477735", "null@f.com", "null");

        assertThrows(CampoTextoInvalidoException.class, () -> dono.cadastrarFranquia("", new Endereco("11111111", "null", "1", "null", "null", "null", UF.AC), null));
        assertThrows(CepInvalidoException.class, () -> dono.cadastrarFranquia("null", new Endereco("1111111", "null", "1", "null", "null", "null", UF.AC), null));
        assertThrows(CepInvalidoException.class, () -> dono.cadastrarFranquia("null", new Endereco("amogus", "null", "1", "null", "null", "null", UF.AC), null));
        assertThrows(CampoTextoInvalidoException.class, () -> dono.cadastrarFranquia("null", new Endereco("11111111", "", "1", "null", "null", "null", UF.AC), null));
        assertThrows(NumeroInvalidoException.class, () -> dono.cadastrarFranquia("null", new Endereco("11111111", "null", "a", "null", "null", "null", UF.AC), null));
        assertThrows(NumeroInvalidoException.class, () -> dono.cadastrarFranquia("null", new Endereco("11111111", "null", "", "null", "null", "null", UF.AC), null));
        assertThrows(CampoTextoInvalidoException.class, () -> dono.cadastrarFranquia("null", new Endereco("11111111", "null", "1", "", "null", "null", UF.AC), null));
        assertThrows(CampoTextoInvalidoException.class, () -> dono.cadastrarFranquia("null", new Endereco("11111111", "null", "1", "null", "", "null", UF.AC), null));
        assertThrows(CampoTextoInvalidoException.class, () -> dono.cadastrarFranquia("null", new Endereco("11111111", "null", "1", "null", "null", "", UF.AC), null));

    }

    @Test
    public void testCriarGerenteSucesso() {
        Dono dono = new Dono("null", "11144477735", "null@f.com", "null");

        

        assertDoesNotThrow(() -> {
            Sistema sistema = new Sistema();
            dono.cadastrarGerente("null", "11144477735", "null@f.com", "null", sistema);
        });
    }

    @Test
    public void testCriarGerenteFalha() {
        Dono dono = new Dono("null", "11144477735", "null@f.com", "null");

        assertThrows(CampoTextoInvalidoException.class, () -> {
            Sistema sistema = new Sistema();
            dono.cadastrarGerente("", "11144477735", "null@f.com", "null", sistema);
         });
        assertThrows(CampoTextoInvalidoException.class, () -> {
            Sistema sistema = new Sistema();
            dono.cadastrarGerente("123", "11144477735", "null@f.com", "null", sistema);
         });
        assertThrows(CpfInvalidoException.class, () -> {
            Sistema sistema = new Sistema();
            dono.cadastrarGerente("null", "11144477734", "null@f.com", "null", sistema);
         });
        assertThrows(CpfInvalidoException.class, () -> {
            Sistema sistema = new Sistema();
            dono.cadastrarGerente("null", "aaaaaa", "null@f.com", "null", sistema);
         });
        assertThrows(EmailInvalidoException.class, () -> {
            Sistema sistema = new Sistema();
            dono.cadastrarGerente("null", "11144477735", "nullf.com", "null", sistema);
         });
        assertThrows(EmailInvalidoException.class, () -> {
            Sistema sistema = new Sistema();
            dono.cadastrarGerente("null", "11144477735", "null@f.c", "null", sistema);
         });
        assertThrows(EmailInvalidoException.class, () -> {
            Sistema sistema = new Sistema();
            dono.cadastrarGerente("null", "11144477735", "null@f", "null", sistema);
         });
        assertThrows(EmailInvalidoException.class, () -> {
            Sistema sistema = new Sistema();
            dono.cadastrarGerente("null", "11144477735", "null", "null", sistema);
         });
        assertThrows(SenhaInvalidaException.class, () -> {
            Sistema sistema = new Sistema();
            dono.cadastrarGerente("null", "11144477735", "null@f.com", "", sistema);
         });

    }

    @Test
    public void testMudarrenteSucesso() throws Exception {
        Dono dono = new Dono("null", "11144477735", "null@f.com", "null");

        Gerente gerente1 = new Gerente("null", "11144477735", "null@f.com", "null");
        Gerente gerente2 = new Gerente("null", "11144477735", "null@f.com", "null");

        dono.cadastrarFranquia("null", new Endereco("11111111", "null", "1", "null", "null", "null", UF.AC), gerente1);
        
        Franquia franquia = dono.getFranquia(0);

        assertTrue(franquia.getGerente() == gerente1 && gerente1.getFranquia() == franquia);

        dono.mudarGerente(franquia, gerente2);

        assertTrue(franquia.getGerente() == gerente2 && gerente2.getFranquia() == franquia && gerente1.getFranquia() == null);

    }


}
