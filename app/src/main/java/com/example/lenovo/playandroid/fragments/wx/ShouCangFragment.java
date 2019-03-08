package com.example.lenovo.playandroid.fragments.wx;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.adapter.wx.CollectAdapter;
import com.example.lenovo.playandroid.base.fragment.BaseFragment;
import com.example.lenovo.playandroid.beans.wx.Data;
import com.example.lenovo.playandroid.presenter.wx.PresenterShou;
import com.example.lenovo.playandroid.view.yx.IView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShouCangFragment extends BaseFragment<IView, PresenterShou<IView>> implements IView {


    @BindView(R.id.collect_recycler_view)
    RecyclerView collectRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout normalView;
    @BindView(R.id.collect_floating_action_btn)
    FloatingActionButton collectFloatingActionBtn;
    Unbinder unbinder;
    private CollectAdapter collectAdapter;

    @Override
    protected int creatrLayoutId() {
        return R.layout.fragment_shou_cang;
    }

    @Override
    protected void initData() {
        Map<String,Object> map = new HashMap<>();
        map.put("biao","shou");
        mPresenter.FishData(map);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        collectRecyclerView.setLayoutManager(manager);
        ArrayList<Data.DataBean.DatasBean> datasBeans = new ArrayList<>();
        collectAdapter = new CollectAdapter(getContext(),datasBeans);
        collectRecyclerView.setAdapter(collectAdapter);
    }

    @Override
    public void show(Object o) {
        Map<String,Object> map = (Map<String, Object>) o;
        String biao = (String) map.get("biao");
        if("shou".equals(biao)){
            Data data = (Data) map.get("va");
            if(data.getErrorCode()==0){
                List<Data.DataBean.DatasBean> datas = data.getData().getDatas();
                collectAdapter.addData(datas);
            }
        }else if("shan".equals(biao)){

        }

    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected PresenterShou<IView> createPresenter() {
        return new PresenterShou<>();
    }


}
