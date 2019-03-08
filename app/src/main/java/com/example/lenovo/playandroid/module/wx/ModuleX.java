package com.example.lenovo.playandroid.module.wx;

import com.example.lenovo.playandroid.base.module.HttpFinishCallBack;
import com.example.lenovo.playandroid.beans.wx.Batree;
import com.example.lenovo.playandroid.beans.wx.Data;
import com.example.lenovo.playandroid.beans.wx.HttpResult;
import com.example.lenovo.playandroid.beans.wx.Re;
import com.example.lenovo.playandroid.beans.yx.Collect;
import com.example.lenovo.playandroid.beans.yx.ProjectClassify;
import com.example.lenovo.playandroid.beans.yx.ProjectClassifyData;
import com.example.lenovo.playandroid.http.BaseObserver;
import com.example.lenovo.playandroid.http.HttpManager;
import com.example.lenovo.playandroid.http.RxUtils;
import com.example.lenovo.playandroid.module.yx.YxModle;

import java.util.HashMap;
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



    public void  WgetData(final WxBase base,Object o) {
        Map<String, Object> map = (Map<String, Object>) o;
        String biao = (String) map.get("biao");
        if("one".equals(biao)){
            int page = (int) map.get("page");
            int cid = (int) map.get("cid");
            base.setAnimation();
            HttpManager.getInstance().getServer().getYan(page,cid)
                    .compose(RxUtils.<Re>rxOBserableSchedulerHelper())
                    .subscribe(new BaseObserver<Re>(base) {
                        @Override
                        public void onNext(Re value) {
                            Map<String,Object> map1 = new HashMap<>();
                            map1.put("biao","one");
                            map1.put("va",value);
                            base.WData(map1);
                            base.setHidoAnimation();
                        }
                    });
        }else if("shou".equals(biao)){
            base.setAnimation();
            //实体类
            /*String a = (String) map.get("a");
            String l = (String) map.get("l");
            String t = (String) map.get("t");*/
            int id = (int) map.get("id");
            HttpManager.getInstance().getLoginServer().getCollect(id)
                    .compose(RxUtils.<Collect>rxOBserableSchedulerHelper())
                    .subscribe(new BaseObserver<Collect>(base) {
                        @Override
                        public void onNext(Collect value) {
                            Map<String,Object> map1 = new HashMap<>();
                            map1.put("va",value);
                            map1.put("biao","shou");
                            base.WData(map1);
                            base.setHidoAnimation();
                        }
                    });
        }else if("shan".equals(biao)){
            base.setAnimation();
            //实体类
            int id = (int) map.get("id");
            int originId = (int) map.get("originId");
            HttpManager.getInstance().getLoginServer().getCancelCollect(id,originId)
                    .compose(RxUtils.<HttpResult>rxOBserableSchedulerHelper())
                    .subscribe(new BaseObserver<HttpResult>(base) {
                        @Override
                        public void onNext(HttpResult value) {
                            Map<String,Object> map1 = new HashMap<>();
                            map1.put("va",value);
                            map1.put("biao","shan");
                            base.WData(map1);
                            base.setHidoAnimation();
                        }
                    });
        }else if("shou1".equals(biao)){
            base.setAnimation();
            //实体类
            HttpManager.getInstance().getLoginServer().getData()
                    .compose(RxUtils.<Data>rxOBserableSchedulerHelper())
                    .subscribe(new BaseObserver<Data>(base) {
                        @Override
                        public void onNext(Data value) {
                            Map<String,Object> map1 = new HashMap<>();
                            map1.put("va",value);
                            map1.put("biao","shou1");
                            base.WData(map1);
                            base.setHidoAnimation();
                        }
                    });
        }

    }



}
