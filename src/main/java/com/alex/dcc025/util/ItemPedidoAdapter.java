package com.alex.dcc025.util;

import java.lang.reflect.Type;

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

        obj.addProperty("produto", src.getProduto().getId());
        obj.addProperty("quantidade", src.getQuantidade());

        return obj;
    }

    @Override
    public ItemPedido deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        
        JsonObject obj = json.getAsJsonObject();

        String produtoId = obj.get("produto").getAsString();
        int quantidade = obj.get("quantidade").getAsInt();

        Produto produto = Serializador.loadProdutos().get(produtoId);

        return new ItemPedido(produto, quantidade);


    }
    
}
