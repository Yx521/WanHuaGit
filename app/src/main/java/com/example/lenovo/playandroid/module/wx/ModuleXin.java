package com.example.lenovo.playandroid.module.wx;

import android.util.Log;

import com.example.lenovo.playandroid.base.module.HttpFinishCallBack;
import com.example.lenovo.playandroid.beans.wx.Data;
import com.example.lenovo.playandroid.beans.wx.HttpResult;
import com.example.lenovo.playandroid.beans.yx.Collect;
import com.example.lenovo.playandroid.http.BaseObserver;
import com.example.lenovo.playandroid.http.HttpManager;
import com.example.lenovo.playandroid.http.RxUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ASUS on 2019/3/7.
 */

public class ModuleXin {

    //接口继承回调接口
    public interface FishCallBase<V> extends HttpFinishCallBack {
        //向P层传输数据
        void wxData(V data);
        void Cancel(V data);
    }

    public void wxgetData(Object obj, final FishCallBase fish) {
        Map<String, Object> map = (Map<String, Object>) obj;
        String biao = (String) map.get("biao");
        Log.i("yangxu", "wxgetData: "+biao);
        if("shou".equals(biao)){
            fish.setAnimation();
            //实体类
            /*String a = (String) map.get("a");
            String l = (String) map.get("l");
            String t = (String) map.get("t");*/
            int id = (int) map.get("id");
            HttpManager.getInstance().getLoginServer().getCollect(id)
                    .compose(RxUtils.<Collect>rxOBserableSchedulerHelper())
                    .subscribe(new BaseObserver<Collect>(fish) {
                        @Override
                        public void onNext(Collect value) {
                            Map<String,Object> map1 = new HashMap<>();
                            map1.put("va",value);
                            map1.put("biao","shou");
                            fish.wxData(map1);
                            fish.setHidoAnimation();
                        }
                    });
        }else if("shan".equals(biao)){
            fish.setAnimation();
            //实体类
            int id = (int) map.get("id");
            int originId = (int) map.get("originId");
            HttpManager.getInstance().getLoginServer().getCancelCollect(id,originId)
                    .compose(RxUtils.<HttpResult>rxOBserableSchedulerHelper())
                    .subscribe(new BaseObserver<HttpResult>(fish) {
                        @Override
                        public void onNext(HttpResult value) {
                            Map<String,Object> map1 = new HashMap<>();
                            map1.put("va",value);
                            map1.put("biao","shan");
                            fish.wxData(map1);
                            fish.setHidoAnimation();
                        }
                    });
        }else if("shou1".equals(biao)){
            fish.setAnimation();
            //实体类
            HttpManager.getInstance().getLoginServer().getData()
                    .compose(RxUtils.<Data>rxOBserableSchedulerHelper())
                    .subscribe(new BaseObserver<Data>(fish) {
                        @Override
                        public void onNext(Data value) {
                            Map<String,Object> map1 = new HashMap<>();
                            map1.put("va",value);
                            map1.put("biao","shou1");
                            fish.wxData(map1);
                            fish.setHidoAnimation();
                        }
                    });
        }


    }
    public void CancelData(Object obj, final FishCallBase fish,int id) {

        fish.setAnimation();



        HttpManager.getInstance().getLoginServer().getCollect(id)
                .compose(RxUtils.<Collect>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<Collect>(fish) {
                    @Override
                    public void onNext(Collect value) {
                        fish.Cancel(value);
                        fish.setHidoAnimation();
                    }
                });
    }
}
