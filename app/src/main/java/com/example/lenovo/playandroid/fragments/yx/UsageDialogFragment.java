package com.example.lenovo.playandroid.fragments.yx;


import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.activitys.yx.WebViewActivity;
import com.example.lenovo.playandroid.base.fragment.BaseDialogFragment;
import com.example.lenovo.playandroid.beans.yx.UsefulSiteData;
import com.example.lenovo.playandroid.global.Global;
import com.example.lenovo.playandroid.presenter.yx.UsefulSitesPresenter;
import com.example.lenovo.playandroid.utils.CircularRevealAnim;
import com.example.lenovo.playandroid.view.yx.IView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsageDialogFragment extends BaseDialogFragment<IView, UsefulSitesPresenter<IView>> implements CircularRevealAnim.AnimListener, ViewTreeObserver.OnPreDrawListener,IView {

    @BindView(R.id.common_toolbar_title_tv)
    TextView mCommonToolbarTitleTv;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.usage_scroll_view)
    NestedScrollView mUsageScrollView;
    @BindView(R.id.usage_coordinator_group)
    CoordinatorLayout mUsageCoordinatorGroup;
    Unbinder unbinder;
    @BindView(R.id.useful_sites_flow_layout)
    TagFlowLayout mUsefulSitesFlowLayout;
    Unbinder unbinder1;
    private CircularRevealAnim mCircularRevealAnim;
    private List<UsefulSiteData.DataBean> mData;

    public UsageDialogFragment() {
        // Required empty public constructor
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


    @Override
    protected int creatrLayoutId() {
        return R.layout.fragment_usage_dialog;
    }

    @Override
    protected void initData() {
        initCircleAnimation();
        mPresenter.getDataP("");
    }


    public void dismiss() {

    }

    private void initCircleAnimation() {
        mCircularRevealAnim = new CircularRevealAnim();
        mCircularRevealAnim.setAnimListener(this);
        mCommonToolbarTitleTv.getViewTreeObserver().addOnPreDrawListener(this);
        mCommonToolbarTitleTv.setText("常用网站");
        setToolbarView(R.color.title_black, R.color.white, R.drawable.ic_arrow_back_grey_24dp);
    }

    @SuppressLint("NewApi")
    private void setToolbarView(@ColorRes int textColor, @ColorRes int backgroundColor, @DrawableRes int navigationIcon) {
        mCommonToolbarTitleTv.setTextColor(ContextCompat.getColor(getContext(), textColor));
        mToolbar.setBackgroundColor(ContextCompat.getColor(getActivity(), backgroundColor));
        mToolbar.setNavigationIcon(ContextCompat.getDrawable(getContext(), navigationIcon));
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
    public void onHideAnimationEnd() {
        dismissAllowingStateLoss();
    }

    @Override
    public void onShowAnimationEnd() {

    }

    @Override
    protected UsefulSitesPresenter<IView> createPresenter() {
        return new UsefulSitesPresenter<>();
    }

    @Override
    public boolean onPreDraw() {
        mCommonToolbarTitleTv.getViewTreeObserver().removeOnPreDrawListener(this);
        mCircularRevealAnim.show(mCommonToolbarTitleTv, mView);
        return true;
    }

    @Override
    public void showError(String error) {

    }


    @Override
    public void show(Object o) {
        final UsefulSiteData usefulSiteData = (UsefulSiteData) o;
        mData = usefulSiteData.getData();
        mUsefulSitesFlowLayout.setAdapter(new TagAdapter<UsefulSiteData.DataBean>(mData) {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public View getView(FlowLayout parent, int position, UsefulSiteData.DataBean o) {
                assert getActivity() != null;
                TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.flow_layout_tv,
                        parent, false);
                assert o != null;
                String name = o.getName();
                tv.setText(name);
                tv.setTransitionName("shareView");
                setItemBackground(tv);
                mUsefulSitesFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        startUsefulSitePager(view, position);
                        return true;
                    }
                });
                return tv;
            }
        });
    }
    private void setItemBackground(TextView tv) {
        tv.setBackgroundColor(randomTagColor());
        tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startUsefulSitePager(View view, int position1) {
         ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mActivity, view, getString(R.string.share_view));
        Intent intent = new Intent();
        int id = mData.get(position1).getId();
        String name = mData.get(position1).getName();
        String link = mData.get(position1).getLink();
        intent.putExtra("id",id);
        intent.putExtra("desc",name);
        intent.putExtra("web",link);
        intent.setClass(getContext(), WebViewActivity.class);
        startActivity(intent,options.toBundle());

    }


    public static int randomTagColor() {
        int randomNum = new Random().nextInt();
        int position = randomNum % Global.TAB_COLORS.length;
        if (position < 0) {
            position = -position;
        }
        return Global.TAB_COLORS[position];
    }
}
