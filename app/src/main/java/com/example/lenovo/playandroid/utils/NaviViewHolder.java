package com.example.lenovo.playandroid.utils;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lenovo.playandroid.R;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * WLG
 * 王轩逸！！
 * created:2019/3/1 9:41
 */
public class NaviViewHolder extends BaseViewHolder {
    @BindView(R.id.taglayout)
    TagFlowLayout taglayout;
    @BindView(R.id.tv)
    TextView tv;
    public NaviViewHolder(View view) {
        super(view);


        ButterKnife.bind(this, view);
        }

}
