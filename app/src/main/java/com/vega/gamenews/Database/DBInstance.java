package com.vega.gamenews.Database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.vega.gamenews.Database.Daos.CategoryDao;
import com.vega.gamenews.Database.Daos.NewsDao;
import com.vega.gamenews.Database.Daos.PlayerDao;
import com.vega.gamenews.Database.Daos.UserDao;
import com.vega.gamenews.Database.Entities.CategoryEntity;
import com.vega.gamenews.Database.Entities.NewsEntity;
import com.vega.gamenews.Database.Entities.PlayerEntity;
import com.vega.gamenews.Database.Entities.UserEntity;
import com.vega.gamenews.Models.Player;

@Database(
        entities = {
                UserEntity.class,
                NewsEntity.class,
                CategoryEntity.class,
                PlayerEntity.class,
        },
        exportSchema = false,
        version = 3
)

public abstract class DBInstance extends RoomDatabase {

    private static volatile DBInstance db;
    private static final String DBNAME = "GameNews.db";

    public static synchronized DBInstance createDB(Context context){
        return Room.databaseBuilder(context, DBInstance.class, DBNAME)
                .build();
    }

    public static synchronized DBInstance getInstance(Context context){
        if(db == null){
            db = createDB(context);
        }
        return db;
    }

    public abstract NewsDao newsDao();
    public abstract UserDao userDao();
    public abstract CategoryDao categoryDao();
    public abstract PlayerDao playerDao();

}
