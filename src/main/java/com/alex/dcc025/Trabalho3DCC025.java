package com.alex.dcc025;

import java.util.ArrayList;
import java.util.List;

import com.alex.dcc025.franquia.Endereco;
import com.alex.dcc025.franquia.ItemPedido;
import com.alex.dcc025.franquia.Pedido.FormaPagamento;
import com.alex.dcc025.franquia.Pedido.ModalidadeEntrega;
import com.alex.dcc025.franquia.Endereco.UF;
import com.alex.dcc025.gui.GUI;
import com.alex.dcc025.usuario.Dono;
import com.alex.dcc025.util.Serializador;

public class Trabalho3DCC025 {
    public static void main(String[] args) {
        
        Sistema sistema = new Sistema();
        
        if (!Serializador.stateExists()) {   
            Dono dono = new Dono("null", "null", "null", "null");
            dono.cadastrarGerente("Alice", "000.000.000-00", "alice@franquia.co", "senha123", sistema);
            dono.cadastrarFranquia("nome", new Endereco("null", "null", "null", "null", "null", "null", UF.AC), dono.getGerentes().get(0));
            dono.getGerente(0).cadastrarVendedor("Alice", "000.000.000-00", "alice@franquia.com", "senha123", sistema);
            dono.getGerente(0).cadastrarProduto("null", 0, "null", 0);
            dono.getGerente(0).cadastrarProduto("null2", 0, "null2", 0);
            dono.getGerente(0).getVendedor(0).cadastrarPedido("null", new ArrayList<>(), FormaPagamento.CREDITO, ModalidadeEntrega.ENTREGA);
            sistema.cadastrarUsuario(dono);

        }
            
        new GUI(sistema);

    }
}