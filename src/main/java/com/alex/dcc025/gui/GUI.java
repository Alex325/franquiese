/*
 * Alex Sandro de Macedo Pinto
 * 202465551C
 */
package com.alex.dcc025.gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.alex.dcc025.Sistema;
import com.alex.dcc025.gui.tela.login.TelaLogin;

public class GUI {
    private final JFrame janela;
    private final Sistema sistema;

    public GUI(Sistema sistema) {
        
        this.sistema = sistema;

        janela = new JFrame("Sistema de Franquias");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setPreferredSize(new Dimension(1600, 900));
        
        janela.setResizable(false);

        janela.pack();
        janela.setLocationRelativeTo(null);

        janela.addWindowListener(new WindowListener() {
            public void windowClosing(WindowEvent e) {
                sistema.onExit();
            }

            @Override
            public void windowOpened(WindowEvent e) {
                return;
            }

            @Override
            public void windowClosed(WindowEvent e) {
                return;
            }

            @Override
            public void windowIconified(WindowEvent e) {
                return;
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                return;
            }

            @Override
            public void windowActivated(WindowEvent e) {
                return;
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                return;
            }
        });

        janela.setVisible(true);

        mudarTela(new TelaLogin(sistema, this));        

    }

    public void mudarTela(JPanel newTela) {

        janela.setContentPane(newTela);

        janela.pack();

        janela.revalidate();
        janela.repaint();
    }

}
