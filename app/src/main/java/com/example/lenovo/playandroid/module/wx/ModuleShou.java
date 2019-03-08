package com.example.lenovo.playandroid.module.wx;

import com.example.lenovo.playandroid.base.module.HttpFinishCallBack;
import com.example.lenovo.playandroid.beans.wx.Data;
import com.example.lenovo.playandroid.beans.wx.HttpResult;
import com.example.lenovo.playandroid.http.BaseObserver;
import com.example.lenovo.playandroid.http.HttpManager;
import com.example.lenovo.playandroid.http.RxUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ASUS on 2019/3/8.
 */

public class ModuleShou {

    //接口继承回调接口
    public interface FishCallBase<V> extends HttpFinishCallBack {
        //向P层传输数据
        void wxData(V data);
    }

    public void wxgetData(Object obj, final FishCallBase fish) {
        Map<String, Object> map = (Map<String, Object>) obj;
        String biao = (String) map.get("biao");
        if("shou".equals(biao)){
            fish.setAnimation();
            //实体类
            HttpManager.getInstance().getLoginServer().getData()
                    .compose(RxUtils.<Data>rxOBserableSchedulerHelper())
                    .subscribe(new BaseObserver<Data>(fish) {
                        @Override
                        public void onNext(Data value) {
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
            /*int id = (int) map.get("id");
            HttpManager.getInstance().getLoginServer().getCancelCollect(id)
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
                    });*/
        }

    }
}
