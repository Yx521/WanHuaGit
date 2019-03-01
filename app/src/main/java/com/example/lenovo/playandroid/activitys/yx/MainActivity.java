package com.example.lenovo.playandroid.activitys.yx;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.fragments.zl.HomePageFragment;
import com.example.lenovo.playandroid.fragments.yx.ClassifyFragment;
import com.example.lenovo.playandroid.fragments.yx.ItemsFragment;
import com.example.lenovo.playandroid.fragments.wx.KnowledgeHierarchyFragment;
import com.example.lenovo.playandroid.fragments.wlg.NavigationFragment;
import com.example.lenovo.playandroid.fragments.yyj.VipcnFragment;
import com.example.lenovo.playandroid.utils.BottomNavigationViewHelper;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.common_toolbar_title_tv)
    TextView mCommonToolbarTitleTv;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fragment_group)
    FrameLayout mFragmentGroup;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.design_bottom_sheet)
    BottomNavigationView mDesignBottomSheet;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    private ClassifyFragment mClassifyFragment;
    private int index;
    private ItemsFragment mItemsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#049486")));

        mClassifyFragment = new ClassifyFragment();
        mItemsFragment = new ItemsFragment();
        //区分fragment
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                if(index==0){
                    EventBus.getDefault().post("0");
                }else if(index==1){
                    EventBus.getDefault().post("1");
                }else if(index==2){
                    EventBus.getDefault().post("2");
                }else if(index==3){
                    EventBus.getDefault().post("3");
                }else
                if(index==4){
                    EventBus.getDefault().post("4");
                }

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initBottomNavigationView();
        initDrawerLayout();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_group,new HomePageFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("ResourceType")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_item_wan_android:

                break;
            case R.id.nav_item_my_collect:

                break;
            case R.id.nav_item_setting:

                break;
            case R.id.nav_item_about_us:

                break;
            case R.id.nav_item_logout:

                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initBottomNavigationView() {
        BottomNavigationViewHelper.disableShiftMode(mDesignBottomSheet);
        mDesignBottomSheet.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_main_pager:
                        index=0;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_group,new HomePageFragment(),"0").commit();
                        break;
                    case R.id.tab_knowledge_hierarchy:
                        index=1;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_group,new KnowledgeHierarchyFragment()).commit();
                        break;
                    case R.id.tab_wx_article:
                        index=2;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_group,new VipcnFragment()).commit();
                        break;
                    case R.id.tab_navigation:
                        index=3;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_group,new NavigationFragment()).commit();
                        break;
                    case R.id.tab_project:
                        index=4;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_group,mItemsFragment,"4").commit();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

    }
    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                View mContent = mDrawerLayout.getChildAt(0);
                float scale = 1 - slideOffset;
                float endScale = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f * scale;

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
                //设置右边菜单滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);
            }
        };
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);
    }
}
