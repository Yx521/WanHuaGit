package com.example.lenovo.playandroid.http;


import com.example.lenovo.playandroid.base.module.HttpFinishCallBack;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by lenovo on 2019/2/28.
 */

public abstract class BaseObserver<T> implements Observer<T> {
    //回调结果处理
    private HttpFinishCallBack mHttpFinishCallBack;

    public BaseObserver(HttpFinishCallBack httpFinishCallBack) {
        mHttpFinishCallBack = httpFinishCallBack;
    }

    //管理内存网络请求
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    //当Observable被订阅(subscribe) OnSubscribe接口的call方法会被执行
    @Override
    public void onSubscribe(Disposable d) {
        mCompositeDisposable.add(d);
    }

    //网络请求错误的时候
    @Override
    public void onError(Throwable e) {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();//如果内存里有数据就清除
        }
        if (mHttpFinishCallBack != null) {//接口回调有值的时候
            if (e instanceof HttpException) {//请求失败的时候
                mHttpFinishCallBack.setError(e.getMessage());//返回失败异常
            }
        }
    }

    //请求完成的时候
    @Override
    public void onComplete() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();//如果内存里有数据就清除
        }
    }
}
