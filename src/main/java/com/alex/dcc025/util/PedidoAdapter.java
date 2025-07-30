package com.alex.dcc025.util;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.alex.dcc025.franquia.FormaPagamento;
import com.alex.dcc025.franquia.Franquia;
import com.alex.dcc025.franquia.ItemPedido;
import com.alex.dcc025.franquia.ModalidadeEntrega;
import com.alex.dcc025.franquia.Pedido;
import com.alex.dcc025.usuario.Vendedor;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

public class PedidoAdapter implements JsonSerializer<Pedido>, JsonDeserializer<Pedido> {

    @Override
    public JsonElement serialize(Pedido src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject obj = new JsonObject();
        
        obj.addProperty("id", src.getId());
        obj.addProperty("vendedor", src.getVendedor().getId());
        obj.addProperty("cliente", src.getCliente());
        obj.add("dataHora", context.serialize(src.getDataHora()));
        obj.add("itens", context.serialize(src.getItens()));
        obj.add("formaPagamento", context.serialize(src.getFormaPagamento()));
        obj.add("modalidadeEntrega", context.serialize(src.getModalidadeEntrega()));
        obj.addProperty("valorTotal", src.getValorTotal());

        return obj;
    }

    @Override
    public Pedido deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        
        JsonObject obj = json.getAsJsonObject();

        String id = obj.get("id").getAsString();
        String vendedorId = obj.get("vendedor").getAsString();
        String cliente = obj.get("cliente").getAsString();
        LocalDateTime dataHora = context.deserialize(obj.get("dataHora"), LocalDateTime.class);

        Type tipoLista = new TypeToken<List<ItemPedido>>(){}.getClass();
        List<ItemPedido> itens = context.deserialize(obj.get("itens"), tipoLista);

        
        FormaPagamento formaPagamento = context.deserialize(obj.get("formaPagamento"), FormaPagamento.class);
        ModalidadeEntrega modalidadeEntrega = context.deserialize(obj.get("modalidadeEntrega"), ModalidadeEntrega.class);
        Double valorTotal = obj.get("valorTotal").getAsDouble();
        
        Vendedor vendedor = Serializador.loadVendedores().stream().filter(v -> v.getId().equals(vendedorId)).toList().get(0);


        return new Pedido(id, vendedor, cliente, dataHora, itens, formaPagamento, modalidadeEntrega, valorTotal);

    }

    
}
