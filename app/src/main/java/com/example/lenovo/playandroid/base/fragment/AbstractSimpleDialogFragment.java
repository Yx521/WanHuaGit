package com.example.lenovo.playandroid.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lenovo on 2019/3/4.
 */

public abstract class AbstractSimpleDialogFragment extends DialogFragment {

    //fragment上下文定义为全局方便调用
    public Context mContext;
    //定义当前的Activity
    public Activity mActivity;
    //ButterKnife对象
    private Unbinder mBind;
    public View mView;


    //当fragment和activity建立关联的时候调用，fragment的生命周期第一个执行onAttach
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //将fragment的上下文转换为Activity建立关联
        mActivity = (Activity) context;
        //赋值上下文
        this.mContext = context;
    }

    //为fragment创建视图调用，在onCreate之后
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //找到fragment绑定的布局
        mView = inflater.inflate(creatrLayoutId(), null, false);
        //绑定布局的方法
        viewCread(mView);
        return mView;//要显示的布局
    }

    //绑定布局
    public void viewCread(View view) {

    }

    //初始化布局
    protected abstract int creatrLayoutId();

    //onViewCreated在onCreateView执行完后立即执行。 onCreateView返回的就是fragment要显示的view
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //找控件。this为把id控件找到当前的位置。view为布局
        mBind = ButterKnife.bind(this, view);
        //初始化数据的方法
        initData();
    }


    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            //防止连续点击add多个fragment
            manager.beginTransaction().remove(this).commit();
            super.show(manager, tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //初始化数据
    protected abstract void initData();


    //当与fragment关联的视图被移除的时候调用
    @Override
    public void onDestroyView() {
        if (mBind != null) {//判断布局不等于空的时候
            mBind.unbind();//释放资源
        }
        super.onDestroyView();
    }
}
