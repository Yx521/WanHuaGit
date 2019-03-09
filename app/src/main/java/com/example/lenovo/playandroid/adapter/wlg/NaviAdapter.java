package com.example.lenovo.playandroid.adapter.wlg;

import android.app.ActivityOptions;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.beans.wlg.NaviBean;
import com.example.lenovo.playandroid.utils.navi.CommonUtils;
import com.example.lenovo.playandroid.utils.navi.JudgeUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * WLG
 * 王轩逸！！
 * created:2019/3/3 20:55
 */
public class NaviAdapter extends BaseQuickAdapter<NaviBean.DataBean,NaviViewHolder> {
    private TagFlowLayout mTagFlowLayout;
    private TagAdapter<NaviBean.DataBean.ArticlesBean> mTagAdapter;

    public NaviAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(NaviViewHolder helper, final NaviBean.DataBean item) {
        if (!TextUtils.isEmpty(item.getName())) {
            helper.setText(R.id.tv, item.getName());

        }
        mTagFlowLayout = helper.getView(R.id.taglayout);
        final List<NaviBean.DataBean.ArticlesBean> articles = item.getArticles();
        mTagAdapter = new TagAdapter<NaviBean.DataBean.ArticlesBean>(articles) {
            @Override
            public View getView(FlowLayout parent, int position, NaviBean.DataBean.ArticlesBean articlesBean) {
                TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.flow_layout_tv,
                        mTagFlowLayout, false);
                if (item == null) {
                    return null;
                }
                String name = articlesBean.getTitle();

                tv.setText(name);
                Log.e(TAG, "getView: " + name);
                tv.setTextColor(CommonUtils.randomColor());
                mTagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public boolean onTagClick(View view, int position1, FlowLayout parent) {
                        startNavigationPager(view, position1, parent, articles);
                        return true;
                    }
                });
                return tv;
            }
        };
        mTagFlowLayout.setAdapter(mTagAdapter);

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void startNavigationPager(View view, int position, FlowLayout parent, List<NaviBean.DataBean.ArticlesBean> articles) {
        Toast.makeText(mContext,position+"",Toast.LENGTH_SHORT).show();
        ActivityOptions activityOptions = ActivityOptions
                .makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
        JudgeUtils.startArticleDetailActivity(parent.getContext(),
                activityOptions,
                articles.get(position).getId(),
                articles.get(position).getTitle(),
                articles.get(position).getLink(),
                articles.get(position).isCollect(),
                false,
                false);
    }
}
