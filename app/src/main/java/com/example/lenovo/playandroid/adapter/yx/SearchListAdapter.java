package com.example.lenovo.playandroid.adapter.yx;

import android.annotation.SuppressLint;
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
import com.example.lenovo.playandroid.activitys.yx.SearchListActivity;
import com.example.lenovo.playandroid.beans.yx.SearchList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2019/3/5.
 */

public class SearchListAdapter extends RecyclerView.Adapter {
    private SearchListActivity mContext;
    private List<SearchList.DataBean.DatasBean> mData;
    private boolean isCollectPage;
    private boolean isSearchPage;
    private boolean isNightMode;

    public SearchListAdapter(SearchListActivity searchListActivity, List<SearchList.DataBean.DatasBean> datas) {
        this.mContext = searchListActivity;
        this.mData = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_pager, parent, false);
        return new ViewHolder(inflate);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        if (!TextUtils.isEmpty(mData.get(position).getTitle())) {
            holder1.mItemSearchPagerTitle.setText( Html.fromHtml(mData.get(position).getTitle()));
        }
        if (mData.get(position).isCollect() || isCollectPage) {
            holder1.mItemSearchPagerLikeIv.setImageResource( R.drawable.icon_like);
        } else {
            holder1.mItemSearchPagerLikeIv.setImageResource( R.drawable.icon_like_article_not_selected);
        }
        if (!TextUtils.isEmpty(mData.get(position).getAuthor())) {
            holder1.mItemSearchPagerAuthor.setText( mData.get(position).getAuthor());
        }
        setTag(holder1, mData.get(position));
        if (!TextUtils.isEmpty(mData.get(position).getChapterName())) {
            String classifyName = mData.get(position).getSuperChapterName() + " / " + mData.get(position).getChapterName();
            if (isCollectPage) {
                holder1.mItemSearchPagerChapterName.setText(mData.get(position).getChapterName());
            } else {
                holder1.mItemSearchPagerChapterName.setText(classifyName);
            }
        }
        if (!TextUtils.isEmpty(mData.get(position).getNiceDate())) {
            holder1.mItemSearchPagerNiceDate.setText(mData.get(position).getNiceDate());
        }
        if (isSearchPage) {
            CardView cardView = holder1.mItemSearchPagerGroup;
            cardView.setForeground(null);
            if (isNightMode) {
                cardView.setBackground(ContextCompat.getDrawable(mContext, R.color.card_color));
            } else {
                cardView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.selector_search_item_bac));
            }
        }
    }
    private void setTag(ViewHolder helper, SearchList.DataBean.DatasBean article) {
        helper.mItemSearchPagerTagGreenTv.setVisibility(View.GONE);
        helper.mItemSearchPagerTagRedTv.setVisibility(View.GONE);
        if (isCollectPage) {
            return;
        }
        if (article.getSuperChapterName().contains(mContext.getString(R.string.open_project))) {
            setRedTag(helper, R.string.project);
        }

        if (article.getSuperChapterName().contains(mContext.getString(R.string.navigation))) {
            setRedTag(helper, R.string.navigation);
        }

        if (article.getNiceDate().contains(mContext.getString(R.string.minute))
                || article.getNiceDate().contains(mContext.getString(R.string.hour))
                || article.getNiceDate().contains(mContext.getString(R.string.one_day))) {
            helper.mItemSearchPagerTagGreenTv.setVisibility(View.VISIBLE);
            helper.mItemSearchPagerTagGreenTv.setText(R.string.text_new);
            helper.mItemSearchPagerTagGreenTv.setTextColor( ContextCompat.getColor(mContext, R.color.light_green));
            helper.mItemSearchPagerTagGreenTv.setBackgroundResource( R.drawable.shape_tag_green_background);
        }
    }
    private void setRedTag(ViewHolder helper, @StringRes int tagName) {
        helper.mItemSearchPagerTagRedTv.setVisibility(View.VISIBLE);
        helper.mItemSearchPagerTagRedTv.setText(tagName);
        helper.mItemSearchPagerTagRedTv.setTextColor( ContextCompat.getColor(mContext, R.color.light_deep_red));
        helper.mItemSearchPagerTagRedTv.setBackgroundResource( R.drawable.selector_tag_red_background);
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
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
}
