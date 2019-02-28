package com.example.lenovo.playandroid.http;


import com.example.lenovo.playandroid.bean.zlbean.BannerBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lenovo on 2019/2/28.
 */

public interface ApiServer {

    @GET("banner/json")
    Observable<BannerBean> getBanner();

}
