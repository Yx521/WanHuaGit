package com.example.lenovo.playandroid.activitys.yx;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.base.activity.SimpleActivity;
import com.example.lenovo.playandroid.global.Global;
import com.example.lenovo.playandroid.utils.StatusBarUtil;
import com.just.agentweb.AgentWeb;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends SimpleActivity {


    @BindView(R.id.article_detail_toolbar)
    Toolbar mArticleDetailToolbar;
    @BindView(R.id.article_detail_web_view)
    FrameLayout mArticleDetailWebView;
    @BindView(R.id.article_detail_group)
    LinearLayout mArticleDetailGroup;
    private AgentWeb mAgentWeb;
    private String mWeb;
    private String mDesc;

    @Override
    protected void initEventAndData() {
        Intent intent = getIntent();
        mWeb = intent.getStringExtra("web");
        mDesc = intent.getStringExtra("desc");
        mArticleDetailToolbar.setTitle(Html.fromHtml(mDesc));
        setSupportActionBar(mArticleDetailToolbar);
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, mArticleDetailToolbar);


        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mArticleDetailWebView, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setMainFrameErrorView(R.layout.webview_error_view, -1)
                .createAgentWeb()
                .ready()
                .go(mWeb);
        WebView mWebView = mAgentWeb.getWebCreator().getWebView();
        WebSettings mSettings = mWebView.getSettings();

        mSettings.setJavaScriptEnabled(true);
        mSettings.setSupportZoom(true);
        mSettings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        mSettings.setDisplayZoomControls(false);
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        mSettings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        mSettings.setLoadWithOverviewMode(true);
        //自适应屏幕
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);


    }

    @Override
    protected int creatrLayoutId() {
        return R.layout.activity_web_view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAfterTransition();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_article_common, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_share:
                //分享
                Intent intent1 = new Intent(Intent.ACTION_SEND);
                intent1.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_type_url, getString(R.string.app_name), mDesc, mWeb));
                intent1.setType("text/plain");
                startActivity(intent1);
                break;
            case R.id.item_system_browser:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mWeb)));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (Global.MENU_BUILDER.equalsIgnoreCase(menu.getClass().getSimpleName())) {
                try {
                    @SuppressLint("PrivateApi")
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }
}
