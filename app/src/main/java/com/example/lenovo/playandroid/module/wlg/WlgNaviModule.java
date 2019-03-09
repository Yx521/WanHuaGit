package com.example.lenovo.playandroid.module.wlg;

import com.example.lenovo.playandroid.base.module.HttpFinishCallBack;
import com.example.lenovo.playandroid.beans.wlg.NaviBean;
import com.example.lenovo.playandroid.beans.zl.BannerBean;
import com.example.lenovo.playandroid.http.BaseObserver;
import com.example.lenovo.playandroid.http.HttpManager;
import com.example.lenovo.playandroid.http.RxUtils;
import com.example.lenovo.playandroid.module.zl.ZlModule;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2019/3/1.
 */

public class WlgNaviModule {
    public interface GetFinsh<V> extends HttpFinishCallBack {
        void getNavi(NaviBean data);
    }

    public void getMessage(final GetFinsh getFinsh) {
        getFinsh.setAnimation();
        Observable<NaviBean> naviBean = HttpManager.getInstance().getServer().getNaviBean();
        naviBean.compose(RxUtils.<NaviBean>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<NaviBean>(getFinsh) {
                    @Override
                    public void onNext(NaviBean value) {
                        getFinsh.getNavi(value);
                        getFinsh.setHidoAnimation();
                    }
                });
    }
}
