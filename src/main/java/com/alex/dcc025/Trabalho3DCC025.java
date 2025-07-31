package com.alex.dcc025;

import java.util.ArrayList;
import java.util.List;

import com.alex.dcc025.franquia.FormaPagamento;
import com.alex.dcc025.franquia.ItemPedido;
import com.alex.dcc025.franquia.ModalidadeEntrega;
import com.alex.dcc025.gui.GUI;
import com.alex.dcc025.usuario.Dono;
import com.alex.dcc025.util.Serializador;

public class Trabalho3DCC025 {
    public static void main(String[] args) {
        
        Sistema sistema = new Sistema();
        
        if (!Serializador.stateExists()) {   
            Dono dono = new Dono("null", "null", "null", "null");
            dono.cadastrarGerente("Alice", "000.000.000-00", "alice@franquia.com", "senha123");
            dono.cadastrarFranquia("nome", "endereco", dono.getGerentes().get(0));
            dono.getGerente(0).cadastrarVendedor("Alice", "000.000.000-00", "alice@franquia.com", "senha123", dono.getFranquia(0));
            dono.getGerente(0).cadastrarProduto("null", 0, "null", 0);
            dono.getGerente(0).getVendedor(0).cadastrarPedido("null", new ArrayList<>(), FormaPagamento.CREDITO, ModalidadeEntrega.ENTREGA);
            dono.getGerente(0).getVendedor(0).getPedidos().get(0).getItens().addAll(List.of(new ItemPedido(dono.getGerente(0).getFranquia().getEstoque().get(0), 1), new ItemPedido(dono.getGerente(0).getFranquia().getEstoque().get(0), 1), new ItemPedido(dono.getGerente(0).getFranquia().getEstoque().get(0), 1), new ItemPedido(dono.getGerente(0).getFranquia().getEstoque().get(0), 1)));
            sistema.cadastrarUsuario(dono);
            sistema.cadastrarUsuario(dono.getGerente(0));
            sistema.cadastrarUsuario(dono.getGerente(0).getVendedor(0));
        }
            
        new GUI(sistema);

    }
}