package com.example.lenovo.playandroid.activitys.wlg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.base.activity.BaseActivity;
import com.example.lenovo.playandroid.beans.wlg.NaviBean;
import com.example.lenovo.playandroid.beans.wx.Data;
import com.example.lenovo.playandroid.beans.wx.HttpResult;
import com.example.lenovo.playandroid.beans.yx.Collect;
import com.example.lenovo.playandroid.dao.CanData;
import com.example.lenovo.playandroid.dao.DataBaseMannger;
import com.example.lenovo.playandroid.dao.HistoryData;
import com.example.lenovo.playandroid.global.Constants;
import com.example.lenovo.playandroid.presenter.wlg.WlgNaviPresenter;
import com.example.lenovo.playandroid.presenter.wx.PresenterXin;
import com.example.lenovo.playandroid.view.wlg.WlgNaviView;
import com.example.lenovo.playandroid.view.yx.IView;
import com.just.agentweb.AgentWeb;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ArticleDetailActivity extends BaseActivity<IView, PresenterXin<IView>> implements IView {

    private static final String TAG = "ArticleDetailActivity";
    @BindView(R.id.article_detail_toolbar)
    Toolbar articleDetailToolbar;
    @BindView(R.id.article_detail_web_view)
    FrameLayout articleDetailWebView;
    @BindView(R.id.collect)
    ImageView collect;
    @BindView(R.id.uncollect)
    ImageView uncollect;
    private String mTitle1;
    private String mArticleLink;
    private AgentWeb mAgentWeb;
    private int mArticleId;
    private String mAhthor;
    private int id1;
    private int originId;
    private String itle;
    private Long mId;
    private List<CanData> mCanData;


    @Override
    protected void initEventAndData() {

        Intent intent = getIntent();
        assert intent != null;
        mTitle1 = intent.getStringExtra(Constants.ARTICLE_TITLE);
        mArticleId = intent.getIntExtra(Constants.ARTICLE_ID, 0);
        mArticleLink = intent.getStringExtra(Constants.ARTICLE_LINK);
        mAhthor = intent.getStringExtra(Constants.IS_COLLECT);

        mCanData = DataBaseMannger.getIntrance().selectCan();
        if (mCanData != null){
            for (int i = 0; i < mCanData.size(); i++) {
                if (mCanData.get(i).getTitle().equals(mTitle1) && mCanData.get(i).getAuthor().equals(mAhthor) && mCanData.get(i).getLink().equals(mArticleLink)) {
                    collect.setVisibility(View.GONE);
                    uncollect.setVisibility(View.VISIBLE);
                }

            }
        }



        Log.e(TAG, "initEventAndData: " + mAhthor);
//        SharedPreferences mColle = getSharedPreferences("collect", MODE_PRIVATE);
//        SharedPreferences.Editor edit = mColle.edit();
//        boolean selected1 = collect.isSelected();
//        edit.putBoolean("isselected", selected1);
//        edit.commit();
//        boolean selected = mColle.getBoolean("selected", false);
//        String link = mColle.getString("link", "");
//        if (link.equals(mArticleLink)) {
//            if (selected) {
//                collect.setSelected(false);
//            } else {
//                collect.setSelected(true);
//            }
//        } else {
//            boolean isselected = mColle.getBoolean("isselected", false);
//
//
//            collect.setSelected(isselected);
//        }
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
        mAgentWeb = AgentWeb.with(ArticleDetailActivity.this)//这里需要把 Activity 、 和 Fragment  同时传入
                .setAgentWebParent(articleDetailWebView, new LinearLayout.LayoutParams(-1, -1))// 设置 AgentWeb 的父控件 ， 这里的view 是 LinearLayout ， 那么需要传入 LinearLayout.LayoutParams
                .useDefaultIndicator(Color.parseColor("#D41B1B"), 3)
                .setMainFrameErrorView(R.layout.webview_error_view, -1)
                .createAgentWeb()
                .ready()
                .go(mArticleLink);
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
        return R.layout.activity_article_detail;


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
                shareText(this, "", mTitle1);
                break;
            case R.id.item_system_browser:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mArticleLink)));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void shareText(Context context, String text, String title) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(intent, title));
    }

    /**
     * 让菜单同时显示图标和文字
     *
     * @param featureId Feature id
     * @param menu      Menu
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
    protected PresenterXin<IView> createPresenter() {
        return new PresenterXin<>();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

//    @Override
//    public void shouNaviBean(NaviBean naviBean) {
//
//    }

//    @Override
//    public void shouCollect(Collect collect) {
//        Log.e(TAG, "shouCollect: " + collect.getErrorCode());
//        if (collect.getErrorCode() == 0) {
//            Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
//        }
//
//    }
//
//    @Override
//    public void shouunCollect(Collect collect) {
//
//        Log.e(TAG, "shouunCollect: " + collect.getErrorCode());
//        if (collect.getErrorCode() == 0) {
//            Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    public void showError(String error) {

    }


//    @OnClick(R.id.collect)
//    public void onViewClicked() {
//        boolean selected = collect.isSelected();
//        SharedPreferences mColle = getSharedPreferences("collect", MODE_PRIVATE);
//        SharedPreferences.Editor edit = mColle.edit();
//
//        if (selected) {
//            collect.setSelected(false);
//            mPresenter.setunCollect(mArticleId);
//            edit.putBoolean("selected", selected);
//            edit.putString("link", mArticleLink);
//            edit.commit();
//
//
//        } else {
//            this.collect.setSelected(true);
//            mPresenter.setCollect(mArticleId);
//            edit.putBoolean("selected", selected);
//            edit.putString("link", mArticleLink);
//            edit.commit();
//        }
//    }


    @OnClick({R.id.collect, R.id.uncollect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.collect:

                collect.setVisibility(View.GONE);
                uncollect.setVisibility(View.VISIBLE);
                Map<String, Object> hashMap = new HashMap<>();
                hashMap.put("t", mTitle1);
                hashMap.put("a", mAhthor);
                hashMap.put("id", mArticleId);
                hashMap.put("l", mArticleLink);
                hashMap.put("biao", "shou");
                mPresenter.FishData(hashMap);
                DataBaseMannger.getIntrance().insertCan(new CanData(null, mTitle1, mAhthor, mArticleLink, true));

                break;
            case R.id.uncollect:
                collect.setVisibility(View.VISIBLE);
                uncollect.setVisibility(View.GONE);
                Map<String, Object> map1 = new HashMap<>();
                map1.put("biao", "shou1");
                mPresenter.FishData(map1);
                List<CanData> canData = DataBaseMannger.getIntrance().selectCan();
                for (int i = 0; i < canData.size(); i++) {
                    if (canData.get(i).getTitle().equals(mTitle1) && canData.get(i).getAuthor().equals(mAhthor)
                            && canData.get(i).getLink().equals(mArticleLink)) {
                        mId = canData.get(i).getId();

                    }
                }
                break;


        }
    }

    @Override
    public void show(Object o) {
        Map<String, Object> map = (Map<String, Object>) o;
        String biao = (String) map.get("biao");
        if ("shou".equals(biao)) {
            Collect collect = (Collect) map.get("va");
            int errorCode = collect.getErrorCode();
            Log.i("yangxu", "show: " + errorCode);

            if (errorCode == 0) {
                Toast.makeText(mActivity, "收藏成功", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(mActivity, "收藏失败", Toast.LENGTH_SHORT).show();
            }
        } else if ("shan".equals(biao)) {
            HttpResult collect = (HttpResult) map.get("va");
            int errorCode = collect.getErrorCode();
            if (errorCode == 0) {
                DataBaseMannger.getIntrance().deleteCan(new CanData(mId, null, null, null, null));
                Toast.makeText(mActivity, "删除成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mActivity, "删除失败", Toast.LENGTH_SHORT).show();
            }
        } else if ("shou1".equals(biao)) {
            Data data = (Data) map.get("va");
            if (data.getData().getDatas() != null) {
                List<Data.DataBean.DatasBean> datas = data.getData().getDatas();
                for (int i = 0; i < datas.size(); i++) {
                    if (mTitle1.equals(datas.get(i).getTitle())) {
                        id1 = datas.get(i).getId();
                        originId = datas.get(i).getOriginId();
                    }
                }
                Map<String, Object> map1 = new HashMap<>();
                map1.put("biao", "shan");
                map1.put("id", id1);
                map1.put("originId", originId);
                Log.i("yangxu", originId + "onClick: " + id1);
                mPresenter.FishData(map1);
            }
        }
    }
}
