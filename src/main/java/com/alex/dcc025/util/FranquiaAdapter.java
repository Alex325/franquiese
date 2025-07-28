package com.alex.dcc025.util;

import java.lang.reflect.Type;

import com.alex.dcc025.usuario.Dono;
import com.alex.dcc025.usuario.Gerente;
import com.alex.dcc025.usuario.Usuario;
import com.alex.dcc025.usuario.Vendedor;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class FranquiaAdapter implements JsonSerializer<Usuario>, JsonDeserializer<Usuario> {
    
    @Override
    public JsonElement serialize(Usuario src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("tipo", src.getTipo());
        obj.add("dados", context.serialize(src));
        return obj;
    }

    @Override
    public Usuario deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        int tipo = obj.get("tipo").getAsInt();
        JsonElement dados = obj.get("dados");

        switch (tipo) {
            case 0:
                return context.deserialize(dados, Dono.class);
            case 1:
                return context.deserialize(dados, Gerente.class);
            case 2:
                return context.deserialize(dados, Vendedor.class);
            default:
                throw new JsonParseException("Tipo desconhecido: " + tipo);
        }
    }
}
