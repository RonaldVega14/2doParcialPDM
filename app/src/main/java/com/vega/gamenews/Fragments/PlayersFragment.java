package com.vega.gamenews.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vega.gamenews.Adapters.PlayersAdapter;
import com.vega.gamenews.Database.DBInstance;
import com.vega.gamenews.Database.Entities.PlayerEntity;
import com.vega.gamenews.R;
import com.vega.gamenews.ViewModels.PlayerVModel;

import java.util.ArrayList;
import java.util.List;

public class PlayersFragment extends Fragment{

    private RecyclerView recyclerView;
    private String pass;
    private PlayersAdapter playersAdapter;
    private PlayerVModel playerVModel;
    private String category;
    private DBInstance dbInstance;

    public static PlayersFragment newInstace(String cat){

        Bundle args = new Bundle();
        args.putString("category", cat);
        PlayersFragment fragment = new PlayersFragment();
        fragment.setArguments(args);

        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getActivity().getSharedPreferences("log", Context.MODE_PRIVATE);
        pass = preferences.getString("token", "");
        setHasOptionsMenu(true);

        category = getArguments().getString("category");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.news_fragment, container, false);

        dbInstance = DBInstance.getInstance(getContext());
        recyclerView = v.findViewById(R.id.recyclerView_news);



        playersAdapter = new PlayersAdapter(getActivity().getApplicationContext());
        playerVModel = ViewModelProviders.of(this).get(PlayerVModel.class);
        playerVModel.getPlayers().observe(this, new Observer<List<PlayerEntity>>() {
            @Override
            public void onChanged(@Nullable List<PlayerEntity> playerEntities) {
                playersAdapter.setPlayers(filter(playerEntities));
            }
        });

        recyclerView.setAdapter(playersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;

    }

    public List<PlayerEntity> filter(List<PlayerEntity> players){

        List<PlayerEntity> auxPlayers = new ArrayList<>();

        for(PlayerEntity x : players){
            if(x.getGame() == category){
                auxPlayers.add(x);

            }

        }

        return auxPlayers;

    }


}
