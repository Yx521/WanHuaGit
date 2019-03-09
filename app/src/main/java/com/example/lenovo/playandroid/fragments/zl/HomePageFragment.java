package com.example.lenovo.playandroid.fragments.zl;


import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.activitys.zl.LoginActivity;
import com.example.lenovo.playandroid.activitys.zl.SuperChapterNameActivity;
import com.example.lenovo.playandroid.activitys.zl.WebActivity;
import com.example.lenovo.playandroid.adapter.zl.ZlRecycAdapter;
import com.example.lenovo.playandroid.base.fragment.BaseFragment;
import com.example.lenovo.playandroid.beans.wx.Data;
import com.example.lenovo.playandroid.beans.wx.HttpResult;
import com.example.lenovo.playandroid.beans.yx.Collect;
import com.example.lenovo.playandroid.beans.zl.BannerBean;
import com.example.lenovo.playandroid.beans.zl.FeedArticleListData;
import com.example.lenovo.playandroid.dao.IsLikeDaoBean;
import com.example.lenovo.playandroid.dao.IsLikeManager;
import com.example.lenovo.playandroid.dao.LogDaoBean;
import com.example.lenovo.playandroid.dao.LoginManager;
import com.example.lenovo.playandroid.fragments.yx.ItemsFragment;
import com.example.lenovo.playandroid.presenter.zl.ZlPresenter;
import com.example.lenovo.playandroid.view.zl.ZlView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends BaseFragment<ZlView, ZlPresenter<ZlView>> implements ZlView {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    Unbinder unbinder1;
    private String TAG = "HomePageFragment";
    private int page = 0;
    private ZlRecycAdapter mZlRecycAdapter;
    private boolean nelsep;
    private String mUrl;
    private ImageView mIsLike;
    private int mId;
    private int mOriginId;


    @Override
    public void onStart() {
        super.onStart();
        // Log.e(TAG, "onStart: "+"shuxin");
        //   mZlRecycAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        //   Log.e(TAG, "onResume: " );
        mZlRecycAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Log.e(TAG, "showError: " + error);


    }

    @Override
    protected int creatrLayoutId() {
        return R.layout.fragment_about_us;
    }

    @Override
    protected void initData() {

        EventBus.getDefault().register(this);
        mPresenter.upwithBanner("");
        mPresenter.upwithItem(page);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());
        mZlRecycAdapter = new ZlRecycAdapter(new ArrayList<BannerBean.DataBean>(), new ArrayList<FeedArticleListData.DataBean.DatasBean>());
        mRecyclerview.setAdapter(mZlRecycAdapter);

        initRefreshAndLoadmore();
        //轮播图的点击事件
        mZlRecycAdapter.setOnBannerListener(new ZlRecycAdapter.OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("isShowlike", false);
                intent.putExtra("weburl", mZlRecycAdapter.mBannerData.get(position).getUrl());
                intent.putExtra("webtitle", mZlRecycAdapter.mBannerData.get(position).getTitle());
                startActivity(intent);

                Toast.makeText(mContext, "点击" + position, Toast.LENGTH_SHORT).show();
            }
        });
        //item点击事件
        mZlRecycAdapter.setOnClickListener(new ZlRecycAdapter.OnClickListener() {


            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void OnClick(View view, int position) {
                ActivityOptions mA = ActivityOptions.makeSceneTransitionAnimation(mActivity, view, getString(R.string.about_us));

                Intent intent = new Intent();
                intent.setClass(mContext, WebActivity.class);
                intent.putExtra("isShowlike", true);
                intent.putExtra("weburl", mZlRecycAdapter.mData.get(position).getLink());
                intent.putExtra("id", mZlRecycAdapter.mData.get(position).getId());
                intent.putExtra("author", mZlRecycAdapter.mData.get(position).getAuthor());
                intent.putExtra("webtitle", mZlRecycAdapter.mData.get(position).getTitle());
                //  intent.putExtra("",mZlRecycAdapter.mData.get(position))
                intent.putExtra("position", position);
                mActivity.startActivity(intent, mA.toBundle());
            }
        });
        mZlRecycAdapter.setJumpFive(new ZlRecycAdapter.jumpFive() {
            @Override
            public void setClick(View view) {
                Log.e("a", "a");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_group, new ItemsFragment(), "4").commit();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_group, new ItemsFragment(), "4").commit();

            }
        });
        mZlRecycAdapter.setData(new ZlRecycAdapter.data() {
            @Override
            public void data(String url, String title, String author, View view, int position) {
                mIsLike = view.findViewById(R.id.item_search_pager_like_iv);
                mUrl = url;
                Toast.makeText(mActivity, mUrl, Toast.LENGTH_SHORT).show();
                List<LogDaoBean> select = LoginManager.mMySqlHelper().select();
                List<IsLikeDaoBean> select1 = IsLikeManager.mMySqlHelper().select();
                if (select.get(0).getIsLogin() == true) {

                    for (int i = 0; i < select1.size(); i++) {
                        if (!select1.get(i).getUrl().equals(mZlRecycAdapter.mData.get(position).getLink())) {
                            nelsep = false;
                        } else {
                            nelsep = true;
                        }
                    }
                    if (nelsep == false) {

                        Log.e("cccc", "cccc");
                        Map<String, Object> map = new HashMap<>();
                        map.put("t", title);
                        map.put("a", author);
                        map.put("l", url);
                        mPresenter.Data(map);
                    } else if (nelsep == true) {
                        Log.e("ccaacc", "ccaacc");
                        mPresenter.shoucang();
                    }
                } else {
                    Intent intent = new Intent();
                    intent.setClass(mActivity, LoginActivity.class);
                    startActivity(intent);
                }

            }
        });

        mZlRecycAdapter.setOnTooTextAndTabText(new ZlRecycAdapter.onTooTextAndTabText() {

            @Override
            public void TooTextAndTabText(String tab, String Tootext, int chapterId) {

                Intent intent = new Intent();
                intent.setClass(mContext, SuperChapterNameActivity.class);
                intent.putExtra("tab", tab);
                intent.putExtra("too", Tootext);
                intent.putExtra("id", chapterId);
                startActivityForResult(intent, 10);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void zhiding(String positon) {
        if ("0".equals(positon)) {
            mRecyclerview.smoothScrollToPosition(0);
        }
    }

    //刷新或加载
    private void initRefreshAndLoadmore() {

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                mPresenter.upwithItem(page);


            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.upwithItem(page);

                //  mRefreshLayout.finishLoadMore(3000);
            }
        });
    }

    @Override
    protected ZlPresenter<ZlView> createPresenter() {
        return new ZlPresenter<>();
    }


    @Override
    public void BannerData(Object bannerdata) {
        BannerBean bannerBean = (BannerBean) bannerdata;
        mZlRecycAdapter.addBanner(bannerBean.getData());
    }

    @Override
    public void MainData(Object maindata) {
        //关闭刷新或加载视图
        if (maindata != null&&mRefreshLayout!=null) {
            if (mRefreshLayout.isEnableLoadMore()) {
                mRefreshLayout.finishLoadMore(100);
            }
            if (mRefreshLayout.isEnableRefresh()) {
                mRefreshLayout.finishRefresh(100);
            }


            FeedArticleListData feedArticleListData = (FeedArticleListData) maindata;
            mZlRecycAdapter.addData(feedArticleListData.getData().getDatas());

        }
    }

    @Override
    public void Login(Object logindata) {
        //
    }

    @Override
    public void Register(Object registerdata) {
        //
    }

    @Override
    public void setData(Object obj) {
        Collect collect = (Collect) obj;
        if (collect.getErrorCode() == 0) {
            nelsep = false;
            Toast.makeText(mActivity, "收藏成功", Toast.LENGTH_SHORT).show();
            IsLikeManager.mMySqlHelper().insert(new IsLikeDaoBean(null, mUrl));
            mIsLike.setImageResource(R.drawable.icon_like);
        } else {
            Toast.makeText(mActivity, "收藏失败", Toast.LENGTH_SHORT).show();
            mIsLike.setImageResource(R.mipmap.ic_toolbar_like_n);


        }
    }

    @Override
    public void setS(Data value) {
        if (value.getErrorCode() == 0) {
            for (int i = 0; i < value.getData().getDatas().size(); i++) {
                if (value.getData().getDatas().get(i).equals(mUrl)) {
                    mId = value.getData().getDatas().get(i).getId();
                    mOriginId = value.getData().getDatas().get(i).getOriginId();
                }
            }
        }
        mPresenter.shan(mId, mOriginId);
    }

    @Override
    public void shan(HttpResult value) {
        HttpResult collect = (HttpResult) value;
        //  Log.e("data", collect.getErrorCode() + "" + "____-");
        if (collect.getErrorCode() == 0) {
            Toast.makeText(mActivity, "取消收藏", Toast.LENGTH_SHORT).show();

            mIsLike.setImageResource(R.drawable.icon_like_article_not_selected);
            List<IsLikeDaoBean> select = IsLikeManager.mMySqlHelper().select();
            for (int i = 0; i < select.size(); i++) {
                IsLikeDaoBean isLikeDaoBean = select.get(i);
                if (isLikeDaoBean.getUrl().equals(mUrl)) {
                    isLikeDaoBean.setUrl(mUrl);
                    IsLikeManager.mMySqlHelper().delete(isLikeDaoBean);
                }

            }
        }
    }


}
