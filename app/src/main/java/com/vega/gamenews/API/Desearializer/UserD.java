package com.vega.gamenews.API.Desearializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.vega.gamenews.Models.User;

import java.lang.reflect.Type;
import java.util.List;

public class UserD implements JsonDeserializer<User> {
    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        User user = new User();

        JsonObject jsonObject = new JsonObject();
        user.setId(jsonObject.get("_id").getAsString());
        user.setUser(jsonObject.get("user").getAsString());
        user.setPassword(jsonObject.get("password").getAsString());
        //falta asignar la lista de favorite News

        return user;
    }
}
