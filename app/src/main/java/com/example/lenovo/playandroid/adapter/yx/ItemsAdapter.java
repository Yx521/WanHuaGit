package com.example.lenovo.playandroid.adapter.yx;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lenovo.playandroid.beans.yx.ProjectClassify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/3/1.
 */

public class ItemsAdapter extends FragmentStatePagerAdapter{

    private List<ProjectClassify.DataBean> mTitle;
    private ArrayList<Fragment> mFrag;

    public ItemsAdapter(FragmentManager fm, List<ProjectClassify.DataBean> data, ArrayList<Fragment> fragments) {
        super(fm);
        this.mTitle = data;
        this.mFrag = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFrag.get(position);
    }

    @Override
    public int getCount() {
        return mFrag.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position).getName();
    }
}
