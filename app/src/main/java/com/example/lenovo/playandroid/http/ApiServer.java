package com.example.lenovo.playandroid.http;


import com.example.lenovo.playandroid.beans.TopSearchData;
import com.example.lenovo.playandroid.beans.yx.ProjectClassify;
import com.example.lenovo.playandroid.beans.yx.ProjectClassifyData;
import com.example.lenovo.playandroid.beans.yx.SearchList;
import com.example.lenovo.playandroid.beans.yx.UsefulSiteData;
import com.example.lenovo.playandroid.beans.zl.BannerBean;
import com.example.lenovo.playandroid.beans.zl.FeedArticleListData;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lenovo on 2019/2/28.
 */

public interface ApiServer {

    //主页Banner
    @GET("banner/json")
    Observable<BannerBean> getBanner();

    //主页item
    @GET("article/list/{num}/json")
    Observable<FeedArticleListData> getFeedArticleList(@Path("num") int num);



    @GET("wxarticle/list/{id}/{num}/json")
    Observable<FeedArticleListData> getIdFeedArticleList(@Path("id") int id, @Path("num") int num);

    /**
     * 项目分类
     *
     * @return
     */
    @GET("project/tree/json")
    Observable<ProjectClassify> getProjectClassifyData();

    @GET("project/list/{page}/json")
    Observable<ProjectClassifyData> getProjectListData(@Path("page") int page, @Query("cid") int cid);


    @GET("friend/json")
    Observable<UsefulSiteData> getUsefulSiteData();

    @GET("hotkey/json")
    Observable<TopSearchData> getTopSearchData();

    @POST("article/query/{page}/json")
    @FormUrlEncoded
    Observable<SearchList> getSearchList(@Path("page") int page, @Field("k") String k);
}
