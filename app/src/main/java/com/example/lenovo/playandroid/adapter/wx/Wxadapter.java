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
import com.example.lenovo.playandroid.beans.wx.Batree;
import com.example.lenovo.playandroid.global.Global;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;
import java.util.Random;

import butterknife.OnClick;

/**
 * Created by ASUS on 2019/3/2.
 */

public class Wxadapter extends XRecyclerView.Adapter {

    private List<Batree.DataBean> list;
    private Context context;

    Random random=new Random();
    private int[] ints;

    public Wxadapter(List<Batree.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //ints = new int[]{R.color.colorPrimary, R.color.colorAccent, R.color.splash_blue, R.color.hong, R.color.hei, R.color.lan};

        View inflate = LayoutInflater.from(context).inflate(R.layout.wxknowled, parent, false);
        MyHoder myHoder=new MyHoder(inflate);
        return myHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int i) {
        MyHoder myHoder= (MyHoder) holder;

        myHoder.knowled_item1.setText(list.get(i).getName());
        int lenth = random.nextInt(5) + 1;
        myHoder.knowled_item1.setTextColor(Global.TAB_COLORS[lenth]);

        //循环
        String string = null;
        for (int j = 0; j < list.get(i).getChildren().size(); j++) {
            string = string + list.get(i).getChildren().get(j).getName() + "   ";
        }
        myHoder .knowled_item2.setText(string);

     myHoder.knowled_img.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if (onClick!=null){
                 onClick.show(list,i);

             }
         }
     });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(List<Batree.DataBean> data) {
        this.list.addAll(data);
        notifyDataSetChanged();
    }

    class MyHoder extends RecyclerView.ViewHolder{
        TextView knowled_item1;
        TextView knowled_item2;
        ImageView knowled_img;

        public MyHoder(View itemView) {
            super(itemView);

            knowled_item1 = itemView.findViewById(R.id.knowled_item1);
            knowled_item2 = itemView.findViewById(R.id.knowled_item2);
            knowled_img = itemView.findViewById(R.id.knowled_img);
        }
    }
    //接口回调
   public OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }
    public interface OnClick{
        void show(List<Batree.DataBean> list,int id);

    }
}
