package com.vega.gamenews.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.vega.gamenews.Database.Repositories.NewsRepository;

public class NewsVModel extends AndroidViewModel {

    private NewsRepository newsRepository;

    public NewsVModel(@NonNull Application application) {
        super(application);
        newsRepository = new NewsRepository(application);
    }


}
