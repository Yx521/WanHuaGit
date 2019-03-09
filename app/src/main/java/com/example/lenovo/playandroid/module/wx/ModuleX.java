package com.example.lenovo.playandroid.module.wx;

import com.example.lenovo.playandroid.base.module.HttpFinishCallBack;
import com.example.lenovo.playandroid.beans.wx.Batree;
import com.example.lenovo.playandroid.beans.wx.Re;
import com.example.lenovo.playandroid.beans.yx.ProjectClassify;
import com.example.lenovo.playandroid.beans.yx.ProjectClassifyData;
import com.example.lenovo.playandroid.http.BaseObserver;
import com.example.lenovo.playandroid.http.HttpManager;
import com.example.lenovo.playandroid.http.RxUtils;
import com.example.lenovo.playandroid.module.yx.YxModle;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by ASUS on 2019/3/3.
 */

public class ModuleX {
    //接口继承回调接口
    public interface WxBase<V> extends HttpFinishCallBack {
        //向P层传输数据
        void WData(V data);
    }



    public void  WgetData(final WxBase base,int page,int cid) {

        base.setAnimation();

       /* Map<String,Object> map = (Map<String, Object>) obj;
        int cid = (int) map.get("cid");
        int page = (int) map.get("page");*/
        //实体类
       /* HttpManager.getInstance().getServer().getYan(page,cid).
        compose(RxUtils.<Re>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<Re>(base) {
                    @Override
                    public void onNext(Re value) {
                        base.WData(value);
                        base.setHidoAnimation();
                    }
                });*/
        base.setAnimation();
        HttpManager.getInstance().getServer().getYan(page,cid)
                .compose(RxUtils.<Re>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<Re>(base) {
                    @Override
                    public void onNext(Re value) {
                        base.WData(value);
                        base.setHidoAnimation();
                    }
                });
    }



}
