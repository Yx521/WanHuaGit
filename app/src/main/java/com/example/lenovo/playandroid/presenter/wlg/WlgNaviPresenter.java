package com.example.lenovo.playandroid.presenter.wlg;

import com.example.lenovo.playandroid.base.presenter.IBasePresenter;
import com.example.lenovo.playandroid.beans.wlg.NaviBean;
import com.example.lenovo.playandroid.beans.yx.Collect;
import com.example.lenovo.playandroid.module.wlg.WlgNaviModule;
import com.example.lenovo.playandroid.view.wlg.WlgNaviView;

/**
 * Created by lenovo on 2019/3/1.
 */

public class WlgNaviPresenter<V extends WlgNaviView> extends IBasePresenter<V> implements WlgNaviModule.GetFinsh {
    private WlgNaviModule mWlgNaviModule=new WlgNaviModule();


    @Override
    public void getNavi(NaviBean data) {
     if (mView!=null){
         mView.shouNaviBean(data);
     }

    }

    @Override
    public void getCollect(Collect collect) {
        if (mView!=null){
            mView.shouCollect(collect);
        }
    }

    @Override
    public void untCollect(Collect collect) {
        if (mView!=null){
            mView.shouunCollect(collect);
        }
    }

    public void setNavi(){
        if (mWlgNaviModule!=null){
            mWlgNaviModule.getMessage(this);
        }
    }
    public void setCollect(int id){
        if (mWlgNaviModule!=null){
            mWlgNaviModule.getCollect(this,id);
        }
    }
    public void setunCollect(int id){
        if (mWlgNaviModule!=null){
            mWlgNaviModule.getunCollect(this,id);
        }
    }

    @Override
    public void setAnimation() {
        if (mView!=null){
            mView.showAnimation();
        }

    }

    @Override
    public void setHidoAnimation() {
        if (mView!=null){
            mView.hidoAnimation();
        }

    }

    @Override
    public void setError(String error) {
        if (mView!=null){
            mView.showError(error);
        }

    }
}
