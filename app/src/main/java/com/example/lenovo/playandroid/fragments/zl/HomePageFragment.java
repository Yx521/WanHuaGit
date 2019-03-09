package com.example.lenovo.playandroid.fragments.zl;


import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.activitys.zl.SuperChapterNameActivity;
import com.example.lenovo.playandroid.activitys.zl.WebActivity;
import com.example.lenovo.playandroid.adapter.zl.ZlRecycAdapter;
import com.example.lenovo.playandroid.base.fragment.BaseFragment;
import com.example.lenovo.playandroid.beans.zl.BannerBean;
import com.example.lenovo.playandroid.beans.zl.FeedArticleListData;
import com.example.lenovo.playandroid.presenter.zl.ZlPresenter;
import com.example.lenovo.playandroid.view.zl.ZlView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends BaseFragment<ZlView, ZlPresenter<ZlView>> implements ZlView {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    Unbinder unbinder1;
    private String TAG = "HomePageFragment";
    private int page = 0;
    private ZlRecycAdapter mZlRecycAdapter;


    @Override
    public void showError(String error) {
        Log.e(TAG, "showError: " + error);


    }

    @Override
    protected int creatrLayoutId() {
        return R.layout.fragment_about_us;
    }
    @Subscribe
    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        mPresenter.upwithBanner("");
        mPresenter.upwithItem(page);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());
        mZlRecycAdapter = new ZlRecycAdapter(new ArrayList<BannerBean.DataBean>(), new ArrayList<FeedArticleListData.DataBean.DatasBean>());
        mRecyclerview.setAdapter(mZlRecycAdapter);

        initRefreshAndLoadmore();
        //轮播图的点击事件
        mZlRecycAdapter.setOnBannerListener(new ZlRecycAdapter.OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("isShowlike", false);
                intent.putExtra("weburl", mZlRecycAdapter.mBannerData.get(position).getUrl());
                intent.putExtra("webtitle", mZlRecycAdapter.mBannerData.get(position).getTitle());
                startActivity(intent);

                Toast.makeText(mContext, "点击" + position, Toast.LENGTH_SHORT).show();
            }
        });
        //item点击事件
        mZlRecycAdapter.setOnClickListener(new ZlRecycAdapter.OnClickListener() {
            private ActivityOptions mA;

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void OnClick(View view, int position) {
                ActivityOptions mA = ActivityOptions.makeSceneTransitionAnimation(mActivity, view, getString(R.string.about_us));

                Intent intent = new Intent();
                intent.setClass(mContext, WebActivity.class);
                intent.putExtra("isShowlike", true);
                intent.putExtra("weburl", mZlRecycAdapter.mData.get(position).getLink());
                intent.putExtra("webtitle", mZlRecycAdapter.mData.get(position).getTitle());
                mActivity.startActivity(intent, mA.toBundle());
            }
        });

        mZlRecycAdapter.setOnTooTextAndTabText(new ZlRecycAdapter.onTooTextAndTabText() {
            @Override
            public void TooTextAndTabText(String tab, String Tootext, int chapterId) {
                Log.e("tab", tab);
                Log.e("too", Tootext);

                Intent intent = new Intent();
                intent.setClass(mContext, SuperChapterNameActivity.class);
                intent.putExtra("tab", tab);
                intent.putExtra("too", Tootext);
                intent.putExtra("id", chapterId);
                startActivity(intent);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void zhiding(String positon) {
        if ("0".equals(positon)) {
            mRecyclerview.smoothScrollToPosition(0);
        }
    }

    //刷新或加载
    private void initRefreshAndLoadmore() {

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                mPresenter.upwithItem(page);


            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.upwithItem(page);

                //  mRefreshLayout.finishLoadMore(3000);
            }
        });
    }

    @Override
    protected ZlPresenter<ZlView> createPresenter() {
        return new ZlPresenter<>();
    }


    @Override
    public void BannerData(Object bannerdata) {
        BannerBean bannerBean = (BannerBean) bannerdata;
        mZlRecycAdapter.addBanner(bannerBean.getData());
    }

    @Override
    public void MainData(Object maindata) {
        //关闭刷新或加载视图
        if(mRefreshLayout!=null&&mZlRecycAdapter!=null){
            mRefreshLayout.finishLoadMore(100);
            mRefreshLayout.finishRefresh(100);
            FeedArticleListData feedArticleListData = (FeedArticleListData) maindata;
            mZlRecycAdapter.addData(feedArticleListData.getData().getDatas());
        }
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }


    @Override
    public void Login(Object logindata) {
        //
    }

    @Override
    public void Register(Object registerdata) {
//
    }


}
