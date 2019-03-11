package com.example.lenovo.playandroid.fragments.yyj;


import android.app.Presentation;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.adapter.yyj.MyAdapter;

import com.example.lenovo.playandroid.base.fragment.BaseFragment;
import com.example.lenovo.playandroid.beans.yyj.Fuyong;
import com.example.lenovo.playandroid.fragments.yyj.FackFragment;


import com.example.lenovo.playandroid.presenter.yyj.Main_FuP;
import com.example.lenovo.playandroid.view.yx.IView;
import com.flyco.tablayout.SlidingTabLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VipcnFragment extends BaseFragment<IView, Main_FuP<IView>> implements IView {




   // private TabLayout tab;
    private ViewPager vp;
    private ArrayList<FackFragment> fackFragments;
    private SlidingTabLayout tab;

    public VipcnFragment() {
        // Required empty public constructor
    }


    @Override
    protected int creatrLayoutId() {
        return R.layout.fragment_play_android;
    }

    @Override
    protected void initData() {
        tab = getActivity().findViewById(R.id.tab);
        vp = getActivity().findViewById(R.id.vp);

        mPresenter.bindup(null);

    }


    @Override
    public void showError(String error) {

    }

    @Override
    public void show(Object o) {
        Fuyong fuyong=(Fuyong)o;
        fackFragments = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        List<Fuyong.DataBean> data = fuyong.getData();
        for (int i = 0; i < data.size(); i++) {
            fackFragments.add(new FackFragment(data.get(i).getId(),data.get(i).getName()));
            strings.add(data.get(i).getName());

        }
        MyAdapter myAdapter = new MyAdapter(getChildFragmentManager(), strings, fackFragments);
        vp.setAdapter(myAdapter);
        tab.setViewPager(vp);
       // tab.setupWithViewPager(vp);

    }

    @Override
    protected Main_FuP<IView> createPresenter() {
        return new Main_FuP<>();
    }

}
