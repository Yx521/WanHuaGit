package com.example.lenovo.playandroid.activitys.wx;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.activitys.zl.LoginActivity;
import com.example.lenovo.playandroid.base.activity.BaseActivity;
import com.example.lenovo.playandroid.beans.yx.Collect;
import com.example.lenovo.playandroid.presenter.wx.PresenterXin;
import com.example.lenovo.playandroid.view.yx.IView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.http.Url;

public class Details extends BaseActivity<IView,PresenterXin<IView>> implements IView,View.OnClickListener {

   private String title;
    private WebView detailsWebview;
    private ImageView img;
    private ImageView xin_img;
    private TextView details_text;
    private ProgressBar bar;
    private ImageView bu_img;
    private String url;
    private int id;
    private String author;


    @Override
    protected void initEventAndData() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        url = intent.getStringExtra("url");
        id = intent.getIntExtra("id",0);
        author = intent.getStringExtra("author");
        Toolbar tool = findViewById(R.id.tool);
        img = findViewById(R.id.img);
        xin_img = findViewById(R.id.xin_img);
        bu_img= findViewById(R.id.bu_img);
        details_text = findViewById(R.id.details_text);
        bar = findViewById(R.id.progressBar1);
        img.setOnClickListener(this);
        xin_img.setOnClickListener(this);
        details_text.setText(title);
        xin_img.setOnClickListener(this);
        bu_img.setOnClickListener(this);

        tool.setTitle("");
        setSupportActionBar(tool);
        detailsWebview = findViewById(R.id.details_webview);
        // web1.loadUrl(aa);
        detailsWebview.loadUrl(url);
        WebSettings settings = detailsWebview.getSettings();
        settings.setJavaScriptEnabled(true);
        detailsWebview.setWebChromeClient(new WebChromeClient() {
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
        return R.layout.activity_details;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img:
                finish();
                break;
            case R.id.xin_img:
                xin_img.setVisibility(View.GONE);
                bu_img.setVisibility(View.VISIBLE);
                Map<String,Object> map = new HashMap<>();
                map.put("t",title);
                map.put("a",author);
                map.put("l",url);
                mPresenter.FishData(map);
                break;
            case R.id.bu_img:
                xin_img.setVisibility(View.VISIBLE);
                bu_img.setVisibility(View.GONE);

                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, Menu.NONE, "分享").setIcon(R.drawable.ic_action_share);
        menu.add(2, 3, Menu.NONE, "用系统浏览器打开").setIcon(R.drawable.ic_action_browser);
          return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自动生成的方法存根
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (detailsWebview.canGoBack()) {
                //当webview不是处于第一页面时，返回上一个页面

                return true;
            } else {
                //当webview处于第一页面时,直接退出程序
                detailsWebview.goBack();
                //	System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
                intent.putExtra(Intent.EXTRA_TEXT, "请在浏览器中打开"+ title);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, getTitle()));
                break;
            case 3:
                Uri uri = Uri.parse(url);
                Intent intent1= new Intent(Intent.ACTION_VIEW, uri);

                startActivity(intent1);
                break;
        }
        return false;
    }


    @Override
    public void show(Object o) {
        Collect collect = (Collect) o;
        int errorCode = collect.getErrorCode();
        Log.i("yangxu", "show: "+errorCode );
    }

    @Override
    public void showError(String error) {
        Log.i("yangxu", "show: "+error );
    }

    @Override
    protected PresenterXin<IView> createPresenter() {
        return new PresenterXin<>();
    }
}
