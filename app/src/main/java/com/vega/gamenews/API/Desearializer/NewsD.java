package com.vega.gamenews.API.Desearializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.vega.gamenews.Models.News;

import java.lang.reflect.Type;

public class NewsD implements JsonDeserializer<News> {
    @Override
    public News deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        News news = new News();

        JsonObject jsonObject = json.getAsJsonObject();
        news.set_id(jsonObject.get("_id").getAsString());
        news.setTitle(jsonObject.get("title").getAsString());
        news.setBody(jsonObject.get("body").getAsString());
        news.setCoverImage(jsonObject.get("coverImage").getAsString());
        news.setCreate_date(jsonObject.get("create_date").getAsString());
        news.setDescription(jsonObject.get("description").getAsString());
        news.setGame(jsonObject.get("game").getAsString());

        return news;
    }
}
