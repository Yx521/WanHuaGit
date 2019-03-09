package com.example.lenovo.playandroid.activitys.zl;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.adapter.zl.ZlRecycAdapter;
import com.example.lenovo.playandroid.base.activity.BaseActivity;
import com.example.lenovo.playandroid.beans.wx.Data;
import com.example.lenovo.playandroid.beans.wx.HttpResult;
import com.example.lenovo.playandroid.beans.yx.Collect;
import com.example.lenovo.playandroid.dao.IsLikeDaoBean;
import com.example.lenovo.playandroid.dao.IsLikeManager;
import com.example.lenovo.playandroid.dao.LogDaoBean;
import com.example.lenovo.playandroid.dao.LoginManager;
import com.example.lenovo.playandroid.fragments.zl.HomePageFragment;
import com.example.lenovo.playandroid.presenter.zl.ZlPresenter;
import com.example.lenovo.playandroid.utils.ShareUtil;
import com.example.lenovo.playandroid.view.zl.ZlView;
import com.just.agentweb.AgentWeb;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 18 on 2019/3/1.
 */

public class WebActivity extends BaseActivity<ZlView, ZlPresenter<ZlView>> implements ZlView, View.OnClickListener {
    @BindView(R.id.web_title)
    TextView mWebTitle;
    @BindView(R.id.webToolbar)
    Toolbar mWebToolbar;
    @BindView(R.id.webcontent)
    FrameLayout mWebcontent;
    @BindView(R.id.islike)
    ImageView mIslike;
    private AgentWeb mAgentWeb;
    private String mWeburl;
    private String mWebtitle;
    private boolean isShowLike;
    private String mAuthor;
    private boolean nelsep;
    private boolean mIsike;
    private int mId;
    private List<IsLikeDaoBean> mSelect;
    private int mPosition;
    private Intent mIntent;
    private int mOriginId;
    private int mId1;

    @Override
    public void BannerData(Object bannerdata) {
        //
        HttpResult collect = (HttpResult) bannerdata;
        //  Log.e("data", collect.getErrorCode() + "" + "____-");
        if (collect.getErrorCode() == 0) {
            Toast.makeText(mActivity, "取消收藏", Toast.LENGTH_SHORT).show();
            mSelect = IsLikeManager.mMySqlHelper().select();
            for (int i = 0; i < mSelect.size(); i++) {
                IsLikeDaoBean isLikeDaoBean = mSelect.get(i);
                if (isLikeDaoBean.getUrl().equals(mWeburl)) {
                    isLikeDaoBean.setUrl(mWeburl);
                    IsLikeManager.mMySqlHelper().delete(isLikeDaoBean);
                }

            }
        }


    }

    @Override
    public void MainData(Object maindata) {
        //
        Collect collect = (Collect) maindata;
        if (collect.getErrorCode() == 0) {

            Toast.makeText(mActivity, "收藏成功", Toast.LENGTH_SHORT).show();
            IsLikeManager.mMySqlHelper().insert(new IsLikeDaoBean(null, mWeburl));
            mIslike.setImageResource(R.mipmap.ic_toolbar_like_p);
        } else {
            Toast.makeText(mActivity, "收藏失败", Toast.LENGTH_SHORT).show();
            mIslike.setImageResource(R.mipmap.ic_toolbar_like_n);


        }
    }

    @Override
    public void Login(Object logindata) {
        //
        Data data = (Data) logindata;
        List<Data.DataBean.DatasBean> datas = data.getData().getDatas();
        for (int i = 0; i < datas.size(); i++) {
            String link = datas.get(i).getLink();
            if (mWeburl.equals(link)){
                mOriginId = data.getData().getDatas().get(i).getOriginId();
                mId1 = data.getData().getDatas().get(i).getId();
            }
        }

        mPresenter.unCollect(mId1, mOriginId);
        mIslike.setImageResource(R.mipmap.ic_toolbar_like_n);
        nelsep = false;
    }

    @Override
    public void Register(Object registerdata) {
//
    }

    @Override
    public void setData(Object obj) {

    }

    @Override
    public void setS(Data value) {

    }

    @Override
    public void shan(HttpResult value) {

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
        initToo();
        mWebTitle.setLines(1);
        mWebTitle.setEllipsize(TextUtils.TruncateAt.END);
        mIntent = getIntent();
        isShowLike = mIntent.getBooleanExtra("isShowlike", isShowLike);
        mWeburl = mIntent.getStringExtra("weburl");
        mWebtitle = mIntent.getStringExtra("webtitle");
        mAuthor = mIntent.getStringExtra("author");
        mPosition = mIntent.getIntExtra("position", 0);
        mId = mIntent.getIntExtra("id", 0);
        Log.e("dada",mId+"");
        mSelect = IsLikeManager.mMySqlHelper().select();
        if (mSelect.size() > 0) {
            for (int i = 0; i < mSelect.size(); i++) {
                String url = mSelect.get(i).getUrl();
                if (mWeburl.equals(url)) {
                    mIslike.setImageResource(R.mipmap.ic_toolbar_like_p);
                    nelsep = true;
                }
            }
        }

        mWebTitle.setText(mWebtitle);

        if (isShowLike == false) {
            mIslike.setVisibility(View.GONE);
        } else if (isShowLike == true) {
            mIslike.setVisibility(View.VISIBLE);
        }


        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mWebcontent, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(mWeburl);

        mIslike.setOnClickListener(this);
    }

    private void initToo() {
        setSupportActionBar(mWebToolbar);
        mWebToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        WebView webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_share:
                //ShareUtil.sendEmail(mActivity,mWebtitle+mWeburl);
                ShareUtil.shareText(mActivity, "WanAndroid分享【" + mWebTitle.getText().toString() + "】专题:  " + mWeburl, null);
                break;
            case R.id.main_browser:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mWeburl)));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    //menu图标显示
    @SuppressLint("RestrictedApi")
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }


    @Override
    protected int creatrLayoutId() {
        return R.layout.zlweb_activity;
    }

    //在Web跳转到另一个页面返回时不退出本Activity
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mAgentWeb.handleKeyEvent(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finishAfterTransition();
    }


    @Override
    public void onClick(View v) {
        List<LogDaoBean> select = LoginManager.mMySqlHelper().select();

        if (select.get(0).getIsLogin() == true) {
            if (nelsep == false) {
                //  mIslike.setImageResource(R.mipmap.ic_toolbar_like_p);
                Map<String, Object> map = new HashMap<>();
                map.put("t", mWebtitle);
                map.put("a", mAuthor);
                map.put("l", mWeburl);
                mPresenter.FishData(map);
                nelsep = true;
            } else if (nelsep == true) {
                mPresenter.shouc();


            }
        } else {
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);
        }

    }
}
