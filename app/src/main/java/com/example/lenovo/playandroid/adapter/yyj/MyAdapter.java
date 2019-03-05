package com.example.lenovo.playandroid.adapter.yyj;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.example.lenovo.playandroid.fragments.yyj.FackFragment;

import java.util.ArrayList;

public class MyAdapter extends FragmentPagerAdapter {
    private ArrayList<String>strings;
    private ArrayList<FackFragment> fackFragments;
    public MyAdapter(FragmentManager fm, ArrayList<String>str, ArrayList<FackFragment> ll) {
        super(fm);
        this.strings=str;
        this.fackFragments=ll;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fackFragments.get(position);
    }

    @Override
    public int getCount() {
        return fackFragments.size();
    }

}
