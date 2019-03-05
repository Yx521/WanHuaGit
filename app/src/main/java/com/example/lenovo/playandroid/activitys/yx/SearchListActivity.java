package com.example.lenovo.playandroid.activitys.yx;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.TextView;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.adapter.yx.SearchListAdapter;
import com.example.lenovo.playandroid.base.activity.SimpleActivity;
import com.example.lenovo.playandroid.beans.yx.SearchList;
import com.example.lenovo.playandroid.utils.KeyBoardUtils;
import com.example.lenovo.playandroid.utils.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchListActivity extends SimpleActivity {


    @BindView(R.id.common_toolbar_title_tv)
    TextView mCommonToolbarTitleTv;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.normal_view)
    RecyclerView mNormalView;
    @BindView(R.id.search_list_refresh_layout)
    SmartRefreshLayout mSearchListRefreshLayout;
    @BindView(R.id.search_list_floating_action_btn)
    FloatingActionButton mSearchListFloatingActionBtn;
    private Intent mIntent;

    @Override
    protected void initEventAndData() {
        StatusBarUtil.setStatusDarkColor(getWindow());
        mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        setToolbarView(R.color.title_black, R.drawable.ic_arrow_back_grey_24dp);
        mIntent = getIntent();
        String title = mIntent.getStringExtra("title");
        List<SearchList.DataBean.DatasBean> datas = (List<SearchList.DataBean.DatasBean>) mIntent.getSerializableExtra("data");
        if (!TextUtils.isEmpty(title)) {
            mCommonToolbarTitleTv.setText(title);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mNormalView.setLayoutManager(manager);

        SearchListAdapter searchListAdapter = new SearchListAdapter(this, datas);
        mNormalView.setAdapter(searchListAdapter);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        setResult(12,mIntent);
        return super.onKeyDown(keyCode, event);
    }

    private void setToolbarView(@ColorRes int textColor, @DrawableRes int navigationIcon) {
        mCommonToolbarTitleTv.setTextColor(ContextCompat.getColor(this, textColor));
        mToolbar.setNavigationIcon(ContextCompat.getDrawable(this, navigationIcon));
    }

    @Override
    protected int creatrLayoutId() {
        return R.layout.activity_search_list;
    }

}
