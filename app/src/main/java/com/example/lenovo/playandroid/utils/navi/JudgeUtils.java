package com.example.lenovo.playandroid.utils.navi;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.example.lenovo.playandroid.activitys.wlg.ArticleDetailActivity;
import com.example.lenovo.playandroid.global.Constants;


/**
 * @author quchao
 * @date 2018/2/24
 */

public class JudgeUtils {

    public static void startArticleDetailActivity(Context mActivity, ActivityOptions activityOptions, int id, String articleTitle,
                                                  String articleLink, String getAuthor,
                                                  boolean isCollectPage, boolean isCommonSite) {
        Intent intent = new Intent(mActivity, ArticleDetailActivity.class);
        intent.putExtra(Constants.ARTICLE_ID, id);
        intent.putExtra(Constants.ARTICLE_TITLE, articleTitle);
        intent.putExtra(Constants.ARTICLE_LINK, articleLink);
        intent.putExtra(Constants.IS_COLLECT, getAuthor);

        if (activityOptions != null && !Build.MANUFACTURER.contains("samsung") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mActivity.startActivity(intent, activityOptions.toBundle());
        } else {
            mActivity.startActivity(intent);
        }
    }






}
