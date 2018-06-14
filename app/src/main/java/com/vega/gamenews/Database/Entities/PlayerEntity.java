package com.vega.gamenews.Database.Entities;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class PlayerEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private String id;

    private String name, biografia, avatar, game;

    public PlayerEntity(@NonNull String id, String name, String biografia, String avatar, String game) {
        this.id = id;
        this.name = name;
        this.biografia = biografia;
        this.avatar = avatar;
        this.game = game;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
