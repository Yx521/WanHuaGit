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
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.base.activity.BaseActivity;
import com.example.lenovo.playandroid.presenter.zl.ZlPresenter;
import com.example.lenovo.playandroid.utils.ShareUtil;
import com.example.lenovo.playandroid.view.zl.ZlView;
import com.just.agentweb.AgentWeb;


import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 18 on 2019/3/1.
 */

public class WebActivity extends BaseActivity<ZlView, ZlPresenter<ZlView>> implements ZlView {
    @BindView(R.id.web_title)
    TextView mWebTitle;
    @BindView(R.id.webToolbar)
    Toolbar mWebToolbar;
    @BindView(R.id.webcontent)
    FrameLayout mWebcontent;
    private AgentWeb mAgentWeb;
    private String mWeburl;
    private String mWebtitle;
    private boolean isShowLike;

    @Override
    public void BannerData(Object bannerdata) {
        //
    }

    @Override
    public void MainData(Object maindata) {
        //
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
        initToo();
        mWebTitle.setLines(1);
        mWebTitle.setEllipsize(TextUtils.TruncateAt.END);
        Intent intent = getIntent();
        isShowLike = intent.getBooleanExtra("isShowlike", isShowLike);
        mWeburl = intent.getStringExtra("weburl");
        mWebtitle = intent.getStringExtra("webtitle");
        mWebTitle.setText(mWebtitle);
        // initWeb(weburl, webtitle);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mWebcontent, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(mWeburl);


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
        if (isShowLike == false) {
            getMenuInflater().inflate(R.menu.main_menu, menu);
        } else if (isShowLike == true) {
            getMenuInflater().inflate(R.menu.main_menu, menu);
            MenuItem item = menu.findItem(R.id.main_web_isslike);
            item.setVisible(true);
        }

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
            case R.id.main_web_isslike:

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
}
