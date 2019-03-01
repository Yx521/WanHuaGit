package com.example.lenovo.playandroid.base.presenter;

/**
 * Created by lenovo on 2019/2/28.
 */

public interface BasePresenter<V> {
    //绑定View
    void attachView(V v);


    //解绑View
    void detachView();
}
