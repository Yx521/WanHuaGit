package com.example.lenovo.playandroid.activitys.wlg;

import android.animation.ValueAnimator;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.utils.StatusBarUtil;
import com.example.lenovo.playandroid.utils.interpolator.ElasticOutInterpolator;
import com.scwang.smartrefresh.header.FlyRefreshHeader;
import com.scwang.smartrefresh.header.flyrefresh.FlyView;
import com.scwang.smartrefresh.header.flyrefresh.MountainSceneView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.about_us_mountain)
    MountainSceneView aboutUsMountain;
    @BindView(R.id.about_us_toolbar)
    Toolbar aboutUsToolbar;
    @BindView(R.id.about_us_toolbar_layout)
    CollapsingToolbarLayout aboutUsToolbarLayout;
    @BindView(R.id.about_us_app_bar)
    AppBarLayout aboutUsAppBar;
    @BindView(R.id.about_us_fly_refresh)
    FlyRefreshHeader aboutUsFlyRefresh;
    @BindView(R.id.aboutVersion)
    TextView aboutVersion;
    @BindView(R.id.aboutContent)
    TextView aboutContent;
    @BindView(R.id.about_us_content)
    NestedScrollView aboutUsContent;
    @BindView(R.id.about_us_refresh_layout)
    SmartRefreshLayout aboutUsRefreshLayout;
    @BindView(R.id.about_us_fab)
    FloatingActionButton aboutUsFab;
    @BindView(R.id.about_us_fly_view)
    FlyView aboutUsFlyView;
    private View.OnClickListener mThemeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        showAboutContent();
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, aboutUsToolbar);
        setSupportActionBar(aboutUsToolbar);
        setSmartRefreshLayout();

        //进入界面时自动刷新
        aboutUsRefreshLayout.autoRefresh();

        //点击悬浮按钮时自动刷新
        aboutUsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutUsRefreshLayout.autoRefresh();
            }
        });

        //监听 AppBarLayout 的关闭和开启 给 FlyView（纸飞机） 和 ActionButton 设置关闭隐藏动画
        aboutUsAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean misAppbarExpand = true;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int scrollRange = appBarLayout.getTotalScrollRange();
                float fraction = 1f * (scrollRange + verticalOffset) / scrollRange;
                double minFraction = 0.1;
                double maxFraction = 0.8;
                if (aboutUsContent == null || aboutUsFab == null || aboutUsFlyView == null) {
                    return;
                }
                if (fraction < minFraction && misAppbarExpand) {
                    misAppbarExpand = false;
                    aboutUsFab.animate().scaleX(0).scaleY(0);
                    aboutUsFlyView.animate().scaleX(0).scaleY(0);
                    ValueAnimator animator = ValueAnimator.ofInt(aboutContent.getPaddingTop(), 0);
                    animator.setDuration(300);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            if (aboutContent != null) {
                                aboutContent.setPadding(0, (int) animation.getAnimatedValue(), 0, 0);
                            }
                        }


                    });
                    animator.start();
                }
                if (fraction > maxFraction && !misAppbarExpand) {
                    misAppbarExpand = true;
                    aboutUsFab.animate().scaleX(1).scaleY(1);
                    aboutUsFlyView.animate().scaleX(1).scaleY(1);
                    ValueAnimator animator = ValueAnimator.ofInt(aboutContent.getPaddingTop(), DensityUtil.dp2px(25));
                    animator.setDuration(300);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            if (aboutContent != null) {
                                aboutContent.setPadding(0, (int) animation.getAnimatedValue(), 0, 0);
                            }
                        }


                    });
                    animator.start();
                }
            }
        });
        aboutUsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void showAboutContent() {
        aboutContent.setText(Html.fromHtml(getString(R.string.about_content)));
        aboutContent.setMovementMethod(LinkMovementMethod.getInstance());
        try {
            String versionStr = getString(R.string.awesome_wan_android)
                    + " V" + getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            aboutVersion.setText(versionStr);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void setSmartRefreshLayout() {
        //绑定场景和纸飞机
        aboutUsFlyRefresh.setUp(aboutUsMountain, aboutUsFlyView);
        aboutUsRefreshLayout.setReboundInterpolator(new ElasticOutInterpolator());
        aboutUsRefreshLayout.setReboundDuration(800);
        aboutUsRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                updateTheme();
                refreshLayout.finishRefresh(1000);
            }
        });

        //设置让Toolbar和AppBarLayout的滚动同步
        aboutUsRefreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                refreshLayout.finishRefresh(2000);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                refreshLayout.finishLoadMore(3000);
            }

            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                super.onHeaderMoving(header, isDragging, percent, offset, headerHeight, maxDragHeight);
                if (aboutUsAppBar == null || aboutUsToolbar == null) {
                    return;
                }
                aboutUsAppBar.setTranslationY(offset);
                aboutUsToolbar.setTranslationY(-offset);
            }
        });
    }
    private void updateTheme() {
        if (mThemeListener == null) {
            mThemeListener = new View.OnClickListener() {
                int index = 0;
                int[] ids = new int[]{
                        R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_red_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_blue_bright,
                };

                @Override
                public void onClick(View v) {
                    int color = ContextCompat.getColor(getApplication(), ids[index % ids.length]);
                    aboutUsRefreshLayout.setPrimaryColors(color);
                    aboutUsFab.setBackgroundColor(color);
                    aboutUsFab.setBackgroundTintList(ColorStateList.valueOf(color));
                    aboutUsToolbarLayout.setContentScrimColor(color);
                    index++;
                }
            };
        }
        mThemeListener.onClick(null);
    }
}
