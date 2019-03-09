package com.example.lenovo.playandroid.adapter.yyj;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.playandroid.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class CollectionAdapter extends XRecyclerView.Adapter<CollectionAdapter.MyHolder> {
    private List<String>ll;
    private Context context;
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.collection_item, parent, false);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return ll.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView gongzhonghao;
        private final TextView title;
        private final TextView data;
        private final ImageView like;
        private final ImageView nolike;

        public MyHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name2);
            gongzhonghao = itemView.findViewById(R.id.gongzhonghao);
            title = itemView.findViewById(R.id.title2);
            data = itemView.findViewById(R.id.data2);
            like = itemView.findViewById(R.id.like);
            nolike = itemView.findViewById(R.id.nolike);
        }
    }
}
