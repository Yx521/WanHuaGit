package com.example.lenovo.playandroid.module.wx;

import com.example.lenovo.playandroid.base.module.HttpFinishCallBack;
import com.example.lenovo.playandroid.base.presenter.IBasePresenter;
import com.example.lenovo.playandroid.beans.wx.Batree;
import com.example.lenovo.playandroid.http.BaseObserver;
import com.example.lenovo.playandroid.http.HttpManager;
import com.example.lenovo.playandroid.http.RxUtils;
import com.example.lenovo.playandroid.view.yx.IView;

import io.reactivex.Observable;

/**
 * Created by ASUS on 2019/3/2.
 */

public class Wxmodule {

    //接口继承回调接口
    public interface FishBase<V> extends HttpFinishCallBack {
        //向P层传输数据
        void wxData(V data);
    }

    public void wxgetData(Object obj, final FishBase fish) {

        fish.setAnimation();
        //实体类
        Observable<Batree> batree = HttpManager.getInstance().getServer().getBatree();
        batree.compose(RxUtils.<Batree>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<Batree>(fish) {
                    @Override
                    public void onNext(Batree value) {
                        fish.wxData(value);
                        fish.setHidoAnimation();
                    }
                });
    }
}
