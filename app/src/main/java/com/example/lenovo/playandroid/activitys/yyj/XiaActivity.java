package com.example.lenovo.playandroid.activitys.yyj;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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


import com.example.lenovo.playandroid.R;


public class XiaActivity extends AppCompatActivity implements View.OnClickListener {

private String aa;
    private WebView web1;
    private ImageView fan;
    private ImageView xi;
    private TextView shu;
    private ProgressBar bar;
    private ImageView no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xia);
         aa = getIntent().getStringExtra("aa");
        String bb = getIntent().getStringExtra("bb");
        Toolbar tool = findViewById(R.id.tool);
        fan = findViewById(R.id.fan);
        xi = findViewById(R.id.xihuan);
        no = findViewById(R.id.noxihuan);
        shu = findViewById(R.id.shu);
        bar = findViewById(R.id.progressBar1);
        fan.setOnClickListener(this);
        xi.setOnClickListener(this);
        shu.setText(bb);
        xi.setOnClickListener(this);
        no.setOnClickListener(this);

        tool.setTitle("");
        setSupportActionBar(tool);
        web1 = findViewById(R.id.wev);
        // web1.loadUrl(aa);
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
                xi.setVisibility(View.GONE);
                no.setVisibility(View.VISIBLE);
                break;
            case R.id.noxihuan:
                xi.setVisibility(View.VISIBLE);
                no.setVisibility(View.GONE);
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
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
                intent.putExtra(Intent.EXTRA_TEXT, "请在浏览器中打开"+aa);
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
}
