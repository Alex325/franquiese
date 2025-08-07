/*
 * Alex Sandro de Macedo Pinto
 * 202465551C
 */
package com.alex.dcc025;

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