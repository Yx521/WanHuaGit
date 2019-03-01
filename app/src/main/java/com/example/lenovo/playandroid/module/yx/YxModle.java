package com.example.lenovo.playandroid.module.yx;

import com.example.lenovo.playandroid.base.module.HttpFinishCallBack;
import com.example.lenovo.playandroid.beans.yx.ProjectClassifyData;
import com.example.lenovo.playandroid.http.BaseObserver;
import com.example.lenovo.playandroid.http.HttpManager;
import com.example.lenovo.playandroid.http.RxUtils;

import java.util.Map;

/**
 * Created by lenovo on 2019/3/1.
 */

public class YxModle {
    //接口继承回调接口
    public interface Callback<V> extends HttpFinishCallBack {
        //向P层传输数据
        void setData(V data);
    }

    public void getData(final Callback callback, Object obj) {
        callback.setAnimation();
        Map<String,Object> map = (Map<String, Object>) obj;
        int cid = (int) map.get("cid");
        int page = (int) map.get("page");
        HttpManager.getInstance().getServer().getProjectListData(page,cid)
                .compose(RxUtils.<ProjectClassifyData>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<ProjectClassifyData>(callback) {
                    @Override
                    public void onNext(ProjectClassifyData value) {
                        callback.setData(value);
                        callback.setHidoAnimation();
                    }
                });
    }
}
