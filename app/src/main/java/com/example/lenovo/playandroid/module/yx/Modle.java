package com.example.lenovo.playandroid.module.yx;


import com.example.lenovo.playandroid.base.module.HttpFinishCallBack;
import com.example.lenovo.playandroid.beans.yx.ProjectClassify;
import com.example.lenovo.playandroid.http.BaseObserver;
import com.example.lenovo.playandroid.http.HttpManager;
import com.example.lenovo.playandroid.http.RxUtils;

/**
 * Created by lenovo on 2019/2/28.
 */

public class Modle {
    //接口继承回调接口
    public interface Callback<V> extends HttpFinishCallBack {
        //向P层传输数据
        void setData(V data);
    }

    public void getData(final Callback Callback, Object obj) {
        Callback.setAnimation();
        HttpManager.getInstance().getServer().getProjectClassifyData()
                .compose(RxUtils.<ProjectClassify>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<ProjectClassify>(Callback) {
                    @Override
                    public void onNext(ProjectClassify value) {
                        Callback.setData(value);
                        Callback.setHidoAnimation();
                    }
                });
    }
}
