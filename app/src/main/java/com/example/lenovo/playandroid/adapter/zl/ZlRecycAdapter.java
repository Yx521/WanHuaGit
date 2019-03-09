package com.example.lenovo.playandroid.adapter.zl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.beans.zl.BannerBean;
import com.example.lenovo.playandroid.beans.zl.FeedArticleListData;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 18 on 2019/2/28.
 */

public class ZlRecycAdapter extends RecyclerView.Adapter {

    private Context mContext;
    public ArrayList<FeedArticleListData.DataBean.DatasBean> mData;


    public List<BannerBean.DataBean> mBannerData;
    private ArrayList<String> bannerImage = new ArrayList<>();
    private ArrayList<String> bannerTitle = new ArrayList<>();
    private OnClickListener mListener;
    private OnBannerListener mBannerListener;
    private onTooTextAndTabText mOnTooTextAndTabText;

    public ZlRecycAdapter(ArrayList<BannerBean.DataBean> dataBeans, ArrayList<FeedArticleListData.DataBean.DatasBean> beans) {
        this.mBannerData = dataBeans;
        this.mData = beans;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        if (viewType == 0) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.homepage_banner_item, parent, false);
            return new HeadViewHolder(inflate);
        } else {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_search_pager, parent, false);
            return new ViewHolder(inflate);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == 0) {
            HeadViewHolder headViewHolder = (HeadViewHolder) holder;
            //设置banner样式
            headViewHolder.mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
            //设置图片加载器
            headViewHolder.mBanner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            headViewHolder.mBanner.setImages(bannerImage);
            //设置banner动画效果
            headViewHolder.mBanner.setBannerAnimation(Transformer.DepthPage);
            //设置标题集合（当banner样式有显示title时）
            headViewHolder.mBanner.setBannerTitles(bannerTitle);
            //设置自动轮播，默认为true
            headViewHolder.mBanner.isAutoPlay(true);
            //设置轮播时间
            headViewHolder.mBanner.setDelayTime(bannerImage.size() * 400);
            //设置指示器位置（当banner模式中有指示器时）
            headViewHolder.mBanner.setIndicatorGravity(BannerConfig.CENTER);
            //banner设置方法全部调用完毕时最后调用
            headViewHolder.mBanner.start();

            headViewHolder.mBanner.setOnBannerListener(new com.youth.banner.listener.OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    mBannerListener.OnBannerClick(position);
                }
            });


        } else {
            final ViewHolder viewHolder = (ViewHolder) holder;
            if (mData.get(position).getSuperChapterName().contains("开源项目") && mData.get(position).getNiceDate().contentEquals("小时前")) {
                viewHolder.mItemSearchPagerTagRedTv.setVisibility(View.VISIBLE);
                viewHolder.mItemSearchPagerTagRedTv.setText(R.string.project);
                viewHolder.mItemSearchPagerTagGreenTv.setVisibility(View.VISIBLE);
                viewHolder.mItemSearchPagerTagGreenTv.setText("新");
            } else if (mData.get(position).getSuperChapterName().contains("开源项目")) {
                viewHolder.mItemSearchPagerTagRedTv.setVisibility(View.VISIBLE);
                viewHolder.mItemSearchPagerTagRedTv.setText(R.string.project);
            } else if (mData.get(position).getNiceDate().contains("小时前")) {
                viewHolder.mItemSearchPagerTagGreenTv.setVisibility(View.VISIBLE);
                viewHolder.mItemSearchPagerTagGreenTv.setText("新");
            }


            viewHolder.mItemSearchPagerTitle.setText(mData.get(position).getTitle());
            viewHolder.mItemSearchPagerAuthor.setText(mData.get(position).getAuthor());
            viewHolder.mItemSearchPagerChapterName.setText(mData.get(position).getSuperChapterName() + " / " + mData.get(position).getChapterName());
            viewHolder.mItemSearchPagerNiceDate.setText(mData.get(position).getNiceDate());

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.OnClick(viewHolder.itemView, position);
                    }
                }
            });

            viewHolder.mItemSearchPagerChapterName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnTooTextAndTabText.TooTextAndTabText(mData.get(position).getChapterName(), mData.get(position).getSuperChapterName(),mData.get(position).getChapterId());
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public void addData(List<FeedArticleListData.DataBean.DatasBean> datas) {
        mData.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addBanner(List<BannerBean.DataBean> data) {
        mBannerData.addAll(data);
        for (int i = 0; i < data.size(); i++) {
            bannerImage.add(data.get(i).getImagePath());
            bannerTitle.add(data.get(i).getTitle());
        }

    }

    static class HeadViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        Banner mBanner;

        HeadViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_search_pager_author)
        TextView mItemSearchPagerAuthor;
        @BindView(R.id.item_search_pager_chapterName)
        TextView mItemSearchPagerChapterName;
        @BindView(R.id.item_search_pager_title)
        TextView mItemSearchPagerTitle;
        @BindView(R.id.item_search_pager_tag_red_tv)
        TextView mItemSearchPagerTagRedTv;
        @BindView(R.id.item_search_pager_tag_green_tv)
        TextView mItemSearchPagerTagGreenTv;
        @BindView(R.id.item_search_tag_group)
        LinearLayout mItemSearchTagGroup;
        @BindView(R.id.item_search_pager_like_iv)
        ImageView mItemSearchPagerLikeIv;
        @BindView(R.id.item_search_pager_niceDate)
        TextView mItemSearchPagerNiceDate;
        @BindView(R.id.item_search_pager_group)
        CardView mItemSearchPagerGroup;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {


            Glide.with(context).load(path).apply(new RequestOptions().placeholder(R.mipmap.icon_wan_android)
                    .error(R.mipmap.icon_author)).into(imageView);
        }
    }


    public interface OnBannerListener {
        void OnBannerClick(int position);
    }

    public void setOnBannerListener(OnBannerListener onBannerListener) {
        this.mBannerListener = onBannerListener;
    }

    public interface OnClickListener {
        void OnClick(View view, int position);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.mListener = listener;
    }

    public interface onTooTextAndTabText {
        void TooTextAndTabText(String tab, String Tootext, int chapterId);
    }

    public void setOnTooTextAndTabText(onTooTextAndTabText onTooTextAndTabText) {
        this.mOnTooTextAndTabText = onTooTextAndTabText;
    }
}
