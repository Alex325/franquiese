package com.alex.dcc025;

import java.util.List;

import com.alex.dcc025.usuario.Usuario;
import com.alex.dcc025.usuario.Vendedor;
import com.alex.dcc025.util.Serializador;

public class Sistema {

    private List<Usuario> usuarios;

    public Sistema() {
        loadUsuarios();
    }

    public void onExit() {
        salvar();

    }

    public Usuario login(String email, String senha) {

        for (Usuario usuario : usuarios) {
            if (usuario.validar(email, senha)) return usuario;
        }

        return null;
    }

    
    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void removerUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    private void salvar() {
        Serializador.saveState(usuarios);
    }
    
    private void loadUsuarios() {
        usuarios = Serializador.loadState();
    }

    public void removerUsuarios(List<Usuario> usuarios) {
        usuarios.removeAll(usuarios);
    }

}
