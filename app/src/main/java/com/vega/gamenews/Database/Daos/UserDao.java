package com.vega.gamenews.Database.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.vega.gamenews.Database.Entities.UserEntity;

@Dao
public interface UserDao {

    @Insert
    void insertUser (UserEntity user);

    @Query("SELECT*FROM UserEntity WHERE user like :user")
    LiveData<UserEntity> getUserbyName (String user);
}
