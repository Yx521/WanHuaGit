package com.example.lenovo.playandroid.module.wlg;

import com.example.lenovo.playandroid.base.module.HttpFinishCallBack;
import com.example.lenovo.playandroid.beans.wlg.NaviBean;
import com.example.lenovo.playandroid.beans.yx.Collect;
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
        void getCollect(Collect collect);
        void untCollect(Collect collect);
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
    public void getCollect(final GetFinsh getFinsh,int id) {
        getFinsh.setAnimation();
        Observable<Collect> collect = HttpManager.getInstance().getLoginServer().getCollect(id);
        collect.compose(RxUtils.<Collect>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<Collect>(getFinsh) {
                    @Override
                    public void onNext(Collect value) {
                        getFinsh.getCollect(value);
                        getFinsh.setHidoAnimation();
                    }
                });
    }
    public void getunCollect(final GetFinsh getFinsh,int id) {
        getFinsh.setAnimation();
        Observable<Collect> collect = HttpManager.getInstance().getLoginServer().cancelCollectArticle(id);
        collect.compose(RxUtils.<Collect>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<Collect>(getFinsh) {
                    @Override
                    public void onNext(Collect value) {
                        getFinsh.untCollect(value);
                        getFinsh.setHidoAnimation();
                    }
                });
    }
}
