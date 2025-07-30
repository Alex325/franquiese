package com.alex.dcc025.util;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alex.dcc025.franquia.Franquia;
import com.alex.dcc025.franquia.Pedido;
import com.alex.dcc025.franquia.Produto;
import com.alex.dcc025.usuario.Gerente;
import com.alex.dcc025.usuario.Vendedor;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class FranquiaAdapter implements JsonSerializer<Franquia>, JsonDeserializer<Franquia> {
    
    @Override
    public JsonElement serialize(Franquia src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject obj = new JsonObject();
        
        obj.addProperty("id", src.getId());
        obj.addProperty("nome", src.getNome());
        obj.add("endereco", context.serialize(src.getEndereco()));
        obj.addProperty("gerente", src.getGerente().getId());
        obj.add("vendedores", context.serialize(src.getVendedores().stream().map(v -> v.getId()).toList()));
        obj.add("pedidos", context.serialize(src.getPedidos()));
        obj.add("estoque", context.serialize(src.getEstoque().stream().map(p -> p.getId()).toList()));

        return obj;
    }

    @Override
    public Franquia deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {


        JsonObject obj = json.getAsJsonObject();

        String id = obj.get("id").getAsString();
        String nome = obj.get("nome").getAsString();
        String endereco = obj.get("endereco").getAsString();
        String gerenteId = obj.get("gerente").getAsString();
        List<String> vendedoresIds = obj.get("vendedores").getAsJsonArray().asList().stream().map(p -> p.getAsString()).toList();
        List<String> estoqueIds = obj.get("estoque").getAsJsonArray().asList().stream().map(p -> p.getAsString()).toList();

        Map<String, Produto> produtos = Serializador.loadProdutos();

        System.out.println(Serializador.usuarios);
        
        Gerente gerente = Serializador.loadGerentes().stream().filter(g -> g.getId().equals(gerenteId)).toList().get(0);
        
        List<Vendedor> vendedores = Serializador.loadVendedores().stream().filter(v -> vendedoresIds.contains(v.getId())).collect(Collectors.toList());
        List<Pedido> pedidos = obj.get("pedidos").getAsJsonArray().asList().stream().map(p -> (Pedido) context.deserialize(p, Pedido.class)).collect(Collectors.toList());
        List<Produto> estoque = estoqueIds.stream().map(pId -> produtos.get(pId)).collect(Collectors.toList());

        return new Franquia(id, nome, endereco, gerente, vendedores, pedidos, estoque);
    }
}
