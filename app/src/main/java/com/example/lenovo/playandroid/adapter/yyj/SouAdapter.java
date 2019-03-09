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
import com.example.lenovo.playandroid.beans.yyj.sousuo;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class SouAdapter extends XRecyclerView.Adapter<SouAdapter.MyHolder> {
    private Context context;
    private List<sousuo.DataBean.DatasBean>ll;

    public SouAdapter(List<sousuo.DataBean.DatasBean> ll) {
        this.ll = ll;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.yanitem, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        sousuo.DataBean.DatasBean datasBean = ll.get(position);
        holder.name.setText(datasBean.getChapterName());
        holder.title.setText(datasBean.getTitle());
        holder.gong.setText("公众号/"+datasBean.getAuthor());
        holder.data.setText(datasBean.getPublishTime()+"");
    }

    @Override
    public int getItemCount() {
        return ll.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView title;
        private final ImageView tao;
        private final TextView gong;
        private final TextView data;
        public MyHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name1);
            title = itemView.findViewById(R.id.title);
            data = itemView.findViewById(R.id.data);
            tao = itemView.findViewById(R.id.bai);
            gong = itemView.findViewById(R.id.gongzhong);

        }
    }
}
