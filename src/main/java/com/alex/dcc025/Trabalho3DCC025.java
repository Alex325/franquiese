package com.alex.dcc025;

import com.alex.dcc025.franquia.Franquia;
import com.alex.dcc025.gui.GUI;
import com.alex.dcc025.usuario.Dono;
import com.alex.dcc025.usuario.Gerente;
import com.alex.dcc025.util.Serializador;

public class Trabalho3DCC025 {
    public static void main(String[] args) {
        Dono dono = new Dono(0,"Alice", "000.000.000-00", "alice@franquia.com", "senha123");
        
        Gerente gerente = new Gerente(1, "V", "asd", "asd", "asd");

        dono.cadastrarFranquia("Amogus", "Sim", gerente);

        Sistema sistema = new Sistema();

        sistema.cadastrarUsuario(dono);
        sistema.cadastrarUsuario(gerente);

        new GUI(sistema);

    }
}