package com.vega.gamenews.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vega.gamenews.API.Desearializer.NewsD;
import com.vega.gamenews.API.GamesAPI;
import com.vega.gamenews.Adapters.NewsAdapter;
import com.vega.gamenews.Models.News;
import com.vega.gamenews.R;

import java.net.SocketTimeoutException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private String token;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("logged", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.news_fragment, container, false);

        recyclerView = v.findViewById(R.id.recyclerView_news);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position){
                return (position % 3 == 0 ? 2 : 1);
            }
        });

        newsAdapter = new NewsAdapter(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(newsAdapter);

        return v;



    }

    public static void getNews(String pass, final NewsAdapter newsAdap, final Activity activity){

        Gson gson = new GsonBuilder().registerTypeAdapter(News.class, new NewsD()).create();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://gamenewsuca.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit retrofit = builder.build();

        GamesAPI gamesAPI = retrofit.create(GamesAPI.class);

        Call<List<News>> noticias = gamesAPI.getNews("Bearer " + pass);
        noticias.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                newsAdap.setNews(response.body());
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                if(t instanceof SocketTimeoutException)
                    Toast.makeText(activity, "Timed Out", Toast.LENGTH_SHORT).show();
                else if(t instanceof Exception)
                    Toast.makeText(activity, "Something went wrong...", Toast.LENGTH_LONG).show();

            }
        });

    }
}
