package com.example.lenovo.playandroid.activitys.zl;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.lenovo.playandroid.beans.zl.LoginData;
import com.example.lenovo.playandroid.presenter.zl.ZlPresenter;
import com.example.lenovo.playandroid.view.zl.ZlView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 18 on 2019/3/6.
 */

public class RegisterActivity extends BaseActivity<ZlView, ZlPresenter<ZlView>> implements ZlView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.register_password_edit)
    EditText mRegisterPasswordEdit;
    @BindView(R.id.register_account_edit)
    EditText mRegisterAccountEdit;
    @BindView(R.id.register_confirm_password_edit)
    EditText mRegisterConfirmPasswordEdit;
    @BindView(R.id.register_btn)
    Button mRegisterBtn;

    @Override
    public void BannerData(Object bannerdata) {

    }

    @Override
    public void MainData(Object maindata) {

    }

    @Override
    public void Login(Object logindata) {

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void Register(Object registerdata) {
        LoginData loginData = (LoginData) registerdata;
        String errorMsg = loginData.getErrorMsg();
        if (errorMsg.equals("用户名已经被注册!")) {
            Snackbar.make(mRegisterBtn, errorMsg, Snackbar.LENGTH_SHORT)
                    .setAction("知道了", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).setActionTextColor(R.color.tab_bac)
                    .show();
        } else {
            finish();
        }

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
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("注册");
    }

    @Override
    protected int creatrLayoutId() {
        //状态栏颜色
        Window window = RegisterActivity.this.getWindow();

        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        //设置状态栏颜色

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.register_bac));
        }
        return R.layout.register_activity;
    }


    @SuppressLint("ResourceAsColor")
    @OnClick(R.id.register_btn)
    public void onViewClicked() {
        int version = Integer.valueOf(android.os.Build.VERSION.SDK);
        String username = mRegisterAccountEdit.getText().toString();
        String password = mRegisterPasswordEdit.getText().toString();
        String repassword = mRegisterConfirmPasswordEdit.getText().toString();
        if (username.length() < 10) {
            startActivity(new Intent(this, RegisterActivity.class));
            if (version>5){
                overridePendingTransition(R.anim.intentloser,R.anim.intentloser);
            }
            //状态栏颜色
            Window window = RegisterActivity.this.getWindow();

            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            //设置状态栏颜色

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.light_transparent));
            }
            Snackbar.make(mRegisterBtn, "注册失败", Snackbar.LENGTH_SHORT).setActionTextColor(R.color.tab_bac)
                    .setAction("知道了", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
        } else if (!password.equals(repassword)) {
            Snackbar.make(mRegisterBtn, "密码不匹配", Snackbar.LENGTH_SHORT).setActionTextColor(R.color.tab_bac)
                    .setAction("知道了", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
        } else {
            mPresenter.register(username, password, repassword);
        }
    }
}
