package com.example.lenovo.playandroid.module.yx;

import com.example.lenovo.playandroid.base.module.HttpFinishCallBack;
import com.example.lenovo.playandroid.beans.yx.ProjectClassifyData;
import com.example.lenovo.playandroid.beans.yx.UsefulSiteData;
import com.example.lenovo.playandroid.http.BaseObserver;
import com.example.lenovo.playandroid.http.HttpManager;
import com.example.lenovo.playandroid.http.RxUtils;

import java.util.Map;

/**
 * Created by lenovo on 2019/3/4.
 */

public class UsefulSitesModule {
    //接口继承回调接口
    public interface UsefulSitesCallback<V> extends HttpFinishCallBack {
        //向P层传输数据
        void setData(V data);
    }

    public void getData(final UsefulSitesCallback callback, Object obj) {
        callback.setAnimation();
        HttpManager.getInstance().getServer().getUsefulSiteData()
                .compose(RxUtils.<UsefulSiteData>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<UsefulSiteData>(callback) {
                    @Override
                    public void onNext(UsefulSiteData value) {
                        callback.setData(value);
                        callback.setHidoAnimation();
                    }
                });
    }
}
