package com.example.lenovo.playandroid.fragments.yx;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.activitys.yx.WebViewActivity;
import com.example.lenovo.playandroid.adapter.yx.ClassifyAdapter;
import com.example.lenovo.playandroid.base.fragment.BaseFragment;
import com.example.lenovo.playandroid.beans.yx.ProjectClassifyData;
import com.example.lenovo.playandroid.presenter.yx.YxPresenter;
import com.example.lenovo.playandroid.view.yx.IView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
public class ClassifyFragment extends BaseFragment<IView, YxPresenter<IView>> implements IView {

    @BindView(R.id.project_list_recycler_view)
    RecyclerView mProjectListRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout mNormalView;
    Unbinder unbinder;

    private int page = 0;
    private ClassifyAdapter mClassifyAdapter;
    private LinearLayoutManager mManager;

    public static ClassifyFragment getFrag(int id, String name) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("name", name);
        ClassifyFragment classifyFragment = new ClassifyFragment();
        classifyFragment.setArguments(bundle);
        return classifyFragment;
    }


    @Override
    protected int creatrLayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        Map<String, Object> map = new HashMap<>();
        map.put("cid", id);
        map.put("page", page);
        mPresenter.getDataP(map);
        mManager = new LinearLayoutManager(getContext());
        mProjectListRecyclerView.setLayoutManager(mManager);

        ArrayList<ProjectClassifyData.DataBean.DatasBean> datasBeans = new ArrayList<>();
        mClassifyAdapter = new ClassifyAdapter(getContext(), datasBeans);
        mProjectListRecyclerView.setAdapter(mClassifyAdapter);

        mClassifyAdapter.setOnItemClickListener(new ClassifyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ArrayList<ProjectClassifyData.DataBean.DatasBean> mData) {
                String projectLink = mData.get(position).getProjectLink();
                String desc = mData.get(position).getDesc();
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("web", projectLink);
                intent.putExtra("desc",desc);
                // 这里指定了共享的视图元素
                /* ActivityOptionsCompat options = ActivityOptionsCompat .makeSceneTransitionAnimation(getActivity(), view, "myClass");
                 ActivityCompat.startActivity(getActivity(), intent, options.toBundle());*/
                startActivity(intent);
            }
        });
    }

    @Override
    public void show(Object o) {
        ProjectClassifyData projectClassifyData = (ProjectClassifyData) o;
        List<ProjectClassifyData.DataBean.DatasBean> datas = projectClassifyData.getData().getDatas();
        mClassifyAdapter.addData(datas);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void upView(String str) {
        if ("4".equals(str)) {
            Log.e("yxup", "upView: ");
            mProjectListRecyclerView.smoothScrollToPosition(0);
        }
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected YxPresenter<IView> createPresenter() {
        return new YxPresenter<>();
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
