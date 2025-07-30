package com.alex.dcc025;

import java.util.ArrayList;
import java.util.List;

import com.alex.dcc025.franquia.FormaPagamento;
import com.alex.dcc025.franquia.ItemPedido;
import com.alex.dcc025.franquia.ModalidadeEntrega;
import com.alex.dcc025.franquia.Produto;
import com.alex.dcc025.gui.GUI;
import com.alex.dcc025.usuario.Dono;

public class Trabalho3DCC025 {
    public static void main(String[] args) {

        Dono dono = new Dono("Alice", "000.000.000-00", "alice@franquia.com", "senha123");

        dono.cadastrarGerente("Alice", "000.000.000-00", "alice@franquia.com", "senha123");
        
        Sistema sistema = new Sistema();

        dono.cadastrarFranquia("nome", "endereco", dono.getGerentes().get(0));

        dono.getGerente(0).cadastrarVendedor("Alice", "000.000.000-00", "alice@franquia.com", "senha123", dono.getFranquia(0));

        dono.getGerente(0).cadastrarProduto("null", 0, "null", 0);

        dono.getGerente(0).getVendedor(0).cadastrarPedido("null", List.of(new ItemPedido(dono.getGerente(0).getFranquia(), dono.getGerente(0).getFranquia().getEstoque().get(0), 1), new ItemPedido(dono.getGerente(0).getFranquia(), dono.getGerente(0).getFranquia().getEstoque().get(0), 1), new ItemPedido(dono.getGerente(0).getFranquia(), dono.getGerente(0).getFranquia().getEstoque().get(0), 1), new ItemPedido(dono.getGerente(0).getFranquia(), dono.getGerente(0).getFranquia().getEstoque().get(0), 1)), FormaPagamento.CREDITO, ModalidadeEntrega.ENTREGA);

        sistema.cadastrarUsuario(dono);
        sistema.cadastrarUsuario(dono.getGerente(0));
        sistema.cadastrarUsuario(dono.getGerente(0).getVendedor(0));
        

        new GUI(sistema);

    }
}