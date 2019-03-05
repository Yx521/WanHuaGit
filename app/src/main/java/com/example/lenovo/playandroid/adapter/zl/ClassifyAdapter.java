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

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.beans.zl.FeedArticleListData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 18 on 2019/3/4.
 */

public class ClassifyAdapter extends RecyclerView.Adapter {

    public ArrayList<FeedArticleListData.DataBean.DatasBean> mData;
    private Context mContext;
    private OnClickListener mListener;

    public ClassifyAdapter(ArrayList<FeedArticleListData.DataBean.DatasBean> datasBeans, String s, String too) {
        this.mData = datasBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_search_pager, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
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
                mListener.OnClick(viewHolder.itemView,position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addData(List<FeedArticleListData.DataBean.DatasBean> datas) {
        mData.addAll(datas);
        notifyDataSetChanged();
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
    public interface OnClickListener{
        void OnClick(View view ,int position);
    }
    public void setOnClickListener(OnClickListener listener){
        this.mListener = listener;
    }
}
