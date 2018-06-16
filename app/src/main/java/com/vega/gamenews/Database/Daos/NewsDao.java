package com.vega.gamenews.Database.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.vega.gamenews.Database.Entities.NewsEntity;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNews(NewsEntity news);

    @Query("SELECT * FROM NewsEntity")
    LiveData<List<NewsEntity>> getAllNews();

    @Query("SELECT * FROM NewsEntity WHERE title like  :query")
    LiveData<List<NewsEntity>> getNewsByQuery(String query);

    @Query("SELECT * FROM NewsEntity WHERE game =:game")
    LiveData<List<NewsEntity>> getNewsByGame(String game);
}
