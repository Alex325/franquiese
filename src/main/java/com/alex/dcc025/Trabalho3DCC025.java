package com.alex.dcc025;

import com.alex.dcc025.gui.GUI;
import com.alex.dcc025.usuario.Dono;
import com.alex.dcc025.usuario.Gerente;

public class Trabalho3DCC025 {
    public static void main(String[] args) {

        Dono dono = new Dono("Alice", "000.000.000-00", "alice@franquia.com", "senha123");

        dono.cadastrarGerente("Alice", "000.000.000-00", "alice@franquia.com", "senha123");
        
        Sistema sistema = new Sistema();

        dono.cadastrarFranquia("nome", "endereco", dono.getGerentes().get(0));

        sistema.cadastrarUsuario(dono);
        

        new GUI(sistema);

    }
}