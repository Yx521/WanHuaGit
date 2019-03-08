package com.example.lenovo.playandroid.presenter.wx;

import com.example.lenovo.playandroid.base.presenter.IBasePresenter;
import com.example.lenovo.playandroid.module.wx.ModuleXin;
import com.example.lenovo.playandroid.view.yx.IView;

/**
 * Created by ASUS on 2019/3/7.
 */

public class PresenterXin<V extends IView> extends IBasePresenter<V> implements ModuleXin.FishCallBase{
    private  ModuleXin wxmodule=new ModuleXin();

    @Override
    public void setAnimation() {
        mView.showAnimation();
    }

    @Override
    public void setHidoAnimation() {
        mView.hidoAnimation();
    }

    public void FishData(Object data){
        wxmodule.wxgetData(data,this);
    }


    @Override
    public void setError(String error) {
        mView.showError(error);
    }

    @Override
    public void wxData(Object data) {
        mView.show(data);
    }
}
