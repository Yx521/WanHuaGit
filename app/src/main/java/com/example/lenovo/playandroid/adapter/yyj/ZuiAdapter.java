package com.example.lenovo.playandroid.adapter.yyj;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ZuiAdapter extends FragmentPagerAdapter {
    private ArrayList<String> strings;
    private ArrayList<Fragment> ll;

    public ZuiAdapter(FragmentManager fm,ArrayList<String> mstrings,ArrayList<Fragment> mll) {
        super(fm);
        this.strings=mstrings;
        this.ll=mll;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return ll.get(position);
    }

    @Override
    public int getCount() {
        return ll.size();
    }
}
