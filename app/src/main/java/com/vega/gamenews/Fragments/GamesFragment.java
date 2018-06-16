package com.vega.gamenews.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vega.gamenews.Adapters.GamesVPAdapter;
import com.vega.gamenews.Database.DBInstance;
import com.vega.gamenews.R;
import com.vega.gamenews.ViewModels.NewsVModel;

public class GamesFragment extends Fragment {

    private GamesVPAdapter adapter;
    private String pass;
    private String cat;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public static GamesFragment newInstance(String cat){
        Bundle args = new Bundle();
        args.putString("category", cat);
        GamesFragment fragment = new GamesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cat = getArguments().getString("category");
        System.out.println("------------------------------------------------------------------------" + cat + "-------------------------------------------------------------------------");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.games_fragment, container, false);
        viewPager = v.findViewById(R.id.viewpager);
        tabLayout = v.findViewById(R.id.tablayout);

        adapter = new GamesVPAdapter(getChildFragmentManager());

        insertFragments();
        return v;
    }

    public void insertFragments(){
        adapter.addFragment(NewsFragment.newInstance(2, cat), "News");
        adapter.addFragment(new NewsFragment(), "News2");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
