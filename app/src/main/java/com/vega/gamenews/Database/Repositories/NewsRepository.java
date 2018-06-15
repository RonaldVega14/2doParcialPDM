package com.vega.gamenews.Database.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.vega.gamenews.Database.DBInstance;
import com.vega.gamenews.Database.Daos.NewsDao;
import com.vega.gamenews.Database.Entities.NewsEntity;
import com.vega.gamenews.Models.News;

import java.util.List;

public class NewsRepository {
    private NewsDao newsDao;

    public NewsRepository(Application app){
        DBInstance db = DBInstance.getInstance(app);
        newsDao = db.newsDao();
    }

    public LiveData<List<NewsEntity>> getAllNews(){
        return newsDao.getAllNews();
    }

    public LiveData<List<NewsEntity>> getNewsbyQuery(String query){
        return newsDao.getNewsByQuery(query);
    }

    public LiveData<List<NewsEntity>> getNewsbyGame(String game){
        return newsDao.getNewsByGame(game);
    }

    public void insert(NewsEntity newsEntity){
        new insertAsyncTask(newsDao).execute(newsEntity);
    }


    //Buscar informacion
    private static class insertAsyncTask extends AsyncTask<NewsEntity, Void, Void>{

        private NewsDao newsDao;

        public insertAsyncTask(NewsDao newsDao){
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(NewsEntity... newsEntities) {
            newsDao.insertNews(newsEntities[0]);
            return null;
        }
    }
}
