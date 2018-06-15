package com.vega.gamenews.Database.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vega.gamenews.API.Desearializer.CategoryD;
import com.vega.gamenews.API.GamesAPI;
import com.vega.gamenews.Activities.HomeActivity;
import com.vega.gamenews.Activities.MainActivity;
import com.vega.gamenews.Database.DBInstance;
import com.vega.gamenews.Database.Daos.CategoryDao;
import com.vega.gamenews.Database.Entities.CategoryEntity;
import com.vega.gamenews.R;
import com.vega.gamenews.ViewModels.CategoryVModel;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryRepository {

    private CategoryDao categoryDao;
    String pass;
    Context context;

    public CategoryRepository(Application app){
        DBInstance db = DBInstance.getInstance(app);
        this.categoryDao = db.categoryDao();

        SharedPreferences preferences = app.getSharedPreferences("log", Context.MODE_PRIVATE);
        pass = preferences.getString("token", "");

        getCategories();
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

    public void getCategories(){

        Gson gson=new GsonBuilder()
                .registerTypeAdapter(ArrayList.class, new CategoryD())
                .create();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(GamesAPI.END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        GamesAPI gamesAPI = retrofit.create(GamesAPI.class);
        Call<List<String>> categories = gamesAPI.getCategories("Beared " + pass);
        categories.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.body()!=null){
                    for(String cat : response.body()){
                        insert(new CategoryEntity(cat));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

                if (t instanceof SocketTimeoutException){
                    Toast.makeText(context, R.string.Fail2log1, Toast.LENGTH_SHORT).show();
                }else if (t instanceof Exception){
                    Toast.makeText(context, R.string.Fail2log2, Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }

            }
        });

    }





}
