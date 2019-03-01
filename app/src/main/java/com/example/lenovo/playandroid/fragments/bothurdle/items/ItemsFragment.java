package com.example.lenovo.playandroid.fragments.bothurdle.items;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.base.fragment.BaseFragment;
import com.example.lenovo.playandroid.presenter.Presenter;
import com.example.lenovo.playandroid.view.IView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemsFragment extends BaseFragment<IView,Presenter<IView>> implements IView{


    public ItemsFragment() {
        // Required empty public constructor
    }

    @Override
    protected int creatrLayoutId() {
        return R.layout.fragment_items;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void show(Object o) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected Presenter<IView> createPresenter() {
        return new Presenter<>();
    }
}
