package com.example.lenovo.playandroid.fragments.bothurdle.items;


import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
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
    private BottomNavigationView mDesign;
    private FloatingActionButton mFab;
    private int mDistanceY;
    private boolean isBottomShow = true;
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

        mManager = new LinearLayoutManager(getContext());
        mProjectListRecyclerView.setLayoutManager(mManager);

        ArrayList<ProjectClassifyData.DataBean.DatasBean> datasBeans = new ArrayList<>();
        mClassifyAdapter = new ClassifyAdapter(getContext(),datasBeans);
        mProjectListRecyclerView.setAdapter(mClassifyAdapter);

    }

    @Override
    public void show(Object o) {
        ProjectClassifyData projectClassifyData = (ProjectClassifyData) o;
        List<ProjectClassifyData.DataBean.DatasBean> datas = projectClassifyData.getData().getDatas();
        mClassifyAdapter.addData(datas);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void upView(String str){
        if("4".equals(str)){
            Log.e("yxup", "upView: " );
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
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
