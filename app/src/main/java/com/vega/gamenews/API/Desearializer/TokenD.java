package com.vega.gamenews.API.Desearializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.vega.gamenews.Models.Login;

import java.lang.reflect.Type;

public class TokenD implements JsonDeserializer<Login>{
    @Override
    public Login deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Login login = new Login();
        if(json.getAsJsonObject()!=null) {
            JsonObject token = json.getAsJsonObject();
            if(token.get("token")!=null){
                login.setToken(token.get("token").getAsString());
                login.setResponse(true);
            }else{
                login.setToken(token.get("message").getAsString());
                login.setResponse(false);
            }
        }

        return login;
    }
}
