package com.example.lenovo.playandroid.module.zl;

import com.example.lenovo.playandroid.base.basemodule.HttpFinishCallBack;
import com.example.lenovo.playandroid.bean.zlbean.BannerBean;
import com.example.lenovo.playandroid.http.BaseObserver;
import com.example.lenovo.playandroid.http.HttpManager;
import com.example.lenovo.playandroid.http.RxUtils;
import com.example.lenovo.playandroid.presenter.zlpresenter.ZlPresenter;

import io.reactivex.Observable;

/**
 * Created by 18 on 2019/2/28.
 */

public class ZlModule {
    public interface FinishData<V> extends HttpFinishCallBack {
        void zlData(Object data);
    }

    public void zlgetData(Object obj, final FinishData finish) {
        finish.setAnimation();
        Observable<BannerBean> banner = HttpManager.getInstance().getServer().getBanner();
        banner.compose(RxUtils.<BannerBean>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<BannerBean>(finish) {
                    @Override
                    public void onNext(BannerBean value) {
                        finish.zlData(value);
                        finish.setHidoAnimation();
                    }
                });
    }
}
