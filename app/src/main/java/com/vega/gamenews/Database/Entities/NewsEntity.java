package com.vega.gamenews.Database.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class NewsEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private String id;

    @ColumnInfo(name = "create_date")
    private String createdate;

    private String title;
    private String coverImage;
    private String description;
    private String body;
    private String game;
    private String date;

    private int favo;

    public NewsEntity(@NonNull String id, String createdate, String title, String coverImage, String description, String body, String game, String date, int favo) {
        this.id = id;
        this.createdate = createdate;
        this.title = title;
        this.coverImage = coverImage;
        this.description = description;
        this.body = body;
        this.game = game;
        this.date = date;
        this.favo = favo;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFavo() {
        return favo;
    }

    public void setFavo(int favo) {
        this.favo = favo;
    }
}
