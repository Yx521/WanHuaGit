package com.example.lenovo.playandroid.http;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lenovo on 2019/2/28.
 */

public interface ApiServer {

     //主页Banner
    @GET("banner/json")
    Observable<BannerBean> getBanner();



}
