package com.example.lenovo.playandroid.adapter.wx;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.beans.wx.Re;
import com.example.lenovo.playandroid.presenter.yx.Presenter;

import java.util.List;

/**
 * Created by ASUS on 2019/3/3.
 */

public class Readapter extends RecyclerView.Adapter<Readapter.Holder>{

          private List<Re.DataBean.DatasBean> list;
              Context context;

    public Readapter(List<Re.DataBean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Readapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.pager,parent,false);
          return new Holder(view);
     }
    @Override
    public void onBindViewHolder(@NonNull Readapter.Holder holder, final int position) {
         holder.item_search_pager_author.setText(list.get(position).getAuthor());
               holder.item_search_pager_chapterName.setText(list.get(position).getSuperChapterName()+"/"+list.get(position).getChapterName());
               holder.item_search_pager_title.setText(list.get(position).getTitle());
               holder.item_search_pager_niceDate.setText(list.get(position).getNiceDate());

               //接口回掉
             holder.itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
              public void onClick(View v) {
                 onCk.show(v,position,list);
                 }
            });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
          TextView item_search_pager_author;
          TextView item_search_pager_chapterName;
          TextView item_search_pager_title;
          TextView item_search_pager_niceDate;

        public Holder(View itemView) {
            super(itemView);
            item_search_pager_author = itemView.findViewById(R.id.item_search_pager_author);
            item_search_pager_chapterName = itemView.findViewById(R.id.item_search_pager_chapterName);
            item_search_pager_title = itemView.findViewById(R.id.item_search_pager_title);
            item_search_pager_niceDate = itemView.findViewById(R.id.item_search_pager_niceDate);

        }

    }

    //接口回掉

       OnCk onCk;

    public void setOnCk(OnCk onCk) {
        this.onCk = onCk;
    }

    public interface OnCk{
          void show(View view,int position,List<Re.DataBean.DatasBean> list);
    }
}

