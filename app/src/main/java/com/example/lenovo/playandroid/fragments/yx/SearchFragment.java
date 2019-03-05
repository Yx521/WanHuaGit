package com.example.lenovo.playandroid.fragments.yx;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.activitys.yx.SearchListActivity;
import com.example.lenovo.playandroid.adapter.yx.HistoryAdapter;
import com.example.lenovo.playandroid.app.App;
import com.example.lenovo.playandroid.base.fragment.BaseDialogFragment;
import com.example.lenovo.playandroid.beans.TopSearchData;
import com.example.lenovo.playandroid.beans.yx.SearchList;
import com.example.lenovo.playandroid.dao.DataBaseMannger;
import com.example.lenovo.playandroid.dao.HistoryData;
import com.example.lenovo.playandroid.global.Global;
import com.example.lenovo.playandroid.presenter.yx.SearchPresenter;
import com.example.lenovo.playandroid.utils.CircularRevealAnim;
import com.example.lenovo.playandroid.utils.KeyBoardUtils;
import com.example.lenovo.playandroid.view.yx.IView;
import com.example.lenovo.playandroid.view.yx.YxView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseDialogFragment<YxView, SearchPresenter<YxView>> implements YxView, CircularRevealAnim.AnimListener, ViewTreeObserver.OnPreDrawListener {


    @BindView(R.id.search_back_ib)
    ImageButton mSearchBackIb;
    @BindView(R.id.search_tv)
    TextView mSearchTv;
    @BindView(R.id.search_edit)
    EditText mSearchEdit;
    @BindView(R.id.search_tint_tv)
    TextView mSearchTintTv;
    @BindView(R.id.search_toolbar)
    Toolbar mSearchToolbar;
    @BindView(R.id.top_search_flow_layout)
    TagFlowLayout mTopSearchFlowLayout;
    @BindView(R.id.search_history_clear_all_tv)
    TextView mSearchHistoryClearAllTv;
    @BindView(R.id.search_history_null_tint_tv)
    TextView mSearchHistoryNullTintTv;
    @BindView(R.id.search_history_rv)
    RecyclerView mSearchHistoryRv;
    @BindView(R.id.search_scroll_view)
    NestedScrollView mSearchScrollView;
    @BindView(R.id.search_floating_action_btn)
    FloatingActionButton mSearchFloatingActionBtn;
    @BindView(R.id.search_coordinator_group)
    CoordinatorLayout mSearchCoordinatorGroup;
    Unbinder unbinder;
    Unbinder unbinder1;
    private CircularRevealAnim mCircularRevealAnim;
    private HistoryAdapter mHistoryAdapter;
    private int page = 0;
    private String mTest;
    private List<TopSearchData.DataBean> mData;

    @Override
    protected int creatrLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogStyle);
    }

    @Override
    public void onStart() {
        super.onStart();
        initDialog();
    }

    private void initDialog() {
        Window window = getDialog().getWindow();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        //DialogSearch的宽
        int width = (int) (metrics.widthPixels * 0.98);
        assert window != null;
        window.setLayout(width, WindowManager.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.TOP);
        //取消过渡动画 , 使DialogSearch的出现更加平滑
        window.setWindowAnimations(R.style.DialogEmptyAnimation);
    }

    @Override
    protected void initData() {
        initCircleAnimation();
        Map<String, Object> map = new HashMap<>();
        map.put("tag", "TopSearch");
        mPresenter.getDataP(map);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mSearchHistoryRv.setLayoutManager(manager);
        List<HistoryData> select = DataBaseMannger.getIntrance().select();
        mHistoryAdapter = new HistoryAdapter(select);
        mSearchHistoryRv.setAdapter(mHistoryAdapter);
        judgeNull(select);
        mSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(mSearchEdit.getText().toString())) {
                    mSearchTintTv.setText(R.string.search_tint);
                } else {
                    mSearchTintTv.setText("");
                }
            }
        });

        mHistoryAdapter.setOnItemClickListener(new HistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view,int position, List<HistoryData> mHistoryData) {
                String history = mHistoryData.get(position).getHistory();
                Map<String, Object> map = new HashMap<>();
                map.put("page", page);
                map.put("k", history);
                map.put("tag", "SearchList");
                mPresenter.getDataP(map);
            }
        });
    }

    private void judgeNull(List<HistoryData> history) {
        if (history.size() == 0) {
            mSearchHistoryNullTintTv.setVisibility(View.VISIBLE);
            setHistoryTvStatus(true);
        } else {
            mSearchHistoryNullTintTv.setVisibility(View.GONE);
            setHistoryTvStatus(false);
        }
    }

    private void initCircleAnimation() {
        mCircularRevealAnim = new CircularRevealAnim();
        mCircularRevealAnim.setAnimListener(this);

        mSearchTintTv.getViewTreeObserver().addOnPreDrawListener(this);
    }


    public void dismiss() {

    }
    public void backEvent() {
        KeyBoardUtils.closeKeyboard(getActivity(), mSearchEdit);
        mCircularRevealAnim.hide(mSearchEdit, mView);
    }

    @Override
    public void show(Object o, String tag) {
        switch (tag) {
            case "TopSearch":
                TopSearchData topSearchData = (TopSearchData) o;
                mData = topSearchData.getData();
                mTopSearchFlowLayout.setAdapter(new TagAdapter<TopSearchData.DataBean>(mData) {

                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public View getView(FlowLayout parent, int position, TopSearchData.DataBean dataBean) {
                        assert getActivity() != null;
                        TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.flow_layout_tv,
                                parent, false);
                        assert dataBean != null;
                        String name = dataBean.getName();
                        tv.setText(name);
                        tv.setTransitionName("topSearch");
                        setItemBackground(tv);
                        mTopSearchFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public boolean onTagClick(View view, int position, FlowLayout parent) {
                                String name1 = mData.get(position).getName();
                                mTest = name1;
                                List<HistoryData> select = DataBaseMannger.getIntrance().select();
                                if(select.size()!=0){
                                    for (int i = 0; i < select.size(); i++) {
                                        if(!select.get(i).getHistory().equals(mTest)){
                                            DataBaseMannger.getIntrance().insert(new HistoryData(null,mTest));
                                        }
                                    }
                                }else {
                                    DataBaseMannger.getIntrance().insert(new HistoryData(null,mTest));
                                }
                                Map<String, Object> map = new HashMap<>();
                                map.put("page", page);
                                map.put("k", name1);
                                map.put("tag", "SearchList");
                                mPresenter.getDataP(map);
                                return true;
                            }
                        });
                        return tv;
                    }
                });
                break;
            case "SearchList":
                SearchList searchList = (SearchList) o;
                List<SearchList.DataBean.DatasBean> datas = searchList.getData().getDatas();
                Intent intent = new Intent(getContext(), SearchListActivity.class);
                intent.putExtra("title",mTest);
                intent.putExtra("data", (Serializable) datas);
                startActivityForResult(intent,11);
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==11&&resultCode==12){
            backEvent();
        }
    }

    private void setItemBackground(TextView tv) {
        tv.setBackgroundColor(randomTagColor());
        tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
    }

    public static int randomTagColor() {
        int randomNum = new Random().nextInt();
        int position = randomNum % Global.TAB_COLORS.length;
        if (position < 0) {
            position = -position;
        }
        return Global.TAB_COLORS[position];
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected SearchPresenter<YxView> createPresenter() {
        return new SearchPresenter<>();
    }

    @Override
    public void onHideAnimationEnd() {
        dismissAllowingStateLoss();
    }

    @Override
    public void onShowAnimationEnd() {

    }

    @Override
    public boolean onPreDraw() {
        mSearchTintTv.getViewTreeObserver().removeOnPreDrawListener(this);
        mCircularRevealAnim.show(mSearchTintTv, mView);
        return true;
    }

    @OnClick({R.id.search_back_ib, R.id.search_history_clear_all_tv, R.id.search_floating_action_btn, R.id.search_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_back_ib:
                backEvent();
                break;
            case R.id.search_history_clear_all_tv:
                DataBaseMannger.getIntrance().deleteAll();
                mHistoryAdapter.mHistoryData.clear();
                mHistoryAdapter.notifyDataSetChanged();
                List<HistoryData> select1 = DataBaseMannger.getIntrance().select();
                judgeNull(select1);
                setHistoryTvStatus(true);
                break;
            case R.id.search_floating_action_btn:
                break;
            case R.id.search_tv:
                mTest = mSearchEdit.getText().toString();
                if (mTest != null) {
                    List<HistoryData> select = DataBaseMannger.getIntrance().select();
                    if(select.size()!=0){
                        for (int i = 0; i < select.size(); i++) {
                            if(!select.get(i).getHistory().equals(mTest)){
                                DataBaseMannger.getIntrance().insert(new HistoryData(null,mTest));
                            }
                        }
                    }else {
                        DataBaseMannger.getIntrance().insert(new HistoryData(null,mTest));
                    }

                    Map<String, Object> map = new HashMap<>();
                    map.put("page", page);
                    map.put("k", mTest);
                    map.put("tag", "SearchList");
                    mPresenter.getDataP(map);

                }
                break;
        }
    }
    private void setHistoryTvStatus(boolean isClearAll) {
        mSearchHistoryClearAllTv.setEnabled(!isClearAll);
        if (isClearAll) {
            setHistoryTvStatus(View.VISIBLE, R.color.search_grey_gone, R.drawable.ic_clear_all_gone);
        } else {
            setHistoryTvStatus(View.GONE, R.color.search_grey, R.drawable.ic_clear_all);
        }
    }
    private void setHistoryTvStatus(int visibility, @ColorRes int textColor, @DrawableRes int clearDrawable) {
        Drawable drawable;
        mSearchHistoryNullTintTv.setVisibility(visibility);
        mSearchHistoryClearAllTv.setTextColor(ContextCompat.getColor(getActivity(), textColor));
        drawable = ContextCompat.getDrawable(getActivity(), clearDrawable);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mSearchHistoryClearAllTv.setCompoundDrawables(drawable, null, null, null);
        mSearchHistoryClearAllTv.setCompoundDrawablePadding(dp2px(6));
    }
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(float dpValue) {
        final float scale = App.getApplication().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
