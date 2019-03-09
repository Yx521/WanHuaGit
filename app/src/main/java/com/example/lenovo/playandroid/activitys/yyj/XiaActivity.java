package com.example.lenovo.playandroid.activitys.yyj;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.activitys.zl.LoginActivity;
import com.example.lenovo.playandroid.base.activity.BaseActivity;
import com.example.lenovo.playandroid.beans.wx.Data;
import com.example.lenovo.playandroid.beans.wx.HttpResult;
import com.example.lenovo.playandroid.beans.yx.Collect;
import com.example.lenovo.playandroid.beans.yyj.Bean;
import com.example.lenovo.playandroid.dao.CanData;
import com.example.lenovo.playandroid.dao.DataBaseMannger;
import com.example.lenovo.playandroid.dao.LogDaoBean;
import com.example.lenovo.playandroid.dao.LoginManager;
import com.example.lenovo.playandroid.presenter.wx.PresenterXin;
import com.example.lenovo.playandroid.view.yx.IView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XiaActivity extends BaseActivity<IView, PresenterXin<IView>> implements IView, View.OnClickListener {
    private int flag = 0;
    private String aa;
    private WebView web1;
    private ImageView fan;
    private ImageView xi;
    private TextView shu;
    private ProgressBar bar;
    private ImageView no;
    private List<LogDaoBean> select;
    private Boolean coack;
    private List<Bean.DataBean.DatasBean> ll;
    private int i;
    private int nobok;
    private String bb;
    private String author;
    private String name;
    private String cc;
    private String author1;
    private int idd;
    private SharedPreferences like;
    private SharedPreferences.Editor edit;
    private int originId;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fan:
                finish();
                break;
            case R.id.xihuan:
                xi.setVisibility(View.GONE);
                no.setVisibility(View.VISIBLE);
                Map<String,Object> map1 = new HashMap<>();
                map1.put("biao","shou1");
                mPresenter.FishData(map1);
                List<CanData> canData = DataBaseMannger.getIntrance().selectCan();
                for (int i = 0; i < canData.size(); i++) {
                    if(canData.get(i).getTitle().equals(bb)&&canData.get(i).getAuthor().equals(author)
                            &&canData.get(i).getLink().equals(aa)){
                        Long id = canData.get(i).getId();
                        DataBaseMannger.getIntrance().deleteCan(new CanData(id,null,null,null,null));
                    }
                }
                break;
            case R.id.buxihuan:
                if (select.get(0).getIsLogin() == true) {
                    xi.setVisibility(View.VISIBLE);
                    no.setVisibility(View.GONE);
                    //mPresenter.CancelP(null,idd);
                    Map<String,Object> map = new HashMap<>();
                    map.put("t",bb);
                    map.put("a",author);
                    map.put("l",aa);
                    map.put("id",idd);
                    map.put("biao","shou");
                    mPresenter.FishData(map);
                    DataBaseMannger.getIntrance().insertCan(new CanData(null,bb,author,aa,true));
                } else {
                    startActivity(new Intent(XiaActivity.this, LoginActivity.class));
                }
                break;
        }
    }

    @Override
    protected PresenterXin<IView> createPresenter() {
        return new PresenterXin<>();
    }

    @Override
    protected void initEventAndData() {
        EventBus.getDefault().unregister(this);
        aa = getIntent().getStringExtra("url");
        bb = getIntent().getStringExtra("bb");
        author = getIntent().getStringExtra("idd");
        cc = getIntent().getStringExtra("cc");
        String id = getIntent().getStringExtra("id");
        idd = Integer.parseInt(id);
        author1 = getIntent().getStringExtra("author");
        this.i = Integer.parseInt(cc);
        nobok=i;
        Toolbar tool = findViewById(R.id.tool);
        fan = findViewById(R.id.fan);
        xi = findViewById(R.id.xihuan);
        no = findViewById(R.id.buxihuan);
        shu = findViewById(R.id.shu);
        bar = findViewById(R.id.progressBar1);

        fan.setOnClickListener(this);
        xi.setOnClickListener(this);
        shu.setText(bb);
        xi.setOnClickListener(this);
        no.setOnClickListener(this);
        List<CanData> canData = DataBaseMannger.getIntrance().selectCan();
        if(canData.size()!=0){
            for (int i = 0; i < canData.size(); i++) {
                if(canData.get(i).getTitle().equals(bb)&&canData.get(i).getAuthor().equals(author)
                        &&canData.get(i).getLink().equals(aa)){
                    xi.setVisibility(View.VISIBLE);
                    no.setVisibility(View.GONE);
                }
            }
        }
        select = LoginManager.mMySqlHelper().select();
        tool.setTitle("");
        setSupportActionBar(tool);
        web1 = findViewById(R.id.wev);
        web1.loadUrl(aa);
        WebSettings settings = web1.getSettings();
        settings.setJavaScriptEnabled(true);
        web1.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    bar.setVisibility(View.GONE);
                } else {
                    bar.setVisibility(View.VISIBLE);
                    bar.setProgress(newProgress);
                }
            }
        });
    }

    @Override
    protected int creatrLayoutId() {
        return R.layout.activity_xia;
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
                Toast.makeText(mActivity, "删除成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mActivity, "删除失败", Toast.LENGTH_SHORT).show();
            }
        } else if ("shou1".equals(biao)) {
            Data data = (Data) map.get("va");
            List<Data.DataBean.DatasBean> datas = data.getData().getDatas();
            for (int i = 0; i < datas.size(); i++) {
                if (bb.equals(datas.get(i).getTitle())) {
                    idd = datas.get(i).getId();
                    originId = datas.get(i).getOriginId();
                }
            }
            Map<String, Object> map1 = new HashMap<>();
            map1.put("biao", "shan");
            map1.put("id", idd);
            map1.put("originId", originId);
            Log.i("yangxu", originId + "onClick: " + idd);
            mPresenter.FishData(map1);

            //    Toast.makeText(this, "收藏成功", Toast.LENGTH_LONG).show();


        }
    }
    @Override
    public void showError(String error) {

    }

    /*    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_xia);
            EventBus.getDefault().unregister(this);
            aa = getIntent().getStringExtra("aa");
            String bb = getIntent().getStringExtra("bb");
            String cc = getIntent().getStringExtra("cc");
            i = Integer.parseInt(cc);
            Toolbar tool = findViewById(R.id.tool);
            fan = findViewById(R.id.fan);
            xi = findViewById(R.id.xihuan);
            no = findViewById(R.id.buxihuan);
            shu = findViewById(R.id.shu);
            bar = findViewById(R.id.progressBar1);
            fan.setOnClickListener(this);
            xi.setOnClickListener(this);
            shu.setText(bb);
            xi.setOnClickListener(this);
            no.setOnClickListener(this);
            select = LoginManager.mMySqlHelper().select();
            tool.setTitle("");
            setSupportActionBar(tool);
            web1 = findViewById(R.id.wev);
            web1.loadUrl(aa);
            WebSettings settings = web1.getSettings();
            settings.setJavaScriptEnabled(true);
            web1.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    if (newProgress == 100) {
                        bar.setVisibility(View.GONE);
                    } else {
                        bar.setVisibility(View.VISIBLE);
                        bar.setProgress(newProgress);
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fan:
                    finish();
                    break;
                case R.id.xihuan:
                    if (select.get(0).getIsLogin() == true) {
                        xi.setVisibility(View.GONE);
                        no.setVisibility(View.VISIBLE);
                    } else {
                        startActivity(new Intent(XiaActivity.this, LoginActivity.class));
                    }
                    break;
                case R.id.buxihuan:
                    xi.setVisibility(View.VISIBLE);
                    no.setVisibility(View.GONE);
                    Toast.makeText(this, "收藏成功", Toast.LENGTH_LONG).show();
                    break;
            }
        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            menu.add(0, 1, Menu.NONE, "分享").setIcon(R.drawable.ic_action_share);
            menu.add(2, 3, Menu.NONE, "用系统浏览器打开").setIcon(R.drawable.ic_action_browser);

            return true;
        }

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
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            // TODO 自动生成的方法存根
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (web1.canGoBack()) {
                    //当webview不是处于第一页面时，返回上一个页面

                    return true;
                } else {
                    //当webview处于第一页面时,直接退出程序
                    web1.goBack();
                    //	System.exit(0);
                }
            }
            return super.onKeyDown(keyCode, event);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case 1:
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
                    intent.putExtra(Intent.EXTRA_TEXT, "请在浏览器中打开" + aa);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(Intent.createChooser(intent, getTitle()));
                    break;
                case 3:
                    Uri uri = Uri.parse(aa);
                    Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent1);
                    break;
            }
            return false;
        }

        @Subscribe(threadMode = ThreadMode.MAIN, sticky = true) //在ui线程执行
        public void onDatant(List<Bean.DataBean.DatasBean> datas) {
            this.ll = datas;
            Log.e("eve1", datas.get(0).getChapterName());


        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            EventBus.getDefault().register(this);
        }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, Menu.NONE, "分享").setIcon(R.drawable.ic_action_share);
        menu.add(2, 3, Menu.NONE, "用系统浏览器打开").setIcon(R.drawable.ic_action_browser);

        return true;
    }

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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自动生成的方法存根
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (web1.canGoBack()) {
                //当webview不是处于第一页面时，返回上一个页面

                return true;
            } else {
                //当webview处于第一页面时,直接退出程序
                web1.goBack();
                //	System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
                intent.putExtra(Intent.EXTRA_TEXT, "请在浏览器中打开" + aa);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, getTitle()));
                break;
            case 3:
                Uri uri = Uri.parse(aa);
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent1);
                break;
        }
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true) //在ui线程执行
    public void onDatant(List<Bean.DataBean.DatasBean> datas) {
        this.ll = datas;
        Log.e("eve1", datas.get(0).getChapterName());


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().register(this);
    }
}
