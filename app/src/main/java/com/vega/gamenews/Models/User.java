package com.vega.gamenews.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    @SerializedName("_id")
    private String id;

    private List<News> favoriteNews;
    private String user;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<News> getFavoriteNews() {
        return favoriteNews;
    }

    public void setFavoriteNews(List<News> favoriteNews) {
        this.favoriteNews = favoriteNews;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
