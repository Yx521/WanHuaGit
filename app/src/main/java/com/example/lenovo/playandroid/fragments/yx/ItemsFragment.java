package com.example.lenovo.playandroid.fragments.yx;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.adapter.yx.ItemsAdapter;
import com.example.lenovo.playandroid.base.fragment.BaseFragment;
import com.example.lenovo.playandroid.beans.yx.ProjectClassify;
import com.example.lenovo.playandroid.presenter.yx.Presenter;
import com.example.lenovo.playandroid.presenter.yx.YxPresenter;
import com.example.lenovo.playandroid.view.yx.IView;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemsFragment extends BaseFragment<IView, Presenter<IView>> implements IView {


    @BindView(R.id.project_tab_layout)
    SlidingTabLayout mProjectTabLayout;
    @BindView(R.id.project_divider)
    View mProjectDivider;
    @BindView(R.id.project_viewpager)
    ViewPager mProjectViewpager;
    @BindView(R.id.normal_view)
    LinearLayout mNormalView;

    private View view;

    public ItemsFragment() {
        // Required empty public constructor
    }

    @Override
    protected int creatrLayoutId() {
        return R.layout.fragment_items;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("liangxq","onCreate");
    }

    @Override
    protected void initData() {
        mPresenter.getDataP("");
    }

    @Override
    public void show(Object o) {
        ProjectClassify projectClassify = (ProjectClassify) o;
        List<ProjectClassify.DataBean> data = projectClassify.getData();
        Log.e("yx", "show: " + data.get(0).getName());
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            ClassifyFragment frag = ClassifyFragment.getFrag(data.get(i).getId(), data.get(i).getName());
            fragments.add(frag);
        }
        Log.e("yx", "show: " + fragments.size());
        if(ItemsFragment.this.isAdded()&&data!=null&&fragments!=null){
             ItemsAdapter itemsAdapter = new ItemsAdapter(getChildFragmentManager(), data, fragments);
            if(mProjectViewpager!=null&&mProjectTabLayout!=null&&itemsAdapter!=null){
                mProjectViewpager.setAdapter(itemsAdapter);
                mProjectTabLayout.setViewPager(mProjectViewpager);
            }
        }
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected Presenter<IView> createPresenter() {
        return new Presenter<>();
    }


    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("liangxq","onDetach");
        mProjectViewpager = null;
        mProjectTabLayout = null;
    }

}
