package com.alex.dcc025.util;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import com.alex.dcc025.franquia.Franquia;
import com.alex.dcc025.franquia.Pedido;
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

class UsuarioAdapter implements JsonSerializer<Usuario>, JsonDeserializer<Usuario> {

    @Override
    public JsonElement serialize(Usuario src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("tipo", src.getTipo());
        
        JsonObject elemento = new JsonObject();
        
        elemento.addProperty("id", src.getId());
        elemento.addProperty("nome", src.getNome());
        elemento.addProperty("cpf", src.getCpf());
        elemento.addProperty("email", src.getEmail());
        elemento.addProperty("senha", src.getSenha());

        switch (src.getTipo()) {
            case 0 -> {
                obj.add("dados", context.serialize(elemento));
            }
            case 1 -> {

                Gerente srcAsGerente = (Gerente) src;

                elemento.addProperty("franquia", srcAsGerente.getFranquia().getId());

                obj.add("dados", context.serialize(elemento));
            }
            case 2 -> {

                Vendedor srcAsVendedor = (Vendedor) src;

                elemento.addProperty("franquia", srcAsVendedor.getFranquia().getId());

                obj.add("dados", context.serialize(elemento));

            }
        }

        return obj;
    }

    @Override
    public Usuario deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        int tipo = obj.get("tipo").getAsInt();
        JsonElement dados = obj.get("dados");

        String id = dados.getAsJsonObject().get("id").getAsString();
        String nome = dados.getAsJsonObject().get("nome").getAsString();
        String cpf = dados.getAsJsonObject().get("cpf").getAsString();
        String email = dados.getAsJsonObject().get("email").getAsString();
        String senha = dados.getAsJsonObject().get("senha").getAsString();

        switch (tipo) {
            case 0 -> { 
                Usuario dono = new Dono(id, nome, cpf, email, senha);
                return dono;
            }
            case 1 -> {

                String franquiaId = dados.getAsJsonObject().get("franquia").getAsString();

                Franquia franquia = Serializador.loadFranquias().stream().filter(f -> f.getId().equals(franquiaId)).toList().get(0);
                
                Usuario gerente = new Gerente(id, nome, cpf, email, senha, franquia);
                return gerente;
                
                }
            case 2 -> {
                String franquiaId = dados.getAsJsonObject().get("franquia").getAsString();

                Franquia franquia = Serializador.loadFranquias().stream().filter(f -> f.getId().equals(franquiaId)).toList().get(0);

                List<Pedido> pedidos = franquia.getPedidos().stream().filter(p -> p.getVendedor().getId().equals(id)).collect(Collectors.toList());

                Usuario vendedor = new Vendedor(id, nome, cpf, email, senha, franquia, pedidos);
                return vendedor;
            }
            default -> throw new JsonParseException("Tipo não suportado");
        }
    }
}
