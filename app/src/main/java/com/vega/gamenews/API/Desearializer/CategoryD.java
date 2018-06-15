package com.vega.gamenews.API.Desearializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CategoryD implements JsonDeserializer<List<String>> {



    @Override
    public List<String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        List<String> cats = new ArrayList<>();
        JsonArray categoies = json.getAsJsonArray();

        for(JsonElement cat:categoies){
            cats.add(cat.getAsString());
        }

        return cats;
    }
}
