package com.example.lenovo.playandroid.module.zl;

import android.util.Log;

import com.example.lenovo.playandroid.base.module.HttpFinishCallBack;
import com.example.lenovo.playandroid.beans.zl.BannerBean;
import com.example.lenovo.playandroid.beans.zl.FeedArticleListData;
import com.example.lenovo.playandroid.beans.zl.LoginData;
import com.example.lenovo.playandroid.http.BaseObserver;
import com.example.lenovo.playandroid.http.HttpManager;
import com.example.lenovo.playandroid.http.RxUtils;
import com.example.lenovo.playandroid.presenter.zl.ZlPresenter;
import com.example.lenovo.playandroid.view.zl.ZlView;

import io.reactivex.Observable;
import retrofit2.http.HTTP;

/**
 * Created by 18 on 2019/2/28.
 */

public class ZlModule {
    private boolean data = true;


    public interface FinishData<V> extends HttpFinishCallBack {
        void zlBannerData(Object data);

        void zlItemData(Object itemData);

        void zlLoginData(Object logindata);

        void zlRegister(Object registerdata);
    }

    public void zlLogin(String username, String password, final FinishData finishData) {
        HttpManager.getInstance().getLoginServer().getLoginData(username, password)
                .compose(RxUtils.<LoginData>rxOBserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginData>(finishData) {
                    @Override
                    public void onNext(LoginData value) {
                        finishData.zlLoginData(value);

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
