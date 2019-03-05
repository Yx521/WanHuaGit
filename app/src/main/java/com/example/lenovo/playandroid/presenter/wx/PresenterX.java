package com.example.lenovo.playandroid.presenter.wx;

import com.example.lenovo.playandroid.base.presenter.BasePresenter;
import com.example.lenovo.playandroid.base.presenter.IBasePresenter;
import com.example.lenovo.playandroid.module.wx.ModuleX;

import com.example.lenovo.playandroid.module.wx.Wxmodule;
import com.example.lenovo.playandroid.module.yx.Modle;
import com.example.lenovo.playandroid.view.yx.IView;

/**
 * Created by ASUS on 2019/3/3.
 */

public class PresenterX <V extends IView> extends IBasePresenter<V> implements ModuleX.WxBase{
    private  ModuleX moduleX=new ModuleX();

    @Override
    public void setAnimation() {
        mView.showAnimation();
    }

    @Override
    public void setHidoAnimation() {
        mView.hidoAnimation();
    }



    public void WDataP(int page,int cid) {
        if (mView != null) {
            moduleX.WgetData(this, page,cid);
        }
    }

    @Override
    public void setError(String error) {
        mView.showError(error);
    }

    @Override
    public void WData(Object data) {
        mView.show(data);


    }
}
