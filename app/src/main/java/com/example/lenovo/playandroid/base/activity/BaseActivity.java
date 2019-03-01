package com.example.lenovo.playandroid.base.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.base.presenter.BasePresenter;
import com.example.lenovo.playandroid.base.view.BaseView;


/**
 * Created by lenovo on 2019/2/28.
 */

public abstract class BaseActivity<V, P extends BasePresenter<V>> extends SimpleActivity implements BaseView {
    //动画对象
    private LottieAnimationView mLoading_animation;
    //P层的对象
    public P mPresenter;
    private RelativeLayout mLoading_group;

    //绑定布局
    @Override
    public void viewCreated(View view) {
        super.viewCreated(view);//先执行SimpleActivity中的viewCreated的方法
        //找到动画的布局
        View inflate = View.inflate(this, R.layout.loading_view, (ViewGroup) view);
        //通过动画的ID找到控件
        mLoading_animation = inflate.findViewById(R.id.loading_animation);
        mLoading_group = inflate.findViewById(R.id.loading_group);
        //定义子类的P层对象
        mPresenter = createPresenter();
        if (mPresenter != null) {//判断P层对象不会null
            mPresenter.attachView((V) this);//绑定View
        }
    }

    //创建子类的P层对象
    protected abstract P createPresenter();

    //开启动画
    @Override
    public void showAnimation() {
        mLoading_group.setVisibility(View.VISIBLE);
        mLoading_animation.setAnimation("loading_bus.json");
        mLoading_animation.loop(true);
        mLoading_animation.playAnimation();
    }

    //关闭动画
    @Override
    public void hidoAnimation() {
        mLoading_group.setVisibility(View.GONE);
        mLoading_animation.cancelAnimation();
    }

    //工作线程的销毁和停止的时候执行
    @Override
    protected void onDestroy() {
        super.onDestroy();//先执行SimpleActivity中的onDestroy()
        if (mPresenter != null) {//判断P层对象不等于空
            mPresenter.detachView();//解绑View
            mPresenter = null;//将P层定义为空
        }
    }
}
