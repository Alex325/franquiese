package com.alex.dcc025;

import java.util.ArrayList;
import java.util.List;

import com.alex.dcc025.franquia.endereco.Endereco;
import com.alex.dcc025.franquia.endereco.Endereco.UF;
import com.alex.dcc025.franquia.pedido.ItemPedido;
import com.alex.dcc025.franquia.pedido.Pedido.FormaPagamento;
import com.alex.dcc025.franquia.pedido.Pedido.ModalidadeEntrega;
import com.alex.dcc025.gui.GUI;
import com.alex.dcc025.usuario.usuario.Dono;
import com.alex.dcc025.util.Serializador;

public class Trabalho3DCC025 {
    public static void main(String[] args) {
        
        Sistema sistema = new Sistema();
        
        if (!Serializador.stateExists()) {   
            Dono dono = new Dono("null", "11144477735", "null@gmail.com", "null");
            sistema.cadastrarUsuario(dono);

        }
            
        new GUI(sistema);

    }
}