package com.example.lenovo.playandroid.module.yyj;

import android.util.Log;

import com.example.lenovo.playandroid.base.module.HttpFinishCallBack;
import com.example.lenovo.playandroid.beans.yyj.Bean;
import com.example.lenovo.playandroid.beans.yyj.Fuyong;
import com.example.lenovo.playandroid.beans.yyj.sousuo;
import com.example.lenovo.playandroid.http.BaseObserver;
import com.example.lenovo.playandroid.http.HttpManager;
import com.example.lenovo.playandroid.http.RxUtils;


public class Main_Fu {
public interface FuCallbak<V> extends HttpFinishCallBack {
    void setFuOb(V o);
    void setBean(V o);
    //void setSou(V o);
}
    public void getFuyong(final FuCallbak fuCallbak, Object obj){
    fuCallbak.setAnimation();
        HttpManager.getInstance().getServer().getFuyong()
                .compose(RxUtils.<Fuyong>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<Fuyong>(fuCallbak) {
                    @Override
                    public void onNext(Fuyong fuyong) {
                        //Log.e("fuyong",fuyong.getData().size()+"");
                        fuCallbak.setFuOb(fuyong);
                        fuCallbak.setHidoAnimation();
                    }
                });
    }
    //子条目
    public void getBe(final FuCallbak fuCallbak,String num,String page){
        fuCallbak.setAnimation();

        HttpManager.getInstance().getServer().getBean(num,page)
                .compose(RxUtils.<Bean>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<Bean>(fuCallbak) {
                    @Override
                    public void onNext(Bean bean) {
                        //Log.e("fuyong",bean.getData()+"");
                        fuCallbak.setBean(bean);
                        fuCallbak.setHidoAnimation();
                    }
                });


    }
//    public void getsoo(final FuCallbak fuCallbak, Object obj,String num,String page){
//        fuCallbak.setAnimation();
//        HttpManager.getInstance().getServer().getShousuo(num,page)
//                .compose(RxUtils.<sousuo>rxOBserableSchedulerHelper())
//                .subscribe(new BaseObserver<sousuo>(fuCallbak) {
//                    @Override
//                    public void onNext(sousuo fuyong) {
//                        Log.e("fuyong",fuyong.getData()+"");
//                        fuCallbak.setSou(fuyong);
//                        fuCallbak.setHidoAnimation();
//                    }
//                });
//
//
//    }
}
