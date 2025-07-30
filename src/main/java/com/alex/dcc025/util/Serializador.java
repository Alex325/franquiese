package com.alex.dcc025.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.alex.dcc025.franquia.Franquia;
import com.alex.dcc025.franquia.ItemPedido;
import com.alex.dcc025.franquia.Pedido;
import com.alex.dcc025.franquia.Produto;
import com.alex.dcc025.usuario.Gerente;
import com.alex.dcc025.usuario.Usuario;
import com.alex.dcc025.usuario.Vendedor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Serializador {

    private static final String PATH_PREFIX = "resources/";
    private static final String PATH_TO_USUARIOS = PATH_PREFIX + "usuarios.json";
    private static final String PATH_TO_FRANQUIAS = PATH_PREFIX + "franquias.json";
    private static final String PATH_TO_PRODUTOS = PATH_PREFIX + "produtos.json";


    public static final Gson gson;

    static {


        try {
            Path path = Paths.get(PATH_PREFIX);

            Files.createDirectory(path);

        } catch (Exception e) {
            // nada
        }

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Usuario.class, new UsuarioAdapter());
        builder.registerTypeAdapter(Franquia.class, new FranquiaAdapter());
        builder.registerTypeAdapter(Pedido.class, new PedidoAdapter());
        builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        builder.registerTypeAdapter(ItemPedido.class, new ItemPedidoAdapter());
        gson = builder.create();
    }

    public static void saveFranquias(List<Franquia> franquias) {
                    
        Type tipoLista = new TypeToken<List<Franquia>>(){}.getType();

        String json = gson.toJson(franquias, tipoLista);

        try (Writer writer = new FileWriter(PATH_TO_FRANQUIAS)) {
            writer.write(json);
        } catch (IOException e) {
        }
    }

    public static void saveUsuarios(List<Usuario> usuarios) {
                    
        Type tipoLista = new TypeToken<List<Usuario>>(){}.getType();

        String json = gson.toJson(usuarios, tipoLista);

        try (Writer writer = new FileWriter(PATH_TO_USUARIOS)) {
            writer.write(json);
        } catch (IOException e) {
        }
    }

    public static void saveProdutos(List<Franquia> franquias) {
        Type tipoMap = new TypeToken<Map<String, Produto>>(){}.getType();

        List<Produto> produtosLista = franquias
        .stream()
        .map(Franquia::getEstoque)
        .reduce(new ArrayList<>(), (identity, proximo) -> { identity.addAll(proximo); return identity; });

        System.out.println(produtosLista);

        Map<String, Produto> produtos = produtosLista.stream().collect(Collectors.toMap(Produto::getId, Function.identity()));



        String json = gson.toJson(produtos, tipoMap);

        try (Writer writer = new FileWriter(PATH_TO_PRODUTOS)) {
            writer.write(json);
        } catch (IOException e) {
        }
    }

    public static List<Usuario> loadUsuarios() {

        List<Usuario> usuarios = new ArrayList<>();

        try (Reader reader = new FileReader(PATH_TO_USUARIOS)) {
            
            Type tipoLista = new TypeToken<List<Usuario>>(){}.getType();

            usuarios = gson.fromJson(reader, tipoLista);

            if (usuarios == null) {
                return new ArrayList<>();
            }

        } catch (IOException e) {
            return new ArrayList<>();
        }

        return usuarios;
    }

    public static List<Franquia> loadFranquias() {

        List<Franquia> franquias = new ArrayList<>();

        try (Reader reader = new FileReader(PATH_TO_FRANQUIAS)) {
            
            Type tipoLista = new TypeToken<List<Franquia>>(){}.getType();

            franquias = gson.fromJson(reader, tipoLista);

            if (franquias == null) {
                return new ArrayList<>();
            }

        } catch (IOException e) {
            return new ArrayList<>();
        }

        return franquias;
    }

    public static List<Gerente> loadGerentes() {

        List<Usuario> usuarios = loadUsuarios();
        List<Gerente> gerentes = new ArrayList<>();

        if (usuarios == null) return gerentes;
        
        gerentes = usuarios.stream()
        .filter(usuario -> usuario.getTipo() == 1)
        .map(usuario -> (Gerente) usuario)
        .collect(Collectors.toList());
        

        return gerentes;
    }

    public static List<Vendedor> loadVendedores() {

        List<Usuario> usuarios = loadUsuarios();
        List<Vendedor> vendedores = new ArrayList<>();

        if (usuarios == null) return vendedores;
        
        vendedores = usuarios.stream()
        .filter(usuario -> usuario.getTipo() == 2)
        .map(usuario -> (Vendedor) usuario)
        .collect(Collectors.toList());
        

        return vendedores;
    }

    public static Map<String, Produto> loadProdutos() {

        Map<String, Produto> produtos = new HashMap<>();

        try (Reader reader = new FileReader(PATH_TO_PRODUTOS)) {
            
            Type tipoMap = new TypeToken<Map<String, Produto>>(){}.getType();

            produtos = gson.fromJson(reader, tipoMap);

            if (produtos == null) {
                return new HashMap<>();
            }

        } catch (IOException e) {
            return new HashMap<>();
        }

        return produtos;
    }


}
