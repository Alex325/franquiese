package com.alex.dcc025.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alex.dcc025.franquia.Endereco;
import com.alex.dcc025.franquia.Endereco.UF;
import com.alex.dcc025.franquia.Franquia;
import com.alex.dcc025.franquia.ItemPedido;
import com.alex.dcc025.franquia.Pedido;
import com.alex.dcc025.franquia.PedidoAlteracao;
import com.alex.dcc025.franquia.PedidoExclusao;
import com.alex.dcc025.franquia.Pedido.FormaPagamento;
import com.alex.dcc025.franquia.Pedido.ModalidadeEntrega;
import com.alex.dcc025.franquia.Produto;
import com.alex.dcc025.franquia.Solicitacao;
import com.alex.dcc025.usuario.Dono;
import com.alex.dcc025.usuario.Gerente;
import com.alex.dcc025.usuario.Usuario;
import com.alex.dcc025.usuario.Vendedor;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;


public class Serializador {

    private static final String PATH_PREFIX = "resources/";
    private static final String PATH_TO_ESTADO = PATH_PREFIX + "estado.bin";

    private static final Kryo kryo;

    private static final File estado = new File(PATH_TO_ESTADO);

    static {


        try {
            Path path = Paths.get(PATH_PREFIX);

            Files.createDirectory(path);

        } catch (Exception e) {
            // nada
        }


        kryo = new Kryo();

        kryo.setReferences(true);

        kryo.register(Usuario.class);
        kryo.register(Dono.class);
        kryo.register(Gerente.class);
        kryo.register(Vendedor.class);
        kryo.register(Franquia.class);
        kryo.register(Endereco.class);
        kryo.register(UF.class);
        kryo.register(Produto.class);
        kryo.register(Pedido.class);
        kryo.register(ItemPedido.class);
        kryo.register(FormaPagamento.class);
        kryo.register(ModalidadeEntrega.class);
        kryo.register(LocalDateTime.class);
        kryo.register(ArrayList.class);
        kryo.register(HashMap.class);
        kryo.register(Solicitacao.class);
        kryo.register(PedidoAlteracao.class);
        kryo.register(PedidoExclusao.class);
        
    }

    public static void saveState(List<Usuario> usuarios) {
        try (Output output = new Output(new FileOutputStream(PATH_TO_ESTADO))) {

            kryo.writeObject(output, usuarios);
            
        } catch (Exception e) {
            estado.delete();
            System.out.println("Erro ao serializar");
            e.printStackTrace();
        }
    }

    public static List<Usuario> loadState() {

        List<Usuario> usuarios = new ArrayList<>();

        if (!estado.exists()) return usuarios;

        try (Input input = new Input(new FileInputStream(PATH_TO_ESTADO))) {

            usuarios = kryo.readObject(input, ArrayList.class);

        } catch (Exception e) {
            estado.delete();
            System.out.println("Erro ao desserializar");
            e.printStackTrace();
        }

        return usuarios;
    }

    public static boolean stateExists() {
        return estado.exists();
    }


}
