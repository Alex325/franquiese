package com.alex.dcc025.util;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

import com.alex.dcc025.franquia.Franquia;
import com.alex.dcc025.franquia.ItemPedido;
import com.alex.dcc025.franquia.Produto;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ItemPedidoAdapter implements JsonSerializer<ItemPedido>, JsonDeserializer<ItemPedido> {

    @Override
    public JsonElement serialize(ItemPedido src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject obj = new JsonObject();

        obj.addProperty("franquia", src.getFranquia().getId());
        obj.addProperty("produto", src.getProduto().getId());
        obj.addProperty("quantidade", src.getQuantidade());

        return obj;
    }

    @Override
    public ItemPedido deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        
        JsonObject obj = json.getAsJsonObject();

        String franquiaId = obj.get("franquia").getAsString();
        String produtoId = obj.get("produto").getAsString();
        int quantidade = obj.get("quantidade").getAsInt();

        Franquia franquia = Serializador.loadFranquias().stream().filter(f -> f.getId().equals(franquiaId)).toList().get(0);


        Produto produto = franquia.getEstoque().stream().filter(p -> p.getId().equals(produtoId)).toList().get(0);


        return new ItemPedido(franquia, produto, quantidade);


    }
    
}
