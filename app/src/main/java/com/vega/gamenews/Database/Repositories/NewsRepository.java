package com.vega.gamenews.Database.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vega.gamenews.API.Desearializer.NewsD;
import com.vega.gamenews.API.GamesAPI;
import com.vega.gamenews.Database.DBInstance;
import com.vega.gamenews.Database.Daos.NewsDao;
import com.vega.gamenews.Database.Entities.NewsEntity;
import com.vega.gamenews.Models.News;

import java.net.SocketTimeoutException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsRepository {
    private NewsDao newsDao;
    private String pass;
    private Context context;

    public NewsRepository(Application app){
        DBInstance db = DBInstance.getInstance(app);
        newsDao = db.newsDao();

        SharedPreferences preferences = app.getSharedPreferences("log", Context.MODE_PRIVATE);
        pass = preferences.getString("token", "");

        getNews();

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

    public void getNews(){


        Gson gson = new GsonBuilder().registerTypeAdapter(News.class, new NewsD()).create();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://gamenewsuca.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit retrofit = builder.build();
        GamesAPI gamesAPI = retrofit.create(GamesAPI.class);

        Call<List<News>> noticias = gamesAPI.getNews("Bearer " + pass);
        noticias.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {

                if(response.isSuccessful()) {
                    for (News x : response.body()) {

                        insert(new NewsEntity(x.get_id(),
                                x.getCreate_date(), x.getTitle(), x.getCoverImage(),
                                x.getDescription(), x.getBody(), x.getGame(),
                                x.getDate(), 0));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
//                if(t instanceof SocketTimeoutException)
//                    Toast.makeText(context, "Timed Out", Toast.LENGTH_SHORT).show();
//                else if(t instanceof Exception)
//                    Toast.makeText(context, "Something went wrong...", Toast.LENGTH_LONG).show();

            }
        });

    }
}
