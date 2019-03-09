package com.example.lenovo.playandroid.module.yyj;

import android.util.Log;

import com.example.lenovo.playandroid.base.module.HttpFinishCallBack;
import com.example.lenovo.playandroid.beans.yyj.Bean;
import com.example.lenovo.playandroid.beans.yyj.Fuyong;
import com.example.lenovo.playandroid.beans.yyj.sousuo;
import com.example.lenovo.playandroid.http.BaseObserver;
import com.example.lenovo.playandroid.http.HttpManager;
import com.example.lenovo.playandroid.http.RxUtils;

public class DuoM {
    public interface HttpQing extends HttpFinishCallBack {
        void setFuyongl(Bean fuyongl);

        void setSoul(Bean soul);
    }

    //复用
    public void getFuyongl(final DuoM.HttpQing httpQing, String num, String page) {
        httpQing.setAnimation();
        HttpManager.getInstance().getServer().getBean(num, page)
                .compose(RxUtils.<Bean>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<Bean>(httpQing) {
                    @Override
                    public void onNext(Bean fuyong) {
                        //Log.e("fuyong", fuyong.getData().getDatas().size() + "");
                        httpQing.setFuyongl(fuyong);
                        httpQing.setHidoAnimation();
                    }
                });
    }

    //搜索
    public void getsousuol(final DuoM.HttpQing fuCallbak, Object obj, String num, int page) {
        fuCallbak.setAnimation();
        HttpManager.getInstance().getServer().getSearchListThree(page, num)
                .compose(RxUtils.<Bean>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<Bean>(fuCallbak) {
                    @Override
                    public void onNext(Bean value) {
                        //Log.e("a",value.getData().getDatas().get(0).getChapterName());
                        fuCallbak.setSoul(value);
                    }
                });
    }
}
