package com.example.lenovo.playandroid.fragments.wx;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.activitys.wx.Multiplexing;
import com.example.lenovo.playandroid.adapter.wx.Wxadapter;
import com.example.lenovo.playandroid.base.fragment.BaseFragment;
import com.example.lenovo.playandroid.beans.wx.Batree;
import com.example.lenovo.playandroid.presenter.wx.WxPresenter;
import com.example.lenovo.playandroid.view.yx.IView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowledgeHierarchyFragment extends BaseFragment<IView, WxPresenter<IView>> implements IView {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Wxadapter wxadapter;
    private Object data;

    @Override
    public void show(Object o) {
        Batree batree = (Batree) o;
        List<Batree.DataBean> data = batree.getData();
        Log.i("yx", "show: "+data.size() );
        wxadapter.addData(data);

    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected int creatrLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    protected void initData() {
        mPresenter.FishData("");
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(manager);
        List<Batree.DataBean> list = new ArrayList<>();
        wxadapter = new Wxadapter(list, getContext());
        rv.setAdapter(wxadapter);

        //  wxadapter.notifyDataSetChanged();


        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
             //   wxadapter.list.clear();
                 data=true;
                mPresenter.FishData(false);
                refreshLayout.finishRefresh(1000);
            }
        });
        //加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                data=false;
                mPresenter.FishData(false);
                refreshLayout.finishRefresh(1000);

            }
        });

        wxadapter.setOnClick(new Wxadapter.OnClick() {
            @Override
            public void show(List<Batree.DataBean> list, int id) {

                List<Batree.DataBean.ChildrenBean> children = list.get(id).getChildren();
                Intent intent = new Intent(getActivity(), Multiplexing.class);
                intent.putExtra("list", (Serializable) children);
                intent.putExtra("string", list.get(id).getName());
                startActivity(intent);
            }


        });

    }


    @Override
    protected WxPresenter<IView> createPresenter() {
        return new WxPresenter<>();
    }

}
