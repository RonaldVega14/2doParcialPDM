package com.vega.gamenews.API.Desearializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.vega.gamenews.Models.Player;

import java.lang.reflect.Type;

public class PlayerD implements JsonDeserializer<Player> {
    @Override
    public Player deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Player player = new Player();

        JsonObject jsonObject = new JsonObject();
        player.setId(jsonObject.get("_id").getAsInt());
        player.setName(jsonObject.get("name").getAsString());
        player.setBiografia(jsonObject.get("biografia").getAsString());
        player.setAvatar(jsonObject.get("avatar").getAsString());
        player.setGame(jsonObject.get("game").getAsString());

        return player;
    }
}
