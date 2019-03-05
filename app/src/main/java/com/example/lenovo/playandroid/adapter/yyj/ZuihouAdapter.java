package com.example.lenovo.playandroid.adapter.yyj;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.beans.yyj.Bean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class ZuihouAdapter extends XRecyclerView.Adapter<ZuihouAdapter.MuHolder> {
    private List<Bean.DataBean.DatasBean> mll;
    private Context context;
    public ZuihouAdapter(List<Bean.DataBean.DatasBean> ll) {
        this.mll = ll;
    }

    public void setLl(List<Bean.DataBean.DatasBean> ll){mll = ll;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.yanitem2, parent, false);
        MuHolder myAdapter = new MuHolder(inflate);
        return myAdapter;
    }

    @Override
    public void onBindViewHolder(@NonNull MuHolder holder, int position) {
        Bean.DataBean.DatasBean bean = mll.get(position);
        final Bean.DataBean.DatasBean datasBean = mll.get(position);
        Log.e("onBindViewHolder",  datasBean.getChapterName()+ "");
        String chapterName = datasBean.getChapterName();
        holder.name.setText(chapterName);

        if (TextUtils.isEmpty(chapterName)){
            holder.gong.setText("公众号/"+datasBean.getTags());
        }

        holder.data.setText(datasBean.getNiceDate());
        holder.title.setText(datasBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return mll.size();
    }

    public class MuHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView title;
        private final ImageView tao;
        private final TextView gong;
        private final TextView data;


        public MuHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name11);
            title = itemView.findViewById(R.id.title1);
            data = itemView.findViewById(R.id.data1);
            tao = itemView.findViewById(R.id.tao1);
            gong = itemView.findViewById(R.id.gong1);
        }
    }
}
