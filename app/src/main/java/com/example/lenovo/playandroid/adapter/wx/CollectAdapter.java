package com.example.lenovo.playandroid.adapter.wx;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.beans.wx.Data;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS on 2019/3/8.
 */

public class CollectAdapter extends RecyclerView.Adapter {
    private ArrayList<Data.DataBean.DatasBean> mData;
    private Context mContext;
    private boolean isCollectPage;
    private boolean isSearchPage;
    private boolean isNightMode;

    public CollectAdapter(Context context, ArrayList<Data.DataBean.DatasBean> datasBeans) {
        this.mContext = context;
        this.mData = datasBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.collect_layout, parent, false);
        return new ViewHolder(inflate);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        if (!TextUtils.isEmpty(mData.get(position).getTitle())) {
            holder1.itemSearchPagerTitle.setText(Html.fromHtml(mData.get(position).getTitle()));
        }

            holder1.itemSearchPagerLikeIv.setImageResource(R.drawable.icon_like);

        if (!TextUtils.isEmpty(mData.get(position).getAuthor())) {
            holder1.itemSearchPagerAuthor.setText( mData.get(position).getAuthor());
        }
        setTag(holder1, mData.get(position));
        if (!TextUtils.isEmpty(mData.get(position).getChapterName())) {
                holder1.itemSearchPagerChapterName.setText( mData.get(position).getChapterName());
        }
        if (!TextUtils.isEmpty(mData.get(position).getNiceDate())) {
            holder1.itemSearchPagerNiceDate.setText( mData.get(position).getNiceDate());
        }
        if (isSearchPage) {
            CardView cardView = holder1.itemSearchPagerGroup;
            cardView.setForeground(null);
            if (isNightMode) {
                cardView.setBackground(ContextCompat.getDrawable(mContext, R.color.card_color));
            } else {
                cardView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.selector_search_item_bac));
            }
        }
    }
    private void setTag(ViewHolder helper, Data.DataBean.DatasBean article) {
        helper.itemSearchPagerTagGreenTv.setVisibility(View.GONE);
        helper.itemSearchPagerTagRedTv.setVisibility(View.GONE);
        if (isCollectPage) {
            return;
        }


        if (article.getNiceDate().contains(mContext.getString(R.string.minute))
                || article.getNiceDate().contains(mContext.getString(R.string.hour))
                || article.getNiceDate().contains(mContext.getString(R.string.one_day))) {
            helper.itemSearchPagerTagGreenTv.setVisibility(View.VISIBLE);
            helper.itemSearchPagerTagGreenTv.setText( R.string.text_new);
            helper.itemSearchPagerTagGreenTv.setTextColor( ContextCompat.getColor(mContext, R.color.light_green));
            helper.itemSearchPagerTagGreenTv.setBackgroundResource( R.drawable.shape_tag_green_background);
        }
    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addData(List<Data.DataBean.DatasBean> datas) {
        this.mData.addAll(datas);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_search_pager_author)
        TextView itemSearchPagerAuthor;
        @BindView(R.id.item_search_pager_chapterName)
        TextView itemSearchPagerChapterName;
        @BindView(R.id.item_search_pager_title)
        TextView itemSearchPagerTitle;
        @BindView(R.id.item_search_pager_tag_red_tv)
        TextView itemSearchPagerTagRedTv;
        @BindView(R.id.item_search_pager_tag_green_tv)
        TextView itemSearchPagerTagGreenTv;
        @BindView(R.id.item_search_tag_group)
        LinearLayout itemSearchTagGroup;
        @BindView(R.id.item_search_pager_like_iv)
        ImageView itemSearchPagerLikeIv;
        @BindView(R.id.item_search_pager_niceDate)
        TextView itemSearchPagerNiceDate;
        @BindView(R.id.item_search_pager_group)
        CardView itemSearchPagerGroup;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
