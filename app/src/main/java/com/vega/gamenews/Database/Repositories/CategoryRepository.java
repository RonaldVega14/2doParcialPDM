package com.vega.gamenews.Database.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.vega.gamenews.Database.DBInstance;
import com.vega.gamenews.Database.Daos.CategoryDao;
import com.vega.gamenews.Database.Entities.CategoryEntity;

import java.util.List;

public class CategoryRepository {

    private CategoryDao categoryDao;

    public CategoryRepository(Application app){
        DBInstance db = DBInstance.getInstance(app);
        this.categoryDao = db.categoryDao();
    }

    public LiveData<List<CategoryEntity>> getAllCategories(){
        return categoryDao.getAllCategories();
    }

    public void insert(CategoryEntity categoryEntity){
        new insertAsyncTask(categoryDao).execute(categoryEntity);
    }

    private static class insertAsyncTask extends AsyncTask<CategoryEntity, Void, Void>{

        private CategoryDao categoryDao;

        private insertAsyncTask(CategoryDao categoryDao){
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(CategoryEntity... categoryEntities) {
            categoryDao.insertCategory(categoryEntities[0]);
            return null;
        }
    }


}
