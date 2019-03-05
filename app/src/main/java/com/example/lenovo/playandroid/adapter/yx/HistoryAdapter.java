package com.example.lenovo.playandroid.adapter.yx;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.dao.HistoryData;
import com.example.lenovo.playandroid.global.Global;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2019/3/5.
 */

public class HistoryAdapter extends RecyclerView.Adapter {
    public List<HistoryData> mHistoryData;
    private OnItemClickListener mListener;

    public HistoryAdapter(List<HistoryData> history) {
        this.mHistoryData = history;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_history, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.mItemSearchHistoryTv.setText(mHistoryData.get(position).getHistory());
        holder1.mItemSearchHistoryTv.setTextColor(randomTagColor());

        holder1.mItemSearchHistoryTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.onItemClick(v,position,mHistoryData);
                }
            }
        });
    }

    public static int randomTagColor() {
        int randomNum = new Random().nextInt();
        int position = randomNum % Global.TAB_COLORS.length;
        if (position < 0) {
            position = -position;
        }
        return Global.TAB_COLORS[position];
    }

    @Override
    public int getItemCount() {
        return mHistoryData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_search_history_tv)
        TextView mItemSearchHistoryTv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public interface OnItemClickListener{
        void onItemClick(View view,int position,List<HistoryData> mHistoryData);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }
}
