package com.example.lenovo.playandroid.adapter.yx;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.beans.yx.ProjectClassifyData;
import com.example.lenovo.playandroid.dao.DataBaseMannger;
import com.example.lenovo.playandroid.dao.DecisionGlide;
import com.example.lenovo.playandroid.utils.NoImageUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2019/3/1.
 */

public class ClassifyAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<ProjectClassifyData.DataBean.DatasBean> mData;
    private OnItemClickListener mListener;

    public ClassifyAdapter(Context context, ArrayList<ProjectClassifyData.DataBean.DatasBean> datasBeans) {
        this.mContext = context;
        this.mData = datasBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_item, parent, false);
        return new ViewHolderClassify(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolderClassify holderClassify = (ViewHolderClassify) holder;
        if (!TextUtils.isEmpty(mData.get(position).getEnvelopePic())) {
            List<DecisionGlide> decisionGlides = DataBaseMannger.getIntrance().selectGlide();
            Boolean isbo = decisionGlides.get(0).getIsbo();
            NoImageUtils.getNoImgnstance().LoadGlide(mData.get(position).getEnvelopePic(),mContext,holderClassify.mItemProjectListIv,isbo);
           // Glide.with(mContext).load(mData.get(position).getEnvelopePic()).into(holderClassify.mItemProjectListIv);
        }
        if (!TextUtils.isEmpty(mData.get(position).getTitle())) {
            holderClassify.mItemProjectListTitleTv.setText(mData.get(position).getTitle());
        }
        if (!TextUtils.isEmpty(mData.get(position).getDesc())) {
            holderClassify.mItemProjectListContentTv.setText(mData.get(position).getDesc());
        }
        if (!TextUtils.isEmpty(mData.get(position).getNiceDate())) {
            holderClassify.mItemProjectListTimeTv.setText(mData.get(position).getNiceDate());
        }
        if (!TextUtils.isEmpty(mData.get(position).getAuthor())) {
            holderClassify.mItemProjectListAuthorTv.setText(mData.get(position).getAuthor());
        }
        if (!TextUtils.isEmpty(mData.get(position).getApkLink())) {
            holderClassify.mItemProjectListInstallTv.setVisibility(View.VISIBLE);
        } else {
            holderClassify.mItemProjectListInstallTv.setVisibility(View.GONE);
        }

        holderClassify.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.onItemClick(v,position,mData);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addData(List<ProjectClassifyData.DataBean.DatasBean> datas) {
        this.mData.addAll(datas);
        notifyDataSetChanged();
    }

    class ViewHolderClassify extends RecyclerView.ViewHolder{
        @BindView(R.id.item_project_list_iv)
        ImageView mItemProjectListIv;
        @BindView(R.id.item_project_list_title_tv)
        TextView mItemProjectListTitleTv;
        @BindView(R.id.item_project_list_content_tv)
        TextView mItemProjectListContentTv;
        @BindView(R.id.item_project_list_time_tv)
        TextView mItemProjectListTimeTv;
        @BindView(R.id.item_project_list_author_tv)
        TextView mItemProjectListAuthorTv;
        @BindView(R.id.item_project_list_install_tv)
        TextView mItemProjectListInstallTv;

        ViewHolderClassify(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position,ArrayList<ProjectClassifyData.DataBean.DatasBean> mData);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }
}
