package com.example.lenovo.playandroid.fragments.yyj;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.airbnb.lottie.L;
import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.activitys.yyj.XiaActivity;
import com.example.lenovo.playandroid.activitys.yyj.XinaActivity;
import com.example.lenovo.playandroid.adapter.yyj.SouAdapter;
import com.example.lenovo.playandroid.adapter.yyj.XreAdapter;
import com.example.lenovo.playandroid.base.fragment.BaseFragment;

import com.example.lenovo.playandroid.beans.yyj.Bean;
import com.example.lenovo.playandroid.beans.yyj.Fuyong;
import com.example.lenovo.playandroid.beans.yyj.sousuo;
import com.example.lenovo.playandroid.presenter.DuoP;
import com.example.lenovo.playandroid.presenter.yyj.Main_beanP;
import com.example.lenovo.playandroid.view.yx.IView;
import com.example.lenovo.playandroid.view.yyj.YanView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class FackFragment extends BaseFragment<YanView, DuoP<YanView>> implements YanView, XRecyclerView.LoadingListener {
    private final String mName;
    private String name;
    private XRecyclerView xre;
    private boolean isViewCreated;

    private boolean isLoadDataCompleted;
    private EditText shou;
    private String string;
    private int page = 0;
    private XreAdapter xreAdapter;
    private List<Bean.DataBean.DatasBean> ll;
    private TextView soosoo;
    private XRecyclerView ying;
    private XreAdapter xreAdapter1;

    @Override
    protected DuoP<YanView> createPresenter() {
        return new DuoP<>();
    }

    @SuppressLint("ValidFragment")
    public FackFragment(int id, String name) {
        this.name = id + "";
        this.mName = name;

    }

    @Override
    protected int creatrLayoutId() {
        return R.layout.fragment_fack;
    }

    @Override
    protected void initData() {
        xre = getActivity().findViewById(R.id.xrell);
        shou = getActivity().findViewById(R.id.sou);
        ying = getActivity().findViewById(R.id.ying);
        soosoo = getActivity().findViewById(R.id.soosoo);


        SpannableString spannableString = new SpannableString(mName + "带你发现更多干货");
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(15, true);
        spannableString.setSpan(absoluteSizeSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        xre.setLayoutManager(new LinearLayoutManager(getActivity()));
        xreAdapter1 = new XreAdapter(new ArrayList<Bean.DataBean.DatasBean>());
        xre.setAdapter(xreAdapter1);

        mPresenter.Fulian(name, page + "");
        xre.setLoadingListener(this);
        shou.setHint(new SpannableString(spannableString));
        xreAdapter1.setDianji(new XreAdapter.Dianji() {
            @Override
            public void Tiao(Bean.DataBean.DatasBean datasBean, int ii) {
                int chapterId = datasBean.getChapterId();
                String string = chapterId + "";
                EventBus.getDefault().postSticky(string);
                Intent intent = new Intent(getActivity(), XiaActivity.class);
                intent.putExtra("aa", datasBean.getLink());
                startActivity(intent);
            }
        });
        xreAdapter1.setSettabAndToo(new XreAdapter.settabAndToo() {
            @Override
            public void setData(String tab) {
                // Log.e(tab, tab);

                Intent intent = new Intent(getActivity(), XinaActivity.class);
                intent.putExtra("na", tab);
                startActivity(intent);
            }
        });

        soosoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = shou.getText().toString();
                mPresenter.soulian(null, s, page);
            }
        });
    }

    @Override
    public void ShowFu(Bean fuyong) {
        xreAdapter1.mll.clear();
        List<Bean.DataBean.DatasBean> datas = fuyong.getData().getDatas();
        Log.e("fgnffgbdfvd", "FackFragment: " + datas.get(0).getChapterName() + "");

        xreAdapter1.setLl(datas);
    }

    @Override
    public void ShowSou(Bean sousuo) {
        //搜索
        xreAdapter1.mll.clear();
        xreAdapter1.setLl(sousuo.getData().getDatas());

    }


    @Override
    public void showError(String error) {

    }

   /* @Override
    public void Tiao(View view, int ii) {
        Intent intent = new Intent(getActivity(), XiaActivity.class);
        intent.putExtra("aa", ll.get(ii).getLink());
        intent.putExtra("bb", ll.get(ii).getTitle());

        startActivity(intent);
    }

    @Override
    public void Gongll(View view, List<Bean.DataBean.DatasBean> mll, int ii) {
        String author = ll.get(ii).getAuthor();
        int chapterId = ll.get(ii).getChapterId();
        String string = chapterId + "";
        Log.e("yx", "Gongll: " + string);
        EventBus.getDefault().postSticky(string);
        Intent intent = new Intent(getActivity(), XinaActivity.class);
        intent.putExtra("na", author);
        startActivity(intent);
    }*/

    @Override
    public void onRefresh() {
        page = 0;
        mPresenter.Fulian(name, page + "");
        xre.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        page++;
        mPresenter.Fulian(name, page + "");
        if(xreAdapter!=null){
            xreAdapter.notifyDataSetChanged();
        }
    }
    // XRecyclerView.LoadingListener, XreAdapter.Dianji, XreAdapter.Gongdian
   /* private final String mName;
    private String name;
    private XRecyclerView xre;
    private boolean isViewCreated;

    *//**
     * 数据是否已加载完毕
     *//*

    private boolean isLoadDataCompleted;
    private EditText shou;
    private String string;
    private int page = 0;
    private XreAdapter xreAdapter;
    private List<Bean.DataBean.DatasBean> ll;
    private TextView soosoo;
    private XRecyclerView ying;

    @SuppressLint("ValidFragment")
    public FackFragment(int id, String name) {
        this.name = id + "";
        this.mName = name;
        Log.e("kmklmlkm", "FackFragment: " + id + "");
        Log.e("fgnfgbhdfgbdfvd", "FackFragment: " + name + "");
        // Required empty public constructor
    }


    @Override
    protected int creatrLayoutId() {
        return R.layout.fragment_fack;
    }

    @Override
    protected void initData() {

        xre = getActivity().findViewById(R.id.xrell);
        shou = getActivity().findViewById(R.id.sou);
        ying = getActivity().findViewById(R.id.ying);
        soosoo = getActivity().findViewById(R.id.soosoo);
        xre.setLayoutManager(new LinearLayoutManager(getActivity()));

        ll = new ArrayList<>();
        xreAdapter = new XreAdapter(ll);
        xre.setAdapter(xreAdapter);
        mPresenter.beanP(null, name, page + "");
        xre.setLoadingListener(this);
        xreAdapter.setDianji(this);
        xreAdapter.setGongdian(this);
        shou.setHint(mName + "带你发现更多干货");
        soosoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = shou.getText().toString();
              //  mPresenter.soulian(null, s, 0 + "");
                ying.setVisibility(View.VISIBLE);
                xre.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected Main_beanP<IView> createPresenter() {
        return new Main_beanP<>();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void show(Object o) {
        Bean bean = (Bean) o;
        List<Bean.DataBean.DatasBean> datas = bean.getData().getDatas();
        Log.e("fgnffgbdfvd", "FackFragment: " + datas.get(0).getChapterName() + "");

        ll.addAll(datas);
        xreAdapter.setLl(ll);
        xre.loadMoreComplete();
        xreAdapter.notifyDataSetChanged();


        //搜索
      *//*  sousuo sousuo = (sousuo) o;
        List<com.example.lenovo.playandroid.beans.yyj.sousuo.DataBean.DatasBean> datas1 = sousuo.getData().getDatas();
        ying.setLayoutManager(new LinearLayoutManager(getActivity()));
        SouAdapter souAdapter = new SouAdapter(datas1);
        ying.setAdapter(souAdapter);*//*

        //   AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(14, true);
        //   string.setSpan(absoluteSizeSpan,0,string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //shou.setHint(new SpannableString(string));

    }

    @Override
    public void onRefresh() {
        page = 0;
        mPresenter.beanP(null, name, page + "");
        xre.refreshComplete();

    }

    @Override
    public void onLoadMore() {
        page++;
        mPresenter.beanP(null, name, page + "");
        xreAdapter.notifyDataSetChanged();
    }

    @Override
    public void Tiao(View v, int ii) {
        Intent intent = new Intent(getActivity(), XiaActivity.class);

        intent.putExtra("aa", ll.get(ii).getLink());
        intent.putExtra("bb", ll.get(ii).getTitle());

        startActivity(intent);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreated && !isLoadDataCompleted) {

            isLoadDataCompleted = true;

            loadData();

        }
    }

    private void loadData() {

    }

    @Override
    public void Gongll(View v, List<Bean.DataBean.DatasBean> ll, int ii) {
        String author = ll.get(ii).getAuthor();
        int chapterId = ll.get(ii).getChapterId();
        String string = chapterId + "";
        Log.e("yx", "Gongll: " + string);
        EventBus.getDefault().postSticky(string);
        Intent intent = new Intent(getActivity(), XinaActivity.class);
        intent.putExtra("na", author);
        startActivity(intent);
    }*/
}
