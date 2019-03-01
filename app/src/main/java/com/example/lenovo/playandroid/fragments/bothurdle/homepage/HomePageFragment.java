package com.example.lenovo.playandroid.fragments.bothurdle.homepage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.base.fragment.BaseFragment;
import com.example.lenovo.playandroid.bean.zlbean.BannerBean;
import com.example.lenovo.playandroid.presenter.zlpresenter.ZlPresenter;
import com.example.lenovo.playandroid.view.IView;
import com.example.lenovo.playandroid.zladapter.ZlRecycAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends BaseFragment<IView, ZlPresenter<IView>> implements IView {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    Unbinder unbinder;
    private String TAG = "HomePageFragment";



    @Override
    public void show(Object o) {
        Log.e("data", o.toString());
    }

    @Override
    public void showError(String error) {
        Log.e(TAG, "showError: " + error);


    }

    @Override
    protected int creatrLayoutId() {
        return R.layout.fragment_about_us;
    }

    @Override
    protected void initData() {
        mPresenter.upwith("");

        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());

        ZlRecycAdapter zlRecycAdapter = new ZlRecycAdapter(new ArrayList<BannerBean.DataBean>());
    }

    @Override
    protected ZlPresenter<IView> createPresenter() {
        return new ZlPresenter<>();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
