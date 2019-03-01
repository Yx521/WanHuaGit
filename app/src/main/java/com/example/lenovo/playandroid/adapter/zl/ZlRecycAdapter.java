package com.example.lenovo.playandroid.adapter.zl;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.lenovo.playandroid.beans.zl.BannerBean;

import java.util.ArrayList;

/**
 * Created by 18 on 2019/2/28.
 */

public class ZlRecycAdapter extends RecyclerView.Adapter {
    public ArrayList<BannerBean.DataBean> mData;

    public ZlRecycAdapter(ArrayList<BannerBean.DataBean> dataBeans) {
        this.mData = dataBeans;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
