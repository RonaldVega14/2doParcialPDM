package com.vega.gamenews.Database.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class UserEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private String id;

    private String user;
    private String password;

    public UserEntity(@NonNull String id, String user, String password) {
        this.id = id;
        this.user = user;
        this.password = password;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
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
