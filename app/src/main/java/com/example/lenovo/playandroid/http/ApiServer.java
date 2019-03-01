package com.example.lenovo.playandroid.http;


import com.example.lenovo.playandroid.beans.yx.ProjectClassify;
import com.example.lenovo.playandroid.beans.yx.ProjectClassifyData;
import com.example.lenovo.playandroid.beans.zl.BannerBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lenovo on 2019/2/28.
 */

public interface ApiServer {

     //主页Banner
    @GET("banner/json")
    Observable<BannerBean> getBanner();

    /**
     * 项目分类
     *
     * @return
     */
    @GET("project/tree/json")
    Observable<ProjectClassify> getProjectClassifyData();

    @GET("project/list/{page}/json")
    Observable<ProjectClassifyData> getProjectListData(@Path("page") int page, @Query("cid") int cid);
}
