package com.example.lenovo.playandroid.presenter.yyj;


import com.example.lenovo.playandroid.base.presenter.IBasePresenter;
import com.example.lenovo.playandroid.module.yyj.Main_Fu;
import com.example.lenovo.playandroid.view.yx.IView;

public class Main_beanP<V extends IView> extends IBasePresenter<V> implements Main_Fu.FuCallbak {
    private Main_Fu main_fu = new Main_Fu();

    public void beanP(Object obj, String num, String page) {
        main_fu.getBe(this, obj, num, page);
    }

    @Override
    public void setFuOb(Object o) {
        mView.show(o);
    }

    @Override
    public void setBean(Object o) {
        mView.show(o);
    }





    @Override
    public void setAnimation() {
        mView.showAnimation();
    }

    @Override
    public void setHidoAnimation() {
        mView.hidoAnimation();
    }

    @Override
    public void setError(String error) {
        mView.showError(error);
    }
}
