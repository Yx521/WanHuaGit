package com.example.lenovo.playandroid.presenter.zl;

import com.example.lenovo.playandroid.base.presenter.IBasePresenter;
import com.example.lenovo.playandroid.beans.wx.Data;
import com.example.lenovo.playandroid.beans.wx.HttpResult;
import com.example.lenovo.playandroid.module.zl.ZlModule;
import com.example.lenovo.playandroid.view.yx.IView;
import com.example.lenovo.playandroid.view.zl.ZlView;

import java.util.Map;

/**
 * Created by 18 on 2019/2/28.
 */

public class ZlPresenter<V extends ZlView> extends IBasePresenter<V> implements ZlModule.FinishData {
    private ZlModule mZlModule = new ZlModule();

    @Override
    public void setAnimation() {
        mView.showAnimation();
    }

    @Override
    public void setHidoAnimation() {
        mView.hidoAnimation();
    }

    public void upwithBanner(Object obj) {
        mZlModule.zlgetData(obj, this);
    }

    @Override
    public void setError(String error) {
        mView.showError(error);
    }

    @Override
    public void zlBannerData(Object data) {
        mView.BannerData(data);
    }

    @Override
    public void zlItemData(Object itemData) {
        mView.MainData(itemData);
    }

    @Override
    public void zlLoginData(Object logindata) {
        mView.Login(logindata);
    }

    @Override
    public void zlRegister(Object registerdata) {
        mView.Register(registerdata);
    }

    @Override
    public void weidata(Object obj) {
        mView.setData(obj);
    }

    @Override
    public void zlSData(Data value) {
        mView.setS(value);
    }

    @Override
    public void zlSHan(HttpResult value) {
        mView.shan(value);
    }

    public void upwithItem(Object page) {
        mZlModule.zlgetItem(page, this);
    }

    public void upwithsingle(int id, int page) {
        mZlModule.zlgetsingle(id, page, this);
    }

    public void login(String username, String password) {
        mZlModule.zlLogin(username, password, this);
    }

    public void register(String username, String password, String repassword) {
        mZlModule.zlRegister(username, password, repassword, this);
    }

    public void FishData(Map<String, Object> map) {
        mZlModule.zlIsLiske(map,this);
    }

    public void unCollect(int id,int originId) {
        mZlModule.zlunCollect(id,originId,this);
    }

    public void shouc() {
        mZlModule.zlshouc(this);
    }

    public void Data(Map<String, Object> map) {
        mZlModule.setData(map,this);
    }

    public void shoucang() {
        mZlModule.shouc(this);
    }

    public void shan(int id, int originId) {
        mZlModule.shan(id ,originId,this);
    }
}
