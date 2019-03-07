package com.example.lenovo.playandroid.activitys.yx;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.beans.zl.LoginData;
import com.example.lenovo.playandroid.dao.LogDaoBean;
import com.example.lenovo.playandroid.dao.LoginManager;
import com.example.lenovo.playandroid.fragments.yx.SearchFragment;
import com.example.lenovo.playandroid.fragments.yx.UsageDialogFragment;
import com.example.lenovo.playandroid.activitys.zl.LoginActivity;
import com.example.lenovo.playandroid.fragments.zl.HomePageFragment;
import com.example.lenovo.playandroid.fragments.yx.ClassifyFragment;
import com.example.lenovo.playandroid.fragments.yx.ItemsFragment;
import com.example.lenovo.playandroid.fragments.wx.KnowledgeHierarchyFragment;
import com.example.lenovo.playandroid.fragments.wlg.NavigationFragment;
import com.example.lenovo.playandroid.fragments.yyj.VipcnFragment;
import com.example.lenovo.playandroid.fragments.zl.SettingFragment;
import com.example.lenovo.playandroid.global.Global;
import com.example.lenovo.playandroid.http.ApiServer;
import com.example.lenovo.playandroid.http.HttpManager;
import com.example.lenovo.playandroid.http.SaveCookiesInterceptor;
import com.example.lenovo.playandroid.utils.BottomNavigationViewHelper;
import com.example.lenovo.playandroid.utils.CircularRevealAnim;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
    private UsageDialogFragment usageDialogFragment;
    private CircularRevealAnim mCircularRevealAnim;
    private SearchFragment mSearchFragment;
    private TextView mLogin;
    private long exitTime;

    private MenuItem mItem;
    private List<LogDaoBean> mSelect;
    private boolean mIsLogin;
    private AlertDialog mAlertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        View headerView = mNavView.getHeaderView(0);
        mLogin = headerView.findViewById(R.id.nav_header_login_tv);

        mSelect = LoginManager.mMySqlHelper().select();
        if (mSelect.size() <= 0) {
            LoginManager.mMySqlHelper().insert(new LogDaoBean(null, "登录", false));
        }

        Menu menu = mNavView.getMenu();
        mItem = menu.findItem(R.id.nav_item_logout);


        loginListener();

        mSelect = LoginManager.mMySqlHelper().select();
        LogDaoBean logDaoBean = mSelect.get(0);
        mIsLogin = logDaoBean.getIsLogin();
        String name = logDaoBean.getName();
        Log.e("name", name);
        if (mIsLogin == true) {
            mLogin.setText(name + "");
            mItem.setVisible(true);
            mLogin.setClickable(false);
        } else if (mIsLogin == false) {
            mItem.setVisible(false);
        }

        EventBus.getDefault().register(this);
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
                if (index == 0) {
                    EventBus.getDefault().post("0");
                } else if (index == 1) {
                    EventBus.getDefault().post("1");
                } else if (index == 2) {
                    EventBus.getDefault().post("2");
                } else if (index == 3) {
                    EventBus.getDefault().post("3");
                } else if (index == 4) {
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


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_group, new HomePageFragment()).commit();
    }

    private void loginListener() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
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

    @SuppressLint("NewApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_usage:
                if (usageDialogFragment == null) {
                    usageDialogFragment = new UsageDialogFragment();
                }
                if (!isDestroyed() && usageDialogFragment.isAdded()) {
                    usageDialogFragment.dismiss();
                }
                usageDialogFragment.show(getSupportFragmentManager(), "UsageDialogFragment");
                break;
            case R.id.action_search:
                if (mSearchFragment == null) {
                    mSearchFragment = new SearchFragment();
                }
                if (!isDestroyed() && mSearchFragment.isAdded()) {
                    mSearchFragment.dismiss();
                }
                mSearchFragment.show(getSupportFragmentManager(), "SearchFragment");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressLint("ResourceType")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        switch (id) {
            case R.id.nav_item_wan_android:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_group, new HomePageFragment(), "0").commit();
                mDesignBottomSheet.setVisibility(View.VISIBLE);
                mFab.setVisibility(View.VISIBLE);
                mCommonToolbarTitleTv.setText(getString(R.string.home_pager));
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_item_my_collect:
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_item_setting:
                mDesignBottomSheet.setVisibility(View.GONE);
                mFab.setVisibility(View.GONE);
                mCommonToolbarTitleTv.setText(getString(R.string.setting));
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_group, new SettingFragment());
                fragmentTransaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_item_about_us:

                break;
            case R.id.nav_item_logout:
                logout();
                break;

        }

        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @SuppressLint("NewApi")
    private void logout() {


        View inflate = LayoutInflater.from(this).inflate(R.layout.common_alert_dialog, null);
        Button ok = inflate.findViewById(R.id.dialog_btn);
        Button on = inflate.findViewById(R.id.dialog_negative_btn);
        mAlertDialog = new AlertDialog.Builder(this)
                .setView(inflate)
                .create();

        mAlertDialog.show();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
            }
        });
    }

    private void exit() {
        ApiServer loginServer = HttpManager.getInstance().getLoginServer();
        loginServer.logout().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginData value) {
                        mAlertDialog.dismiss();
                        mDrawerLayout.openDrawer(Gravity.LEFT);
                        SaveCookiesInterceptor.clearCookie(MainActivity.this);
                        mSelect = new LoginManager().select();
                        if (value.getErrorMsg().equals("")) {
                            mItem.setVisible(false);
                            mLogin.setClickable(true);
                            LogDaoBean logDaoBean = mSelect.get(0);
                            logDaoBean.setIsLogin(false);
                            logDaoBean.setName("登录");
                            LoginManager.mMySqlHelper().updata(logDaoBean);
                            mSelect = LoginManager.mMySqlHelper().select();
                            mLogin.setText(mSelect.get(0).getName());
                            Intent intent = new Intent();
                            intent.setClass(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void initBottomNavigationView() {
        BottomNavigationViewHelper.disableShiftMode(mDesignBottomSheet);
        mDesignBottomSheet.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_main_pager:
                        index = 0;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_group, new HomePageFragment(), "0").commit();
                        break;
                    case R.id.tab_knowledge_hierarchy:
                        index = 1;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_group, new KnowledgeHierarchyFragment()).commit();
                        break;
                    case R.id.tab_wx_article:
                        index = 2;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_group, new VipcnFragment()).commit();
                        break;
                    case R.id.tab_navigation:
                        index = 3;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_group, new NavigationFragment()).commit();
                        break;
                    case R.id.tab_project:
                        index = 4;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_group, mItemsFragment, "4").commit();
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
            @SuppressLint("NewApi")
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Snackbar.make(mNavView, getString(R.string.double_click_exit_tint), Snackbar.LENGTH_SHORT)
                        .setActionTextColor(Color.parseColor("#049486"))
                        .setAction("知道了", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void login(String name) {
        mLogin.setText(name);
        mItem.setVisible(true);
        mLogin.setClickable(false);
        Log.e("name", name);


    }
}
