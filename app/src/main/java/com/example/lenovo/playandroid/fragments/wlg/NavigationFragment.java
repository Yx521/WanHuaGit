package com.example.lenovo.playandroid.fragments.wlg;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.adapter.wlg.NaviAdapter;
import com.example.lenovo.playandroid.base.fragment.BaseFragment;
import com.example.lenovo.playandroid.beans.wlg.NaviBean;
import com.example.lenovo.playandroid.presenter.wlg.WlgNaviPresenter;
import com.example.lenovo.playandroid.view.wlg.WlgNaviView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends BaseFragment<WlgNaviView, WlgNaviPresenter<WlgNaviView>> implements WlgNaviView {


    @BindView(R.id.tab_navi)
    VerticalTabLayout tabNavi;
    @BindView(R.id.navigation_divider)
    View navigationDivider;
    @BindView(R.id.xlv_navi)
    RecyclerView xlvNavi;
    Unbinder unbinder;
    @BindView(R.id.li)
    LinearLayout li;
    Unbinder unbinder1;
    private LinearLayoutManager mManager;
    private NaviAdapter mNaviAdapter;
    private boolean needScroll;
    private boolean isClickTab;
    private int mIndex;
    private List<NaviBean.DataBean> mData;

    public NavigationFragment() {
        // Required empty public constructor
    }


    @Override
    protected int creatrLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initData() {
        mManager = new LinearLayoutManager(getActivity());
        ArrayList<NaviBean.DataBean> dataBeans = new ArrayList<>();
        mNaviAdapter = new NaviAdapter(R.layout.item_list, dataBeans);
        xlvNavi.setLayoutManager(mManager);
        xlvNavi.setHasFixedSize(true);
        xlvNavi.setAdapter(mNaviAdapter);
        mPresenter.setNavi();

    }

    @Override
    protected WlgNaviPresenter<WlgNaviView> createPresenter() {
        return new WlgNaviPresenter<>();
    }

    @Override
    public void shouNaviBean(final NaviBean naviBean) {
        Log.i("wlg导航", "shouNaviBean: " + naviBean);
        mData = naviBean.getData();


        tabNavi.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return mData == null ? 0 : mData.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int i) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int i) {
                return null;
            }


            @Override
            public ITabView.TabTitle getTitle(int i) {
                return new TabView.TabTitle.Builder()
                        .setContent(mData.get(i).getName())
                        .setTextColor(ContextCompat.getColor(getActivity(), R.color.tab_bac),
                                ContextCompat.getColor(getActivity(), R.color.grey))
                        .build();
            }

            @Override
            public int getBackground(int i) {
                return -1;
            }
        });
        setChildViewVisibility(View.VISIBLE);

        mNaviAdapter.replaceData(mData);
        mNaviAdapter.openLoadAnimation(5);
        leftRightLinkage();

    }
    private void leftRightLinkage() {
        xlvNavi.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (needScroll && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                    scrollRecyclerView();
                }
                rightLinkageLeft(newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (needScroll) {
                    scrollRecyclerView();
                }
            }
        });

        tabNavi.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tabView, int i) {
                isClickTab = true;
                selectTag(i);
            }

            @Override
            public void onTabReselected(TabView tabView, int i) {
            }
        });
    }
    private void scrollRecyclerView() {
        needScroll = false;
        int indexDistance = mIndex - mManager.findFirstVisibleItemPosition();
        if (indexDistance >= 0 && indexDistance < xlvNavi.getChildCount()) {
            int top = xlvNavi.getChildAt(indexDistance).getTop();
            xlvNavi.smoothScrollBy(0, top);
        }
    }
    private void setChildViewVisibility(int visibility) {
        li.setVisibility(visibility);
        xlvNavi.setVisibility(visibility);
        navigationDivider .setVisibility(visibility);
    }
    private void rightLinkageLeft(int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (isClickTab) {
                isClickTab = false;
                return;
            }
            int firstPosition = mManager.findFirstVisibleItemPosition();
            if (mIndex != firstPosition) {
                mIndex = firstPosition;
                setChecked(mIndex);
            }
        }
    }

    private void selectTag(int i) {
        mIndex = i;
        xlvNavi.stopScroll();
        smoothScrollToPosition(i);
    }
    private void setChecked(int position) {
        if (isClickTab) {
            isClickTab = false;
        } else {
            if (tabNavi == null) {
                return;
            }
            tabNavi.setTabSelected(mIndex);

        }
        mIndex = position;


    }

    private void smoothScrollToPosition(int currentPosition) {
        int firstPosition = mManager.findFirstVisibleItemPosition();
        int lastPosition = mManager.findLastVisibleItemPosition();
        if (currentPosition <= firstPosition) {
            xlvNavi.smoothScrollToPosition(currentPosition);
        } else if (currentPosition <= lastPosition) {
            int top = xlvNavi.getChildAt(currentPosition - firstPosition).getTop();
            xlvNavi.smoothScrollBy(0, top);
        } else {
            xlvNavi.smoothScrollToPosition(currentPosition);
            needScroll = true;
        }
    }
    public void jumpToTheTop() {
        if (tabNavi != null) {
            tabNavi.setTabSelected(0);
        }
    }

    @Override
    public void showError(String error) {

    }



}
