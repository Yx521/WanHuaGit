package com.example.lenovo.playandroid.activitys.wx;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.adapter.wx.Knadapter;
import com.example.lenovo.playandroid.base.activity.BaseActivity;
import com.example.lenovo.playandroid.beans.wx.Batree;
import com.example.lenovo.playandroid.beans.wx.Re;
import com.example.lenovo.playandroid.fragments.wx.ReFragment;
import com.example.lenovo.playandroid.presenter.wx.PresenterX;
import com.example.lenovo.playandroid.view.yx.IView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class Multiplexing extends BaseActivity<IView, PresenterX<IView>> implements IView{

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.mult_img)
    ImageView multImg;
    @BindView(R.id.mult_text)
    TextView multText;

    private List<Batree.DataBean.ChildrenBean> shujulist=new ArrayList<>();
    private List<String> mStringList = new ArrayList<>();
    private List<Fragment> mFragmentList = new ArrayList<>();
    private Knadapter knadapter;

    @Override
    public void show(Object o) {
        Re re = (Re) o;
        Log.i("baga",re.getData().getDatas().get(0).getChapterName());

        List<Batree.DataBean.ChildrenBean> list = (List<Batree.DataBean.ChildrenBean>) getIntent().getSerializableExtra("list");
          shujulist.addAll(list);

        for (int i = 0; i < shujulist.size(); i++) {
            mStringList.add(shujulist.get(i).getName());
            mFragmentList.add(ReFragment.fuyong(shujulist.get(i).getId()));
        }


        knadapter = new Knadapter(getSupportFragmentManager(),mStringList,mFragmentList);
        vp.setAdapter(knadapter);
        tab.setupWithViewPager(vp);

       multText.setText(getIntent().getStringExtra("string"));
    }

    @Override
    public void showError(String error) {

    }


    @Override
    protected void initEventAndData() {
        mPresenter.WDataP(0,60);

    }

    @Override
    protected int creatrLayoutId() {
        return R.layout.activity_multiplexing;
    }

    @OnClick(R.id.mult_img)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected PresenterX<IView> createPresenter() {
        return new PresenterX<>();
    }
}
