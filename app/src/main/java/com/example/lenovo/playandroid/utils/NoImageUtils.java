package com.example.lenovo.playandroid.utils;

import android.content.Context;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lenovo.playandroid.R;

public class NoImageUtils {
    private static NoImageUtils noImgView;

    private boolean mBooleans;

    public boolean isBoolean() {
        return mBooleans;
    }

    public void setBoolean(boolean aBoolean) {
        mBooleans = aBoolean;
    }

    public NoImageUtils() {

    }

    public NoImageUtils(boolean aBoolean) {
        mBooleans = aBoolean;
    }

    public static NoImageUtils getNoImgnstance() {
        if (noImgView == null) {
            synchronized (NoImageUtils.class) {
                if (noImgView == null) {
                    noImgView = new NoImageUtils();
                }
            }
        }
        return noImgView;
    }

    public void LoadGlide(String imgUrl, Context context, ImageView imageView, Boolean m) {

        if (m) {
           // Log.e("项目图片2",imgUrl);
            Glide.with(context).load(R.mipmap.icon_up_arrow).into(imageView);
        } else {

            Glide.with(context).load(imgUrl).into(imageView);
        }

    }
}
