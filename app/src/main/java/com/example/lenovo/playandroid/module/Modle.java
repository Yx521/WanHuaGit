package com.example.lenovo.playandroid.module;


import com.example.lenovo.playandroid.base.basemodule.HttpFinishCallBack;

/**
 * Created by lenovo on 2019/2/28.
 */

public class Modle {
    //接口继承回调接口
    public interface Callback<V> extends HttpFinishCallBack {
        //向P层传输数据
        void setData(V data);
    }

    public void getData(final Callback Callback, Object obj) {

    }
}
