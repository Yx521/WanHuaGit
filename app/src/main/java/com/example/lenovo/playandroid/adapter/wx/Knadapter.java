package com.example.lenovo.playandroid.adapter.wx;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.airbnb.lottie.L;
import com.example.lenovo.playandroid.beans.wx.Batree;
import com.example.lenovo.playandroid.fragments.wx.ReFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2019/3/3.
 */

public class Knadapter extends FragmentPagerAdapter{
     List<String> mStringList = new ArrayList<>();
    List<Fragment> mFragmentList = new ArrayList<>();

    public Knadapter(FragmentManager fm, List<String> mStringList, List<Fragment> mFragmentList) {
        super(fm);
        this.mStringList = mStringList;
        this.mFragmentList = mFragmentList;
    }

    public Knadapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mStringList.get(position);
    }
}
