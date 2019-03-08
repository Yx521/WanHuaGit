package com.example.lenovo.playandroid.adapter.wx;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.beans.wx.Re;
import com.example.lenovo.playandroid.dao.CanData;
import com.example.lenovo.playandroid.dao.DataBaseMannger;
import com.example.lenovo.playandroid.presenter.yx.Presenter;

import java.util.List;

/**
 * Created by ASUS on 2019/3/3.
 */

public class Readapter extends RecyclerView.Adapter<Readapter.Holder>{

          public List<Re.DataBean.DatasBean> list;
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
    public void onBindViewHolder(@NonNull final Readapter.Holder holder, final int position) {
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
        List<CanData> canData = DataBaseMannger.getIntrance().selectCan();
        if(canData.size()!=0){
            for (int i = 0; i < canData.size(); i++) {
                    if(canData.get(i).getTitle().equals(list.get(position).getTitle())&&canData.get(i).getAuthor().equals(list.get(position).getAuthor())
                            &&canData.get(i).getLink().equals(list.get(position).getLink())){
                        holder.item_search_pager_no_iv.setVisibility(View.VISIBLE);
                        holder.item_search_pager_like_iv.setVisibility(View.GONE);
                    }
            }
        }
        //
           holder.item_search_pager_like_iv.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   onCkon.show(v,position,list);
                   holder.item_search_pager_no_iv.setVisibility(View.VISIBLE);
                   holder.item_search_pager_like_iv.setVisibility(View.GONE);
               }
           });

           holder.item_search_pager_no_iv.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   onClickNo.show(v,position,list);
                   holder.item_search_pager_like_iv.setVisibility(View.VISIBLE);
                   holder.item_search_pager_no_iv.setVisibility(View.GONE);
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
          ImageView item_search_pager_like_iv;
          ImageView  item_search_pager_no_iv;
        public Holder(View itemView) {
            super(itemView);
            item_search_pager_author = itemView.findViewById(R.id.item_search_pager_author);
            item_search_pager_chapterName = itemView.findViewById(R.id.item_search_pager_chapterName);
            item_search_pager_title = itemView.findViewById(R.id.item_search_pager_title);
            item_search_pager_niceDate = itemView.findViewById(R.id.item_search_pager_niceDate);
            item_search_pager_like_iv=itemView.findViewById(R.id.item_search_pager_like_iv);
            item_search_pager_no_iv=itemView.findViewById(R.id.item_search_pager_no_iv);

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

    OnCkon onCkon;

    public void setOnCkon(OnCkon onCkon) {
        this.onCkon = onCkon;
    }

    public interface OnCkon{
        void show(View view,int position,List<Re.DataBean.DatasBean> list);
    }
    OnClick1 onClickNo;

    public void setOnClick(OnClick1 onClick1) {
        this.onClickNo = onClick1;
    }

    public interface OnClick1{
        void show(View view,int position,List<Re.DataBean.DatasBean> list);
    }
}

