package com.example.lenovo.playandroid.presenter;

import com.example.lenovo.playandroid.base.presenter.IBasePresenter;
import com.example.lenovo.playandroid.beans.yyj.Bean;
import com.example.lenovo.playandroid.beans.yyj.Fuyong;
import com.example.lenovo.playandroid.beans.yyj.sousuo;
import com.example.lenovo.playandroid.module.yyj.DuoM;
import com.example.lenovo.playandroid.presenter.yyj.Yyj;
import com.example.lenovo.playandroid.view.yyj.YanView;

public class DuoP<V extends YanView> extends IBasePresenter<V> implements DuoM.HttpQing {
    private DuoM duoM = new DuoM();

    public void Fulian(String num, String page) {
        duoM.getFuyongl(this,num,page);
    }

    public void soulian(Object o,String num,int page){
 duoM.getsousuol(this,o,num,page);
    }

    @Override
    public void setFuyongl(Bean o) {
        mView.ShowFu(o);
    }

    @Override
    public void setSoul(Bean o) {
        mView.ShowSou(o);
    }

    @Override
    public void setAnimation() {
        mView.showAnimation();
    }

    @Override
    public void setHidoAnimation() {
        mView.hidoAnimation();
    }

    @Override
    public void setError(String error) {
        mView.showError(error);
    }
}
