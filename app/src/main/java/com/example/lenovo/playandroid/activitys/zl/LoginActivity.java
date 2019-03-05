package com.example.lenovo.playandroid.activitys.zl;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.base.activity.BaseActivity;
import com.example.lenovo.playandroid.presenter.zl.ZlPresenter;
import com.example.lenovo.playandroid.view.zl.ZlView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 18 on 2019/3/4.
 */

public class LoginActivity extends BaseActivity<ZlView, ZlPresenter<ZlView>> implements ZlView, View.OnClickListener {
    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.register)
    Button mRegister;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void BannerData(Object bannerdata) {

    }

    @Override
    public void MainData(Object maindata) {

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
        mToolbar.setNavigationOnClickListener(this);
    }

    @Override
    protected int creatrLayoutId() {
        //状态栏颜色
        Window window = LoginActivity.this.getWindow();

        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        //设置状态栏颜色

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.light_transparent));
        }
        return R.layout.login_activity;
    }


    @OnClick({R.id.login, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                judgingLogin();
                break;
            case R.id.register:
                break;
        }
    }

    private void judgingLogin() {
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        if (username.length() <= 0 && password.length() <= 0) {
            Snackbar.make(mRegister, getString(R.string.account_password_null_tint), Snackbar.LENGTH_SHORT)
                    .setActionTextColor(Color.parseColor("#049486"))
                    .setAction("知道了", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    })/*.setCallback(new Snackbar.Callback(){
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    super.onDismissed(transientBottomBar, event);

                }
            })*/.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
