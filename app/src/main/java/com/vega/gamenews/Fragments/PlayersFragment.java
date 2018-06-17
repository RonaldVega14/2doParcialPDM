package com.vega.gamenews.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vega.gamenews.Adapters.PlayersAdapter;
import com.vega.gamenews.ViewModels.PlayerVModel;

public class PlayersFragment extends Fragment{

    private RecyclerView recyclerView;
    private String pass;
    private PlayersAdapter playersAdapter;
    private PlayerVModel playerVModel;

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
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
