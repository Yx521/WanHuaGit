package com.example.lenovo.playandroid.view.zl;


import com.example.lenovo.playandroid.base.view.BaseView;
import com.example.lenovo.playandroid.beans.wx.Data;
import com.example.lenovo.playandroid.beans.wx.HttpResult;

/**
 * Created by 18 on 2019/3/1.
 */

public interface ZlView extends BaseView {
    void BannerData(Object bannerdata);

    void MainData(Object maindata);

    void Login(Object logindata);

    void Register(Object registerdata);

    void setData(Object obj);

    void setS(Data value);

    void shan(HttpResult value);
}
