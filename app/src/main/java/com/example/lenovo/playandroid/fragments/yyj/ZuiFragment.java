package com.example.lenovo.playandroid.fragments.yyj;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.adapter.yyj.XreAdapter;
import com.example.lenovo.playandroid.adapter.yyj.ZuihouAdapter;
import com.example.lenovo.playandroid.base.fragment.BaseFragment;
import com.example.lenovo.playandroid.beans.yyj.Bean;
import com.example.lenovo.playandroid.presenter.yyj.Main_beanP;
import com.example.lenovo.playandroid.view.yx.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


public class ZuiFragment extends BaseFragment<IView, Main_beanP<IView>> implements IView, XRecyclerView.LoadingListener {
    private int page = 0;
    private XRecyclerView xre;
    private String keywork;
    private ZuihouAdapter zuihouAdapter;

    public ZuiFragment() {
        // Required empty public constructor
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true) //在ui线程执行
    public void getSum(String cc) {
        this.keywork = cc;
        Log.e("eve1", cc + "");

        mPresenter.beanP( keywork, page + "");
    }


    @Override
    protected int creatrLayoutId() {
        return R.layout.fragment_zui;
    }

    @Override
    protected void initData() {
        xre = getActivity().findViewById(R.id.xxre);
        EventBus.getDefault().register(this);
        xre.setLoadingListener(this);

    }


    @Override
    protected Main_beanP<IView> createPresenter() {
        return new Main_beanP<>();
    }

    @Override
    public void show(Object o) {
        Bean bean = (Bean) o;
        List<Bean.DataBean.DatasBean> datas = bean.getData().getDatas();
        xre.setLayoutManager(new LinearLayoutManager(getActivity()));
        zuihouAdapter = new ZuihouAdapter(datas);
        xre.setAdapter(zuihouAdapter);

    }

    @Override
    public void showError(String error) {
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onRefresh() {
        page = 0;
        mPresenter.beanP( keywork, page + "");
        xre.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        page++;
        mPresenter.beanP( keywork, page + "");
        zuihouAdapter.notifyDataSetChanged();
    }
}
