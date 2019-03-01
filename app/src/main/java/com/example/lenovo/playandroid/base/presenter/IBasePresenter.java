package com.example.lenovo.playandroid.base.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by lenovo on 2019/2/28.
 */

public class IBasePresenter<V> implements BasePresenter<V> {
    //弱引用
    private WeakReference<V> mWeakReference;
    //View对象，用于绑定
    public V mView;

    //绑定View
    @Override
    public void attachView(V v) {
        //创建弱引用对象，并将View放入包裹
        mWeakReference = new WeakReference<V>(v);
        //得到弱引用包裹的View对象
        mView = mWeakReference.get();
    }

    @Override
    public void detachView() {
        if (mWeakReference != null && mWeakReference.get() != null) {
            //清除弱引用里的包裹内容
            mWeakReference.clear();
            //赋值弱引用为空
            mWeakReference = null;
        }
    }
}
