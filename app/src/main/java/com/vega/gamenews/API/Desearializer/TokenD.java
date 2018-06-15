package com.vega.gamenews.API.Desearializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.vega.gamenews.Models.Login;

import java.lang.reflect.Type;

public class TokenD implements JsonDeserializer<String>{
    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        String login = "";
        if(json.getAsJsonObject()!=null) {
            JsonObject token = json.getAsJsonObject();
            if(token.get("token")!=null){
                login = token.get("token").getAsString();
            }else{
                login = token.get("message").getAsString();

            }
        }

        return login;
    }
}
