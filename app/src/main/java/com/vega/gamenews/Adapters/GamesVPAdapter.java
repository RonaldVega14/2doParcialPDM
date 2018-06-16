package com.vega.gamenews.Adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GamesVPAdapter extends FragmentPagerAdapter{

    private List<String> titles;
    private List<Fragment> fragmentList;


    public GamesVPAdapter(FragmentManager fm) {
        super(fm);
        titles = new ArrayList<>();
        fragmentList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    public void addFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        titles.add(title);
    }
}
