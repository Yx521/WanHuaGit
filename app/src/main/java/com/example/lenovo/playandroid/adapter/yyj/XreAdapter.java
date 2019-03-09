package com.example.lenovo.playandroid.adapter.yyj;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.lenovo.playandroid.adapter.wx.Readapter;
import com.example.lenovo.playandroid.beans.yyj.Bean;
import com.example.lenovo.playandroid.dao.CanData;
import com.example.lenovo.playandroid.dao.DataBaseMannger;


import java.util.List;
import java.util.logging.Handler;

public class XreAdapter extends RecyclerView.Adapter<XreAdapter.MuHolder> {
    public List<Bean.DataBean.DatasBean> mll;
    private Context context;
    private settabAndToo mSetData;
    private Dianji mdianji;
    private int aa;

    public XreAdapter(List<Bean.DataBean.DatasBean> ll) {
        this.mll = ll;
    }

    public void setLl(List<Bean.DataBean.DatasBean> ll) {
        mll.addAll(ll);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.yanitem, parent, false);
        MuHolder muHolder = new MuHolder(inflate);
        return muHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MuHolder holder, final int position) {
        aa = position;
        final Bean.DataBean.DatasBean datasBean = mll.get(position);
        Log.e("onBindViewHolder", datasBean.getChapterName() + "");
        final String chapterName = datasBean.getChapterName();
        holder.name.setText(chapterName);
        holder.gong.setText("公众号/" + chapterName);
        holder.gong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSetData.setData(chapterName);
            }
        });
        holder.data.setText(datasBean.getNiceDate());
        holder.title.setText(datasBean.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdianji.Tiao(datasBean, position);

            }
        });


        List<CanData> canData = DataBaseMannger.getIntrance().selectCan();
        if (canData.size() != 0) {
            for (int i = 0; i < canData.size(); i++) {
                if (canData.get(i).getTitle().equals(mll.get(position).getTitle()) && canData.get(i).getAuthor().equals(mll.get(position).getAuthor())
                        && canData.get(i).getLink().equals(mll.get(position).getLink())) {
                    holder.lu.setVisibility(View.VISIBLE);
                    holder.tao.setVisibility(View.GONE);
                }
            }
        } else {
            holder.lu.setVisibility(View.GONE);
            holder.tao.setVisibility(View.VISIBLE);
        }
        holder.lu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickyan.show(v, position, mll);
                holder.tao.setVisibility(View.VISIBLE);
                holder.lu.setVisibility(View.GONE);

            }
        });
        holder.tao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBai.Bai(v, position, mll);
                holder.tao.setVisibility(View.GONE);
                holder.lu.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mll.size();
    }

    public class MuHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView title;
        private final ImageView tao;
        private final ImageView lu;
        private final TextView gong;
        private final TextView data;


        public MuHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name1);
            title = itemView.findViewById(R.id.title);
            data = itemView.findViewById(R.id.data);
            tao = itemView.findViewById(R.id.bai);
            lu = itemView.findViewById(R.id.lu);
            gong = itemView.findViewById(R.id.gongzhong);

        }
    }


    public void setDianji(Dianji dianji) {
        this.mdianji = dianji;
    }

    public interface Dianji {
        void Tiao(Bean.DataBean.DatasBean datasBean, int ii);
    }


    public interface settabAndToo {
        void setData(String tab);

    }

    public void setSettabAndToo(settabAndToo settabAndToo) {
        this.mSetData = settabAndToo;
    }


    //点击禄色
    public OnClickyan onClickyan;

    public void setOnClickyan(OnClickyan onClickyan) {
        this.onClickyan = onClickyan;
    }

    public interface OnClickyan {
        void show(View view, int position, List<Bean.DataBean.DatasBean> mll);
    }

    public OnClickBai onClickBai;

    public void setOnClickBai(OnClickBai onClickBai) {
        this.onClickBai = onClickBai;
    }

    public interface OnClickBai {
        void Bai(View view, int position, List<Bean.DataBean.DatasBean> mll);
    }

}
