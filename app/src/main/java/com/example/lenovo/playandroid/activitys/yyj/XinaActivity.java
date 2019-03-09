package com.example.lenovo.playandroid.activitys.yyj;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.adapter.yyj.ZuiAdapter;
import com.example.lenovo.playandroid.adapter.yyj.ZuihouAdapter;
import com.example.lenovo.playandroid.base.activity.BaseActivity;
import com.example.lenovo.playandroid.base.fragment.BaseFragment;
import com.example.lenovo.playandroid.beans.yyj.Bean;
import com.example.lenovo.playandroid.fragments.yyj.ZuiFragment;
import com.example.lenovo.playandroid.presenter.yyj.Main_beanP;
import com.example.lenovo.playandroid.view.yx.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class XinaActivity extends BaseActivity<IView, Main_beanP<IView>> implements IView, XRecyclerView.LoadingListener {

    private Toolbar to;
    private ImageView fanhui;
    private TextView naa;
    private TextView text;
    private int page = 0;
    private String keywork;
    private ZuihouAdapter zuihouAdapter;
    private XRecyclerView xre;

    @Override
    protected Main_beanP<IView> createPresenter() {
        return new Main_beanP<>();
    }
 @Subscribe(threadMode = ThreadMode.MAIN, sticky = true) //在ui线程执行
     public void onData(String event) {
         this.keywork =event;
         Log.e("eve1",event);

     mPresenter.beanP( event, page + "");
     }


    @Override
    protected void initEventAndData() {
        EventBus.getDefault().register(this);
        String na = getIntent().getStringExtra("na");
        to = (Toolbar) findViewById(R.id.to);
        text = findViewById(R.id.mingzi);
        xre = findViewById(R.id.zuixre);
        // fanhui = (ImageView) findViewById(R.id.fanhui);
        naa = (TextView) findViewById(R.id.naa);
        to.setTitle("");
        setSupportActionBar(to);
        text.setText(na);
        to.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        xre.setLoadingListener(this);
    }

    @Override
    protected int creatrLayoutId() {
        return R.layout.activity_xina;
    }

    @Override
    public void show(Object o) {
        Bean bean = (Bean) o;
        List<Bean.DataBean.DatasBean> datas = bean.getData().getDatas();
        Log.e("sshu",datas.get(1).getChapterName());
        xre.setLayoutManager(new LinearLayoutManager(this));
        zuihouAdapter = new ZuihouAdapter(datas);
        xre.setAdapter(zuihouAdapter);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onRefresh() {
        page = 0;
        mPresenter.beanP( keywork, page + "");
        xre.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        page++;
        mPresenter.beanP( keywork, page + "");
        zuihouAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
