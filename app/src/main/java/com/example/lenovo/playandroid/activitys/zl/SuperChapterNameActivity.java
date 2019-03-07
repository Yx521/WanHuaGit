package com.example.lenovo.playandroid.activitys.zl;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.adapter.zl.ClassifyAdapter;
import com.example.lenovo.playandroid.base.activity.BaseActivity;
import com.example.lenovo.playandroid.beans.zl.FeedArticleListData;
import com.example.lenovo.playandroid.presenter.zl.ZlPresenter;
import com.example.lenovo.playandroid.view.zl.ZlView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 18 on 2019/3/4.
 */

public class SuperChapterNameActivity extends BaseActivity<ZlView, ZlPresenter<ZlView>> implements ZlView, View.OnClickListener {
    @BindView(R.id.web_title)
    TextView mWebTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.smartRefresh)
    SmartRefreshLayout mSmartRefresh;
    @BindView(R.id.fragment_group)
    FrameLayout mFragmentGroup;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    private int page = 0;
    private ClassifyAdapter mClassifyAdapter;
    private String mToo;
    private String mTab;
    private int mId;

    @Override
    public void BannerData(Object bannerdata) {

    }

    @Override
    public void MainData(Object maindata) {

        FeedArticleListData feedArticleListData = (FeedArticleListData) maindata;
        if (feedArticleListData.getData().getDatas().size() == 0) {
            Log.e("size", feedArticleListData.getData().getDatas().size() + "" + "美了");
            Toast.makeText(mActivity, R.string.load_more_no_data, Toast.LENGTH_SHORT).show();
        } else {
            Log.e("size", feedArticleListData.getData().getDatas() + "");
            mClassifyAdapter.addData(feedArticleListData.getData().getDatas());
        }


    }

    @Override
    public void Login(Object logindata) {
        //
    }

    @Override
    public void Register(Object registerdata) {
//
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected ZlPresenter<ZlView> createPresenter() {
        return new ZlPresenter<>();
    }

    @Override
    protected void initEventAndData() {


        mRecyclerview.setLayoutManager(new LinearLayoutManager(mActivity));
        Intent intent = getIntent();
        mTab = intent.getStringExtra("tab");
        mToo = intent.getStringExtra("too");
        mId = intent.getIntExtra("id", 0);

        mPresenter.upwithsingle(mId, page);
        mWebTitle.setText(mToo);
        TextView textView = new TextView(this);
        textView.setText(mTab);
        textView.setTextColor(Color.WHITE);
        mTablayout.addTab(mTablayout.newTab().setCustomView(textView));

        mToolbar.setNavigationOnClickListener(this);
        mClassifyAdapter = new ClassifyAdapter(new ArrayList<FeedArticleListData.DataBean.DatasBean>(), mToo, mTab);
        mRecyclerview.setAdapter(mClassifyAdapter);


        mClassifyAdapter.setOnClickListener(new ClassifyAdapter.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void OnClick(View view, int position) {
                ActivityOptions mA = ActivityOptions.makeSceneTransitionAnimation(mActivity, view, getString(R.string.about_us));

                Intent intent = new Intent();
                intent.setClass(mActivity, WebActivity.class);
                intent.putExtra("isShowlike", true);
                intent.putExtra("weburl", mClassifyAdapter.mData.get(position).getLink());
                intent.putExtra("webtitle", mClassifyAdapter.mData.get(position).getTitle());
                mActivity.startActivity(intent, mA.toBundle());
            }
        });


        Refreshandload();
    }

    private void Refreshandload() {
        mSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                mPresenter.upwithsingle(mId, page);
                mSmartRefresh.finishRefresh(200);
            }
        });
        mSmartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.upwithsingle(mId, page);
                mSmartRefresh.finishLoadMore(200);
            }
        });
    }

    @Override
    protected int creatrLayoutId() {
        return R.layout.activity_superchaptername;
    }


    @Override
    public void onClick(View v) {
        finish();
    }



}
