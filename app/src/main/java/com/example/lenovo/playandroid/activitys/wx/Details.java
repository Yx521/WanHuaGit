package com.example.lenovo.playandroid.activitys.wx;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.Toast;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.base.activity.BaseActivity;
import com.example.lenovo.playandroid.beans.wx.Data;
import com.example.lenovo.playandroid.beans.wx.HttpResult;
import com.example.lenovo.playandroid.beans.yx.Collect;
import com.example.lenovo.playandroid.dao.CanData;
import com.example.lenovo.playandroid.dao.DataBaseMannger;
import com.example.lenovo.playandroid.presenter.wx.PresenterXin;
import com.example.lenovo.playandroid.view.yx.IView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Details extends BaseActivity<IView,PresenterXin<IView>> implements IView,View.OnClickListener {

    private  String title;
    private WebView detailsWebview;
    private ImageView img;
    private ImageView xin_img;
    private TextView details_text;
    private ProgressBar bar;
    private ImageView bu_img;
    private String url;
    private int id;
    private String author;
    private int originId;
    private int id1;


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
        List<CanData> canData = DataBaseMannger.getIntrance().selectCan();
        if(canData.size()!=0){
            for (int i = 0; i < canData.size(); i++) {
                if(canData.get(i).getTitle().equals(title)&&canData.get(i).getAuthor().equals(author)
                        &&canData.get(i).getLink().equals(url)){
                    xin_img.setVisibility(View.GONE);
                    bu_img.setVisibility(View.VISIBLE);
                }
            }
        }
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
                map.put("id",id);
                map.put("biao","shou");
                mPresenter.FishData(map);
                DataBaseMannger.getIntrance().insertCan(new CanData(null,title,author,url,true));
                break;
            case R.id.bu_img:
                xin_img.setVisibility(View.VISIBLE);
                bu_img.setVisibility(View.GONE);
                Map<String,Object> map1 = new HashMap<>();
                map1.put("biao","shou1");
                mPresenter.FishData(map1);
                List<CanData> canData = DataBaseMannger.getIntrance().selectCan();
                for (int i = 0; i < canData.size(); i++) {
                    if(canData.get(i).getTitle().equals(title)&&canData.get(i).getAuthor().equals(author)
                            &&canData.get(i).getLink().equals(url)){
                        Long id = canData.get(i).getId();
                        DataBaseMannger.getIntrance().deleteCan(new CanData(id,null,null,null,null));
                    }
                }
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
        Map<String,Object> map = (Map<String, Object>) o;
        String biao = (String) map.get("biao");
        if("shou".equals(biao)){
            Collect collect = (Collect) map.get("va");
            int errorCode = collect.getErrorCode();
            Log.i("yangxu", "show: "+errorCode );

            if (errorCode==0){
                Toast.makeText(mActivity,"收藏成功",Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(mActivity,"收藏失败",Toast.LENGTH_SHORT).show();
            }
        }else if("shan".equals(biao)){
            HttpResult collect = (HttpResult) map.get("va");
            int errorCode = collect.getErrorCode();
            if (errorCode==0){
                Toast.makeText(mActivity,"删除成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(mActivity,"删除失败",Toast.LENGTH_SHORT).show();
            }
        }else if("shou1".equals(biao)){
            Data data = (Data) map.get("va");
            List<Data.DataBean.DatasBean> datas = data.getData().getDatas();
            for (int i = 0; i < datas.size(); i++) {
                if(title.equals(datas.get(i).getTitle())){
                    id1 = datas.get(i).getId();
                    originId = datas.get(i).getOriginId();
                }
            }
            Map<String,Object> map1 = new HashMap<>();
            map1.put("biao","shan");
            map1.put("id",id1);
            map1.put("originId",originId);
            Log.i("yangxu", originId+"onClick: "+id1);
            mPresenter.FishData(map1);

        }

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
