/*
 * Alex Sandro de Macedo Pinto
 * 202465551C
 */
package com.alex.dcc025.gerente;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.alex.dcc025.Sistema;
import com.alex.dcc025.exception.CampoTextoInvalidoException;
import com.alex.dcc025.exception.NumeroInvalidoException;
import com.alex.dcc025.franquia.Franquia;
import com.alex.dcc025.franquia.pedido.ItemPedido;
import com.alex.dcc025.franquia.pedido.Pedido.FormaPagamento;
import com.alex.dcc025.franquia.pedido.Pedido.ModalidadeEntrega;
import com.alex.dcc025.franquia.pedido.Produto;
import com.alex.dcc025.usuario.usuario.Gerente;
import com.alex.dcc025.usuario.usuario.Vendedor;

public class GerenteTest {

    @Test
    public void testCriarProdutoSucesso() {
        
        Gerente gerente = new Gerente("null", "11144477735", "null@f.com", "null");

        Franquia franquia = new Franquia("null", null, gerente);

        gerente.setFranquia(franquia);

        assertDoesNotThrow(() -> gerente.cadastrarProduto("null", 0.0, "null", 0));
    }

    @Test
    public void testCriarProdutoFalha() {
        
        Gerente gerente = new Gerente("null", "11144477735", "null@f.com", "null");

        Franquia franquia = new Franquia("null", null, gerente);

        gerente.setFranquia(franquia);

        assertThrows(CampoTextoInvalidoException.class, () -> gerente.cadastrarProduto("", 0.0, "null", 0));
        assertThrows(CampoTextoInvalidoException.class, () -> gerente.cadastrarProduto("123", 0.0, "null", 0));
        assertThrows(NumeroInvalidoException.class, () -> gerente.cadastrarProduto("null", -1.0, "null", 0));
        assertThrows(CampoTextoInvalidoException.class, () -> gerente.cadastrarProduto("null", 0.0, "", 0));
        assertThrows(CampoTextoInvalidoException.class, () -> gerente.cadastrarProduto("null", 0.0, "123", 0));

    }

    @Test
    public void testRemoverProdutoSucesso() throws Exception {
        
        Gerente gerente = new Gerente("null", "11144477735", "null@f.com", "null");

        Franquia franquia = new Franquia("null", null, gerente);

        gerente.setFranquia(franquia);

        gerente.cadastrarProduto("A", 0.0, "null", 0);

        gerente.removerProduto((Produto)franquia.getEstoque().keySet().toArray()[0]);

        assertTrue(franquia.getEstoque().isEmpty());

    }

    @Test
    public void testRemoverPedidoSucesso() throws Exception {
        
        Sistema sistema = new Sistema();

        Gerente gerente = new Gerente("null", "11144477735", "null@f.com", "null");

        Franquia franquia = new Franquia("null", null, gerente);

        gerente.setFranquia(franquia);

        gerente.cadastrarProduto("A", 0.0, "null", 10);

        gerente.cadastrarVendedor("null", "11144477735", "null@f.com", "null", sistema);

        Vendedor vendedor = franquia.getVendedores().get(0);

        Produto produto = (Produto) franquia.getEstoque().keySet().toArray()[0];

        assertDoesNotThrow(() -> vendedor.cadastrarPedido("null", new ArrayList<>(List.of(new ItemPedido(produto, 6))), FormaPagamento.CREDITO, ModalidadeEntrega.BUSCA_NA_LOJA));

        gerente.removerPedido(vendedor.getPedidos().get(0));

        assertTrue(franquia.getEstoque().get(produto) == 10 && franquia.getPedidos().size() == 0 && vendedor.getPedidos().size() == 0);


    }

    @Test
    public void testEditarPedidoSucesso() throws Exception {
        
        Sistema sistema = new Sistema();

        Gerente gerente = new Gerente("null", "11144477735", "null@f.com", "null");

        Franquia franquia = new Franquia("null", null, gerente);

        gerente.setFranquia(franquia);

        gerente.cadastrarProduto("A", 0.0, "null", 10);

        gerente.cadastrarVendedor("null", "11144477735", "null@f.com", "null", sistema);

        Vendedor vendedor = franquia.getVendedores().get(0);

        Produto produto = (Produto) franquia.getEstoque().keySet().toArray()[0];

        
        assertDoesNotThrow(() -> vendedor.cadastrarPedido("null", new ArrayList<>(List.of(new ItemPedido(produto, 6))), FormaPagamento.CREDITO, ModalidadeEntrega.BUSCA_NA_LOJA));

        gerente.alterarPedido(vendedor.getPedidos().get(0), new ArrayList<>(List.of(new ItemPedido(produto, 4))), FormaPagamento.CREDITO, ModalidadeEntrega.BUSCA_NA_LOJA);

        assertTrue(franquia.getEstoque().get(produto) == 6 && franquia.getPedidos().size() == 1 && vendedor.getPedidos().size() == 1);


    }

}
