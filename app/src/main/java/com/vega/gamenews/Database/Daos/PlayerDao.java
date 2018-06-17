package com.vega.gamenews.Database.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.vega.gamenews.Database.Entities.PlayerEntity;

import java.util.List;

@Dao
public interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlayer(PlayerEntity playerEntity);

    @Query("SELECT * FROM PlayerEntity WHERE game=:game")
    LiveData<List<PlayerEntity>> getPlayerByGame(String game);

}
