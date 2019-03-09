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
import android.widget.Toast;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.activitys.wx.Details;
import com.example.lenovo.playandroid.activitys.yyj.XiaActivity;
import com.example.lenovo.playandroid.adapter.wx.Readapter;
import com.example.lenovo.playandroid.base.activity.BaseActivity;
import com.example.lenovo.playandroid.base.fragment.BaseFragment;
import com.example.lenovo.playandroid.beans.wx.Batree;
import com.example.lenovo.playandroid.beans.wx.Data;
import com.example.lenovo.playandroid.beans.wx.HttpResult;
import com.example.lenovo.playandroid.beans.wx.Re;
import com.example.lenovo.playandroid.beans.yx.Collect;
import com.example.lenovo.playandroid.dao.CanData;
import com.example.lenovo.playandroid.dao.DataBaseMannger;
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
    private int id;
    private int originId;
    private int id1;
    private String title;


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
        Map<String,Object> map1 = (Map<String, Object>) o;
        String biao = (String) map1.get("biao");
        if("one".equals(biao)){
            Re re = (Re) map1.get("va");
            List<Re.DataBean.DatasBean> datas=re.getData().getDatas();
            Log.i("data",datas.get(0).getChapterName());
            Log.i("yangxu",""+datas.get(0).isCollect());
            mDatasBeanList.addAll(datas);
            Log.e(TAG, "show: "+mDatasBeanList.size() );
            readapter.notifyDataSetChanged();

        }else if("shou".equals(biao)){
            Collect collect = (Collect) map1.get("va");
            int errorCode = collect.getErrorCode();
            Log.i("yangxu", "show: "+errorCode );

            if (errorCode==0){
                Toast.makeText(mActivity,"收藏成功",Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(mActivity,"收藏失败",Toast.LENGTH_SHORT).show();
            }
        }else if("shan".equals(biao)){
            HttpResult collect = (HttpResult) map1.get("va");
            int errorCode = collect.getErrorCode();
            Log.i("yang", "show: "+errorCode);
            if (errorCode==0){
                Toast.makeText(mActivity,"删除成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(mActivity,"删除失败",Toast.LENGTH_SHORT).show();
            }
        }else if("shou1".equals(biao)){
            Data data = (Data) map1.get("va");
            List<Data.DataBean.DatasBean> datas = data.getData().getDatas();
            //id1 = datas.get(0).getId();

            for (int i = 0; i < datas.size(); i++) {
                for (int j = 0; j < readapter.list.size(); j++) {
                    if(readapter.list.get(j).getTitle().equals(datas.get(i).getTitle())){
                        id1 = datas.get(i).getId();
                        originId = datas.get(i).getOriginId();
                    }
                }
            }
            Map<String,Object> map2 = new HashMap<>();
            map2.put("biao","shan");
            Log.i("yangxuxu", "show: "+id1);
            map2.put("id",id1);
            Log.i("yangxuxu", "show: "+originId);
            map2.put("originId",originId);
            Log.i("yangxu", originId+"onClick: "+id1);
            mPresenter.WDataP(map2);
        }


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
        Bundle bundle = getArguments();
        id = bundle.getInt("int");
        Log.i("yangxu", id +"");
        Map<String,Object> map = new HashMap<>();
        map.put("cid", id);
        map.put("page",page);
        map.put("biao","one");
        mPresenter.WDataP(map);
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
                startActivityForResult(intent,11);
            }
        });

        readapter.setOnCkon(new Readapter.OnCkon() {
            @Override
            public void show(View view, int position, List<Re.DataBean.DatasBean> list) {
                Toast.makeText(mContext, "喜欢", Toast.LENGTH_SHORT).show();
                Map<String,Object> map = new HashMap<>();
                int id2 = list.get(position).getId();
                map.put("id", id2);
                map.put("biao","shou");
                mPresenter.WDataP(map);
                DataBaseMannger.getIntrance().insertCan(new CanData(null,list.get(position).getTitle(),list.get(position).getAuthor(),list.get(position).getLink(),true));
            }
        });

        readapter.setOnClick(new Readapter.OnClick1() {
            @Override
            public void show(View view, int position, List<Re.DataBean.DatasBean> list) {
                Toast.makeText(mContext, "不喜欢", Toast.LENGTH_SHORT).show();
                title = list.get(position).getTitle();
                Map<String,Object> map1 = new HashMap<>();
                map1.put("biao","shou1");
                mPresenter.WDataP(map1);
                List<CanData> canData = DataBaseMannger.getIntrance().selectCan();
                for (int i = 0; i < canData.size(); i++) {
                    if(canData.get(i).getTitle().equals(list.get(position).getTitle())&&canData.get(i).getAuthor().equals(list.get(position).getAuthor())
                            &&canData.get(i).getLink().equals(list.get(position).getLink())){
                        Long id = canData.get(i).getId();
                        DataBaseMannger.getIntrance().deleteCan(new CanData(id,null,null,null,null));
                    }
                }
            }
        });
    }

    @Override
    protected PresenterX<IView> createPresenter() {
        return new PresenterX<>();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==11&&resultCode==12){
            initData();
        }
    }


}
