package com.vega.gamenews.Database.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.vega.gamenews.Database.Entities.CategoryEntity;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(CategoryEntity categoryEntity);

    @Query("SELECT*FROM CategoryEntity")
    LiveData<List<CategoryEntity>> getAllCategories();
}
