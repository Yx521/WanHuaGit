package com.example.lenovo.playandroid.view.wlg;

import com.example.lenovo.playandroid.base.view.BaseView;
import com.example.lenovo.playandroid.beans.wlg.NaviBean;
import com.example.lenovo.playandroid.beans.yx.Collect;

/**
 * Created by lenovo on 2019/3/1.
 */

public interface WlgNaviView<V > extends BaseView{
    void shouNaviBean(NaviBean naviBean);
    void shouCollect(Collect collect);
    void shouunCollect(Collect collect);
}
