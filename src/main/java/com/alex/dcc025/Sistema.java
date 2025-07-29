package com.alex.dcc025;

import java.util.List;

import com.alex.dcc025.usuario.Dono;
import com.alex.dcc025.usuario.Usuario;
import com.alex.dcc025.util.Serializador;

public class Sistema {

    private List<Usuario> usuarios;
    private Usuario usuarioAtual;

    public Sistema() {
        loadUsuarios();
    }

    public void onExit() {
        for (Usuario usuario : usuarios) {
            if (usuario.getTipo() == 0) {
                Dono dono = (Dono) usuario;
                dono.savePropriedades();
                break;
            }
        }
        updateUsuarios();

    }

    public Usuario login(String email, String senha) {

        for (Usuario usuario : usuarios) {
            if (usuario.validar(email, senha)) { usuarioAtual = usuario; return usuario; }
        }

        return null;
    }

    
    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    private void updateUsuarios() {
        Serializador.saveUsuarios(usuarios);
    }
    
    private void loadUsuarios() {
        usuarios = Serializador.loadUsuarios();
    }

    public Usuario getUsuarioAtual() {
        return usuarioAtual;
    }

}
