package com.vega.gamenews.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.vega.gamenews.Database.Entities.NewsEntity;
import com.vega.gamenews.Database.Repositories.NewsRepository;

import java.util.List;

public class NewsVModel extends AndroidViewModel {

    private NewsRepository newsRepository;

    public NewsVModel(@NonNull Application application) {
        super(application);
        newsRepository = new NewsRepository(application);
    }

    public LiveData<List<NewsEntity>> getAllNews(){
        return newsRepository.getAllNews();
    }

    public LiveData<List<NewsEntity>> getNewsByQuery(String query){
        return newsRepository.getNewsbyQuery(query);
    }

    public LiveData<List<NewsEntity>> getNewsByGame(String game){
        return newsRepository.getNewsbyGame(game);
    }

    public void insert(NewsEntity newsEntity){
        newsRepository.insert(newsEntity);
    }
}
