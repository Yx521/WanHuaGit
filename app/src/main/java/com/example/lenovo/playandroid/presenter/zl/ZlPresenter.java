package com.example.lenovo.playandroid.presenter.zl;

import com.example.lenovo.playandroid.base.presenter.IBasePresenter;
import com.example.lenovo.playandroid.module.zl.ZlModule;
import com.example.lenovo.playandroid.view.yx.IView;
import com.example.lenovo.playandroid.view.zl.ZlView;

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

    public void upwithItem(Object page) {
        mZlModule.zlgetItem(page, this);
    }
    public void upwithsingle(int id , int page){
        mZlModule.zlgetsingle(id,page,this);
    }

}
