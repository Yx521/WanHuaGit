package com.example.lenovo.playandroid.module.zl;

import android.util.Log;

import com.example.lenovo.playandroid.base.module.HttpFinishCallBack;
import com.example.lenovo.playandroid.beans.wx.Data;
import com.example.lenovo.playandroid.beans.wx.HttpResult;
import com.example.lenovo.playandroid.beans.yx.Collect;
import com.example.lenovo.playandroid.beans.zl.BannerBean;
import com.example.lenovo.playandroid.beans.zl.FeedArticleListData;
import com.example.lenovo.playandroid.beans.zl.LoginData;
import com.example.lenovo.playandroid.http.BaseObserver;
import com.example.lenovo.playandroid.http.HttpManager;
import com.example.lenovo.playandroid.http.RxUtils;
import com.example.lenovo.playandroid.presenter.zl.ZlPresenter;
import com.example.lenovo.playandroid.view.zl.ZlView;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.HTTP;

/**
 * Created by 18 on 2019/2/28.
 */

public class ZlModule {
    private boolean data = true;

    public void setData(Map<String, Object> map, final FinishData finishData) {
        finishData.setAnimation();
        //实体类
        String a = (String) map.get("a");
        String l = (String) map.get("l");
        String t = (String) map.get("t");

        HttpManager.getInstance().getLoginServer().getWaiCollect(t, a, l)
                .compose(RxUtils.<Collect>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<Collect>(finishData) {
                    @Override
                    public void onNext(Collect value) {
                        finishData.weidata(value);
                        finishData.setHidoAnimation();
                    }
                });
    }

    public void shouc(final FinishData finishData) {
        HttpManager.getInstance().getLoginServer().getData()
                .compose(RxUtils.<Data>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<Data>(finishData) {
                    @Override
                    public void onNext(Data value) {
                        finishData.zlSData(value);
                    }
                });

    }

    public void shan(int id, int originId, final FinishData finishData) {
        HttpManager.getInstance().getLoginServer().cancelCollectPageArticle(id, originId)
                .compose(RxUtils.<HttpResult>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<HttpResult>(finishData) {
                    @Override
                    public void onNext(HttpResult value) {
                        finishData.zlSHan(value);
                        finishData.setHidoAnimation();
                    }
                });

    }


    public interface FinishData<V> extends HttpFinishCallBack {
        void zlBannerData(Object data);

        void zlItemData(Object itemData);

        void zlLoginData(Object logindata);

        void zlRegister(Object registerdata);

        void weidata(Object obj);

        void zlSData(Data value);

        void zlSHan(HttpResult value);
    }

    public void zlshouc(final FinishData finishData) {
        HttpManager.getInstance().getLoginServer().getData()
                .compose(RxUtils.<Data>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<Data>(finishData) {
                    @Override
                    public void onNext(Data value) {
                        finishData.zlLoginData(value);
                    }
                });

    }

    public void zlunCollect(int id, int originId, final FinishData finishData) {
        finishData.setAnimation();
        HttpManager.getInstance().getLoginServer().getCancelCollect(id, originId)
                .compose(RxUtils.<HttpResult>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<HttpResult>(finishData) {
                    @Override
                    public void onNext(HttpResult value) {
                        finishData.zlBannerData(value);
                        finishData.setHidoAnimation();
                    }
                });
    }

    public void zlIsLiske(Map<String, Object> map, final FinishData finishData) {
        finishData.setAnimation();
        //实体类
        String a = (String) map.get("a");
        String l = (String) map.get("l");
        String t = (String) map.get("t");

        HttpManager.getInstance().getLoginServer().getWaiCollect(t, a, l)
                .compose(RxUtils.<Collect>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<Collect>(finishData) {
                    @Override
                    public void onNext(Collect value) {
                        finishData.zlItemData(value);
                        finishData.setHidoAnimation();
                    }
                });
    }


    public void zlLogin(String username, String password, final FinishData finishData) {
        finishData.setAnimation();
        HttpManager.getInstance().getLoginServer().getLoginData(username, password)
                .compose(RxUtils.<LoginData>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginData>(finishData) {
                    @Override
                    public void onNext(LoginData value) {
                        Log.e("DATA", value.getErrorCode() + "");
                        finishData.zlLoginData(value);
                        finishData.setHidoAnimation();

                    }
                });
    }

    public void zlRegister(String username, String password, String repassword, final FinishData finishData) {
        HttpManager.getInstance().getLoginServer().getRegisterData(username, password, repassword)
                .compose(RxUtils.<LoginData>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginData>(finishData) {
                    @Override
                    public void onNext(LoginData value) {
                        finishData.zlRegister(value);
                    }
                });

    }

    /**
     * 首页Item
     *
     * @param page
     */
    public void zlgetItem(Object page, final FinishData finishData) {
        int pa = (int) page;
        if (data == true) {
            finishData.setAnimation();
            data = false;
        }
        HttpManager.getInstance().getServer().getFeedArticleList(pa)
                .compose(RxUtils.<FeedArticleListData>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<FeedArticleListData>(finishData) {
                    @Override
                    public void onNext(FeedArticleListData value) {
                        finishData.zlItemData(value);
                        finishData.setHidoAnimation();
                    }
                });
    }


    /**
     * Banner数据
     *
     * @param obj
     * @param finish
     */
    public void zlgetData(Object obj, final FinishData finish) {
        finish.setAnimation();

        Observable<BannerBean> banner = HttpManager.getInstance().getServer().getBanner();
        banner.compose(RxUtils.<BannerBean>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<BannerBean>(finish) {
                    @Override
                    public void onNext(BannerBean value) {
                        finish.zlBannerData(value);
                        finish.setHidoAnimation();
                    }
                });
    }

    public void zlgetsingle(int id, int page, final FinishData finishData) {
        finishData.setHidoAnimation();
        HttpManager.getInstance().getServer().getIdFeedArticleList(id, page)
                .compose(RxUtils.<FeedArticleListData>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<FeedArticleListData>(finishData) {
                    @Override
                    public void onNext(FeedArticleListData value) {
                        finishData.zlItemData(value);
                        finishData.setHidoAnimation();
                    }
                });
    }
}
