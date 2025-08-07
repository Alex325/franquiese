/*
 * Alex Sandro de Macedo Pinto
 * 202465551C
 */
package com.alex.dcc025.vendedor;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.alex.dcc025.Sistema;
import com.alex.dcc025.exception.CampoTextoInvalidoException;
import com.alex.dcc025.exception.PedidoVazioException;
import com.alex.dcc025.franquia.Franquia;
import com.alex.dcc025.franquia.pedido.ItemPedido;
import com.alex.dcc025.franquia.pedido.Produto;
import com.alex.dcc025.franquia.pedido.Pedido.FormaPagamento;
import com.alex.dcc025.franquia.pedido.Pedido.ModalidadeEntrega;
import com.alex.dcc025.usuario.usuario.Gerente;
import com.alex.dcc025.usuario.usuario.Vendedor;

public class VendedorTest {

    @Test
    public void testCadastrarPedidoSucesso() throws Exception {
        
        Sistema sistema = new Sistema();

        Gerente gerente = new Gerente("null", "11144477735", "null@f.com", "null");

        Franquia franquia = new Franquia("null", null, gerente);

        gerente.setFranquia(franquia);

        gerente.cadastrarProduto("A", 0.0, "null", 10);

        gerente.cadastrarVendedor("null", "11144477735", "null@f.com", "null", sistema);

        Vendedor vendedor = franquia.getVendedores().get(0);

        Produto produto = (Produto) franquia.getEstoque().keySet().toArray()[0];

        assertDoesNotThrow(() -> vendedor.cadastrarPedido("null", new ArrayList<>(List.of(new ItemPedido(produto, 6))), FormaPagamento.CREDITO, ModalidadeEntrega.BUSCA_NA_LOJA));

        assertTrue(franquia.isEstoqueBaixo(produto));

    }

    @Test
    public void testCadastrarPedidoFalha() throws Exception {
        
        Sistema sistema = new Sistema();

        Gerente gerente = new Gerente("null", "11144477735", "null@f.com", "null");

        Franquia franquia = new Franquia("null", null, gerente);

        gerente.setFranquia(franquia);

        gerente.cadastrarProduto("A", 0.0, "null", 10);

        gerente.cadastrarVendedor("null", "11144477735", "null@f.com", "null", sistema);

        Vendedor vendedor = franquia.getVendedores().get(0);

        Produto produto = (Produto) franquia.getEstoque().keySet().toArray()[0];

        assertThrows(CampoTextoInvalidoException.class, () -> vendedor.cadastrarPedido("", new ArrayList<>(List.of(new ItemPedido(produto, 1))), FormaPagamento.CREDITO, ModalidadeEntrega.BUSCA_NA_LOJA));
        assertThrows(CampoTextoInvalidoException.class, () -> vendedor.cadastrarPedido("123", new ArrayList<>(List.of(new ItemPedido(produto, 1))), FormaPagamento.CREDITO, ModalidadeEntrega.BUSCA_NA_LOJA));
        assertThrows(PedidoVazioException.class, () -> vendedor.cadastrarPedido("null", new ArrayList<>(), FormaPagamento.CREDITO, ModalidadeEntrega.BUSCA_NA_LOJA));

    }
}
