package com.example.lenovo.playandroid.activitys.wlg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.global.Constants;
import com.just.agentweb.AgentWeb;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleDetailActivity extends AppCompatActivity {

    private static final String TAG = "ArticleDetailActivity";
    @BindView(R.id.article_detail_toolbar)
    Toolbar articleDetailToolbar;
    @BindView(R.id.article_detail_web_view)
    FrameLayout articleDetailWebView;
    private String mTitle1;
    private String mArticleLink;
    private AgentWeb mAgentWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        mTitle1 = (String) bundle.get(Constants.ARTICLE_TITLE);
        mArticleLink = (String) bundle.get(Constants.ARTICLE_LINK);
        int articleId = ((int) bundle.get(Constants.ARTICLE_ID));
        boolean isCommonSite = ((boolean) bundle.get(Constants.IS_COMMON_SITE));
        boolean isCollect = ((boolean) bundle.get(Constants.IS_COLLECT));
        boolean isCollectPage = ((boolean) bundle.get(Constants.IS_COLLECT_PAGE));
        Log.e(TAG, "onCreate: " + mArticleLink);
        articleDetailToolbar.setTitle(mTitle1);

        setSupportActionBar(articleDetailToolbar);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        articleDetailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
            }
        });
        initEventAndData();

    }
    protected void initEventAndData() {
        mAgentWeb = AgentWeb.with(ArticleDetailActivity.this)//这里需要把 Activity 、 和 Fragment  同时传入
                .setAgentWebParent(articleDetailWebView, new LinearLayout.LayoutParams(-1, -1))// 设置 AgentWeb 的父控件 ， 这里的view 是 LinearLayout ， 那么需要传入 LinearLayout.LayoutParams
                .useDefaultIndicator(Color.parseColor("#D41B1B"), 3)
                .setMainFrameErrorView(R.layout.webview_error_view,-1)
                .createAgentWeb()
                .ready()
                .go(mArticleLink);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_acticle, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_share:
             shareText(this,"",mTitle1);
                break;
            case R.id.item_collect:
//                item.setTitle(mTitle1);
                item.setIcon(R.mipmap.ic_toolbar_like_n);
                break;
            case R.id.item_system_browser:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mArticleLink)));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public static void shareText(Context context, String text, String title){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,text);
        context.startActivity(Intent.createChooser(intent,title));
    }

    /**
     * 让菜单同时显示图标和文字
     *
     * @param featureId Feature id
     * @param menu Menu
     * @return menu if opened
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (Constants.MENU_BUILDER.equalsIgnoreCase(menu.getClass().getSimpleName())) {
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
