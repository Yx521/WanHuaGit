package com.example.lenovo.playandroid.activitys.wx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.playandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Details extends AppCompatActivity {
    @BindView(R.id.details_img)
    ImageView detailsImg;
    @BindView(R.id.details_text)
    TextView detailsText;
    @BindView(R.id.details_webview)
    WebView detailsWebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        detailsText.setText(getIntent().getStringExtra("title"));

        detailsWebview.loadUrl(getIntent().getStringExtra("url"));
    }
    @OnClick(R.id.details_img)
    public void onViewClicked() {
        finish();
    }
}
