package com.example.lenovo.playandroid.module.wx;

import com.example.lenovo.playandroid.base.module.HttpFinishCallBack;
import com.example.lenovo.playandroid.beans.wx.Batree;
import com.example.lenovo.playandroid.beans.yx.Collect;
import com.example.lenovo.playandroid.http.BaseObserver;
import com.example.lenovo.playandroid.http.HttpManager;
import com.example.lenovo.playandroid.http.RxUtils;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by ASUS on 2019/3/7.
 */

public class ModuleXin {

    //接口继承回调接口
    public interface FishCallBase<V> extends HttpFinishCallBack {
        //向P层传输数据
        void wxData(V data);
    }

    public void wxgetData(Object obj, final FishCallBase fish) {

        fish.setAnimation();
        //实体类
        Map<String, Object> map = (Map<String, Object>) obj;
        String a = (String) map.get("a");
        String l = (String) map.get("l");
        String t = (String) map.get("t");

        HttpManager.getInstance().getLoginServer().getWaiCollect(t,a,l)
                .compose(RxUtils.<Collect>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<Collect>(fish) {
                    @Override
                    public void onNext(Collect value) {
                        fish.wxData(value);
                        fish.setHidoAnimation();
                    }
                });
    }
}
