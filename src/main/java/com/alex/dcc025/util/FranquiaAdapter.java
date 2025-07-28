package com.alex.dcc025.util;

import java.lang.reflect.Type;

import com.alex.dcc025.franquia.Franquia;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class FranquiaAdapter implements JsonSerializer<Franquia>, JsonDeserializer<Franquia> {
    
    @Override
    public JsonElement serialize(Franquia src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(src);
    }

    @Override
    public Franquia deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return context.deserialize(json, typeOfT);
    }
}
