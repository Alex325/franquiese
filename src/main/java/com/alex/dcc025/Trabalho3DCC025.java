package com.alex.dcc025;

import java.util.List;

import com.alex.dcc025.franquia.FormaPagamento;
import com.alex.dcc025.franquia.ItemPedido;
import com.alex.dcc025.franquia.ModalidadeEntrega;
import com.alex.dcc025.gui.GUI;
import com.alex.dcc025.usuario.Dono;

public class Trabalho3DCC025 {
    public static void main(String[] args) {

        Sistema sistema = new Sistema();

        new GUI(sistema);

    }
}