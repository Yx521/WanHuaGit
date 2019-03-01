package com.example.lenovo.playandroid.presenter.zl;

import com.example.lenovo.playandroid.base.presenter.IBasePresenter;
import com.example.lenovo.playandroid.module.zl.ZlModule;
import com.example.lenovo.playandroid.view.yx.IView;

/**
 * Created by 18 on 2019/2/28.
 */

public class ZlPresenter<V extends IView> extends IBasePresenter<V> implements ZlModule.FinishData {
    private ZlModule mZlModule = new ZlModule();

    @Override
    public void setAnimation() {
        mView.showAnimation();
    }

    @Override
    public void setHidoAnimation() {
        mView.hidoAnimation();
    }

    public void upwith(Object obj) {
        mZlModule.zlgetData(obj, this);
    }

    @Override
    public void setError(String error) {
          mView.showError(error);
    }

    @Override
    public void zlData(Object data) {
        mView.show(data);
    }
}
