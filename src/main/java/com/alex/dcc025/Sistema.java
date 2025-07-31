package com.alex.dcc025;

import java.util.List;

import com.alex.dcc025.usuario.Usuario;
import com.alex.dcc025.util.Serializador;

public class Sistema {

    private List<Usuario> usuarios;
    private transient Usuario usuarioAtual;

    public Sistema() {
        loadUsuarios();
    }

    public void onExit() {
        salvar();

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

    private void salvar() {
        Serializador.saveState(usuarios);
    }
    
    private void loadUsuarios() {
        usuarios = Serializador.loadState();
    }

    public Usuario getUsuarioAtual() {
        return usuarioAtual;
    }

}
