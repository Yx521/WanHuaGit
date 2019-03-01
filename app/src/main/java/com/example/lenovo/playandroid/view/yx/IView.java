package com.example.lenovo.playandroid.view.yx;


import com.example.lenovo.playandroid.base.view.BaseView;

/**
 * Created by lenovo on 2019/2/28.
 */

public interface IView<V> extends BaseView {
    //显示的数据
    void show(V v);
}
