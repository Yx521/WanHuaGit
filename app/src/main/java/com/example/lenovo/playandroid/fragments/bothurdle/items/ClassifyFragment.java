package com.example.lenovo.playandroid.fragments.bothurdle.items;


import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.activitys.MainActivity;
import com.example.lenovo.playandroid.adapter.ClassifyAdapter;
import com.example.lenovo.playandroid.base.fragment.BaseFragment;
import com.example.lenovo.playandroid.bean.yxbean.ProjectClassifyData;
import com.example.lenovo.playandroid.presenter.Presenter;
import com.example.lenovo.playandroid.presenter.yx.YxPresenter;
import com.example.lenovo.playandroid.view.IView;
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
public class ClassifyFragment extends BaseFragment<IView, YxPresenter<IView>> implements IView {


    @BindView(R.id.project_list_recycler_view)
    RecyclerView mProjectListRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout mNormalView;
    Unbinder unbinder;
    private int page = 0;
    private ClassifyAdapter mClassifyAdapter;
    private BottomNavigationView mDesign;
    private FloatingActionButton mFab;
    private int mDistanceY;
    private boolean isBottomShow = true;

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
        getActivity().findViewById(R.id.project_list_recycler_view);
        MainActivity activity = (MainActivity) getActivity();
        mDesign = activity.findViewById(R.id.design_bottom_sheet);
        mFab = activity.findViewById(R.id.fab);

        Bundle bundle = getArguments();
        int id = bundle.getInt("id");

        Map<String,Object> map = new HashMap<>();
        map.put("cid",id);
        map.put("page",page);

        mPresenter.getDataP(map);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mProjectListRecyclerView.setLayoutManager(manager);

        ArrayList<ProjectClassifyData.DataBean.DatasBean> datasBeans = new ArrayList<>();
        mClassifyAdapter = new ClassifyAdapter(getContext(),datasBeans);
        mProjectListRecyclerView.setAdapter(mClassifyAdapter);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "点击", Toast.LENGTH_SHORT).show();
            }
        });
        mProjectListRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy<0){
                    mDesign.setVisibility(View.VISIBLE);
                    mFab.setVisibility(View.VISIBLE);
                }else if(dy>0){
                    mDesign.setVisibility(View.INVISIBLE);
                    mFab.setVisibility(View.INVISIBLE);

                }
            }
        });

    }

    @Override
    public void show(Object o) {
        ProjectClassifyData projectClassifyData = (ProjectClassifyData) o;
        List<ProjectClassifyData.DataBean.DatasBean> datas = projectClassifyData.getData().getDatas();
        mClassifyAdapter.addData(datas);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected YxPresenter<IView> createPresenter() {
        return new YxPresenter<>();
    }

}
