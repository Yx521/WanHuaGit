package com.example.lenovo.playandroid.view.yyj;

import com.example.lenovo.playandroid.base.view.BaseView;
import com.example.lenovo.playandroid.beans.yyj.Bean;
import com.example.lenovo.playandroid.beans.yyj.Fuyong;
import com.example.lenovo.playandroid.beans.yyj.sousuo;

public interface YanView<V> extends BaseView {
    void ShowFu(Bean fuyong);

    void ShowSou(Bean sousuo);
}
