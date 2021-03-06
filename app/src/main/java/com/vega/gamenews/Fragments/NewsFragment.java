package com.vega.gamenews.Fragments;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vega.gamenews.API.Desearializer.NewsD;
import com.vega.gamenews.API.GamesAPI;
import com.vega.gamenews.Adapters.NewsAdapter;
import com.vega.gamenews.Database.DBInstance;
import com.vega.gamenews.Database.Entities.NewsEntity;
import com.vega.gamenews.Models.News;
import com.vega.gamenews.R;
import com.vega.gamenews.ViewModels.NewsVModel;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsFragment extends Fragment {

    public static final int NEWS = 1;
    public static final int GAMENEWS = 2;
    public static final int FAVNEWS = 3;

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private GridLayoutManager gridLayoutManager;
    private String token, cat;
    private NewsVModel newsVModel;
    private SearchView searchView;
    private DBInstance db;
    private int Type;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("logged", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        setHasOptionsMenu(true);

        Type = getArguments() != null ? getArguments().getInt("fragmentType") : 1;
        cat = getArguments() != null ? getArguments().getString("category") : "";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.news_fragment, container, false);

        db = DBInstance.getInstance(getContext());
        recyclerView = v.findViewById(R.id.recyclerView_news);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position){
                return (position % 3 == 0 ? 2 : 1);
            }
        });

        newsAdapter = new NewsAdapter(getActivity().getApplicationContext());
        newsVModel = ViewModelProviders.of(this).get(NewsVModel.class);
        newsVModel.getAllNews().observe(this, new Observer<List<NewsEntity>>() {
            @Override
            public void onChanged(@Nullable List<NewsEntity> newsEntities) {

                newsAdapter.setNews(selectCat(newsEntities));

            }
        });

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(newsAdapter);

        return v;

    }

    public static NewsFragment newInstance(int type,String Category){

        Bundle args = new Bundle();
        args.putInt("fragmentType", type);
        args.putString("category", Category);

        NewsFragment fragment= new NewsFragment();
        fragment.setArguments(args);

        return fragment;

    }

    public List<NewsEntity> selectCat(List<NewsEntity> entities){

        if(Type == FAVNEWS){
            List<NewsEntity> faventities= new ArrayList<>();
            return faventities;

        }
        if (Type == GAMENEWS){
            List<NewsEntity> game = new ArrayList<>();
            for(NewsEntity news : entities){
                if (news.getGame().equals(cat)){
                    game.add(news);
                }

            }
            return game;
        }else {
            return entities;
        }

    }

}
