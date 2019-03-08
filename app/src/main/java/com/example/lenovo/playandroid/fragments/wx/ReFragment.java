package com.example.lenovo.playandroid.fragments.wx;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.activitys.wx.Details;
import com.example.lenovo.playandroid.activitys.yyj.XiaActivity;
import com.example.lenovo.playandroid.adapter.wx.Readapter;
import com.example.lenovo.playandroid.base.activity.BaseActivity;
import com.example.lenovo.playandroid.base.fragment.BaseFragment;
import com.example.lenovo.playandroid.beans.wx.Batree;
import com.example.lenovo.playandroid.beans.wx.Re;
import com.example.lenovo.playandroid.fragments.yx.ClassifyFragment;
import com.example.lenovo.playandroid.presenter.wx.PresenterX;
import com.example.lenovo.playandroid.presenter.wx.WxPresenter;
import com.example.lenovo.playandroid.view.yx.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */

@SuppressLint("ValidFragment")
public class ReFragment extends BaseFragment<IView, PresenterX<IView>> implements IView {

    @BindView(R.id.re_xrv)
    XRecyclerView re_Xrv;
    Unbinder unbinder;

    private List<Re.DataBean.DatasBean> mDatasBeanList=new ArrayList<>();
    private Readapter readapter;
    private int page = 0;


 //复用
 public static Fragment fuyong(int userid) {
         ReFragment reFragment = new  ReFragment();
         Bundle bundle = new Bundle();
          bundle.putInt("int", userid);
        reFragment.setArguments(bundle);
        return reFragment;


    }


    @Override
    public void show(Object o) {
    Re re = (Re) o;
        List<Re.DataBean.DatasBean> datas=re.getData().getDatas();
        Log.i("data",datas.get(0).getChapterName());
        mDatasBeanList.addAll(datas);
        Log.e(TAG, "show: "+mDatasBeanList.size() );
        readapter.notifyDataSetChanged();



    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected int creatrLayoutId() {
        return R.layout.fragment_re;
    }

    @Override
    protected void initData() {

//        Bundle bundle = getArguments();
//        int id = bundle.getInt("id");
//        Log.i("yyj",id+"");
       /* Map<String,Object> map = new HashMap<>();
        map.put("cid",id);
        map.put("page",page);*/

        mPresenter.WDataP(0,60);
      LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        re_Xrv.setLayoutManager(linearLayoutManager);
        readapter = new  Readapter(mDatasBeanList,getActivity());
         re_Xrv.setAdapter(readapter);
        readapter.notifyDataSetChanged();

        //跳转
        readapter.setOnCk(new Readapter.OnCk() {
            @Override
            public void show(View view, int position, List<Re.DataBean.DatasBean> list) {
                Intent intent = new Intent(getContext(),Details.class);
                String title = list.get(position).getTitle();
                String link = list.get(position).getLink();
                String author = list.get(position).getAuthor();
                int id = list.get(position).getId();
                intent.putExtra("id",id);
                intent.putExtra("title",title);
                intent.putExtra("url",link);
                intent.putExtra("author",author);
                startActivity(intent);
            }
        });


    }

    @Override
    protected PresenterX<IView> createPresenter() {
        return new PresenterX<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
