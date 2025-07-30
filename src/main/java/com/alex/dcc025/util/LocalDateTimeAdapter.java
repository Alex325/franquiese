package com.alex.dcc025.util;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.alex.dcc025.franquia.FormaPagamento;
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

public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    @Override
    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject obj = new JsonObject();
        
        obj.addProperty("ano", src.getYear());
        obj.addProperty("mes", src.getMonth().getValue());
        obj.addProperty("dia", src.getDayOfMonth());
        obj.addProperty("hora", src.getHour());
        obj.addProperty("minuto", src.getMinute());
        obj.addProperty("segundo", src.getSecond());
        obj.addProperty("nano", src.getNano());

        return obj;
    }

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        
        JsonObject obj = json.getAsJsonObject();

        int ano = obj.get("ano").getAsInt();
        int mes = obj.get("mes").getAsInt();
        int dia = obj.get("dia").getAsInt();
        int hora = obj.get("hora").getAsInt();
        int minuto = obj.get("minuto").getAsInt();
        int segundo = obj.get("segundo").getAsInt();
        int nano = obj.get("nano").getAsInt();

        return LocalDateTime.of(ano, mes, dia, hora, minuto, segundo, nano);


    }
    
}
