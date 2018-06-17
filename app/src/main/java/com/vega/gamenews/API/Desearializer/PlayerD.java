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

        if (jsonObject.get("_id") != null){
            player.setId(jsonObject.get("_id").getAsString());
        }else{
            player.setId("");
        }


        if (jsonObject.get("name") != null){
            player.setName(jsonObject.get("name").getAsString());
        }else{
            player.setName("");
        }


        if(jsonObject.get("biografia") != null){
            player.setBiografia(jsonObject.get("biografia").getAsString());
        }else{
            player.setBiografia("");
        }

        if(jsonObject.get("avatar") !=null) {
            player.setAvatar(jsonObject.get("avatar").getAsString());
        }else{
            player.setAvatar("");
        }

        if(jsonObject.get("game") !=null) {
            player.setGame(jsonObject.get("game").getAsString());
        }else{
            player.setGame("");
        }


        return player;
    }
}
