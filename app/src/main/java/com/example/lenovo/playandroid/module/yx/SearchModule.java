package com.example.lenovo.playandroid.module.yx;

import com.example.lenovo.playandroid.base.module.HttpFinishCallBack;
import com.example.lenovo.playandroid.beans.TopSearchData;
import com.example.lenovo.playandroid.beans.yx.SearchList;
import com.example.lenovo.playandroid.http.BaseObserver;
import com.example.lenovo.playandroid.http.HttpManager;
import com.example.lenovo.playandroid.utils.RxUtils;

import java.util.Map;

/**
 * Created by lenovo on 2019/3/4.
 */

public class SearchModule {
    //接口继承回调接口
    public interface SearchCallback<V> extends HttpFinishCallBack {
        //向P层传输数据
        void setData(V data,String tag);
    }

    public void getData(final SearchCallback callback, Object obj) {
        Map<String,Object> map = (Map<String, Object>) obj;
        final String tag = (String) map.get("tag");
        switch (tag) {
            case "TopSearch":
                HttpManager.getInstance().getServer().getTopSearchData()
                        .compose(RxUtils.<TopSearchData>rxOBserableSchedulerHelper())
                        .subscribe(new BaseObserver<TopSearchData>(callback) {
                            @Override
                            public void onNext(TopSearchData value) {
                                callback.setData(value,tag);
                            }
                        });
                break;
            case "SearchList":
                int page = (int) map.get("page");
                String k = (String) map.get("k");
                HttpManager.getInstance().getServer().getSearchList(page,k)
                        .compose(RxUtils.<SearchList>rxOBserableSchedulerHelper())
                        .subscribe(new BaseObserver<SearchList>(callback) {
                            @Override
                            public void onNext(SearchList value) {
                                callback.setData(value,tag);
                            }
                        });
                break;
        }

    }
}
