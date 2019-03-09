package com.example.lenovo.playandroid.http;


import com.example.lenovo.playandroid.beans.wlg.NaviBean;
import com.example.lenovo.playandroid.beans.wx.Batree;
import com.example.lenovo.playandroid.beans.wx.Re;
import com.example.lenovo.playandroid.beans.TopSearchData;
import com.example.lenovo.playandroid.beans.yx.Collect;
import com.example.lenovo.playandroid.beans.yx.ProjectClassify;
import com.example.lenovo.playandroid.beans.yx.ProjectClassifyData;
import com.example.lenovo.playandroid.beans.yx.SearchList;
import com.example.lenovo.playandroid.beans.yx.UsefulSiteData;
import com.example.lenovo.playandroid.beans.yyj.Bean;
import com.example.lenovo.playandroid.beans.yyj.Fuyong;
import com.example.lenovo.playandroid.beans.yyj.sousuo;
import com.example.lenovo.playandroid.beans.zl.BannerBean;
import com.example.lenovo.playandroid.beans.zl.FeedArticleListData;
import com.example.lenovo.playandroid.beans.zl.LoginData;

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

    //导航数据
    @GET("navi/json")
    Observable<NaviBean> getNaviBean();

    /**
    *
    *  知识体系
    * http://www.wanandroid.com/article/list/0/json?cid=60
     */

    @GET("tree/json")
    Observable<Batree> getBatree();

    @GET("article/list/{page}/json")
    Observable<Re> getYan(@Path("page") int page, @Query("cid") int cid);

    //article/list/0/json?cid=60
    @GET("friend/json")
    Observable<UsefulSiteData> getUsefulSiteData();

    @GET("hotkey/json")
    Observable<TopSearchData> getTopSearchData();

    @POST("article/query/{page}/json")
    @FormUrlEncoded
    Observable<SearchList> getSearchList(@Path("page") int page, @Field("k") String k);

    //yyj
//复用顶部接口
//http://wanandroid.com/wxarticle/chapters/json
    @GET("wxarticle/chapters/json")
    Observable<Fuyong> getFuyong();

    //* 获取当前公众号某页的数据
//     * http://wanandroid.com/wxarticle/list/405/1/json
    @GET("wxarticle/list/{num}/{page}/json")
    Observable<Bean> getBean(@Path("num") String num, @Path("page") String page);
    //
    //搜索
    //http://wanandroid.com/wxarticle/list/419/2/json?k=Java

    @GET("wxarticle/list/{num}/{page}/json")
    Observable<sousuo> getShousuo(@Path("num") String num, @Path("page") String page);

    @POST("article/query/{page}/json")
    @FormUrlEncoded
    Observable<Bean> getSearchListThree(@Path("page") int page, @Field("k") String k);

    @POST("lg/Collect/{id}/json")
    Observable<Collect> getCollect(@Path("id") int id);

    @POST("user/login")
    @FormUrlEncoded
    Observable<LoginData> getLoginData(@Field("username") String username, @Field("password") String password);

    @POST("user/register")
    @FormUrlEncoded
    Observable<LoginData> getRegisterData(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

    /**
     * 退出登录
     * http://www.wanandroid.com/user/logout/json
     *
     * @return 登陆数据
     */
    @GET("user/logout/json")
    Observable<LoginData> logout();

}
