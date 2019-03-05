package com.example.lenovo.playandroid.presenter.yx;

import com.example.lenovo.playandroid.base.presenter.IBasePresenter;
import com.example.lenovo.playandroid.module.yx.UsefulSitesModule;
import com.example.lenovo.playandroid.module.yx.YxModle;
import com.example.lenovo.playandroid.view.yx.IView;

/**
 * Created by lenovo on 2019/3/4.
 */

public class UsefulSitesPresenter<V extends IView> extends IBasePresenter<V> implements UsefulSitesModule.UsefulSitesCallback{
    //创建Modle层实例化对象
    private UsefulSitesModule mModle = new UsefulSitesModule();
    @Override
    public void setAnimation() {
        //向V层发送显示Progressbar信号
        mView.showAnimation();
    }

    @Override
    public void setHidoAnimation() {
        //向V层发送隐藏Progressbar信号
        mView.hidoAnimation();
    }

    //使M层与V关联
    public void getDataP(Object obj) {
        if (mView != null) {//判断V层不为空
            //向M层发送数据
            mModle.getData(this, obj);
        }
    }


    @Override
    public void setError(String error) {
        //向V层发送错误信息
        mView.showError(error);
    }

    @Override
    public void setData(Object data) {
        //向V层发送数据
        mView.show(data);
    }
}
