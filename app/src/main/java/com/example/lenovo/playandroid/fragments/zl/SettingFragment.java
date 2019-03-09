package com.example.lenovo.playandroid.fragments.zl;

import android.content.Intent;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.playandroid.R;
import com.example.lenovo.playandroid.activitys.yx.MainActivity;
import com.example.lenovo.playandroid.base.fragment.BaseFragment;
import com.example.lenovo.playandroid.dao.DataBaseMannger;
import com.example.lenovo.playandroid.dao.DecisionGlide;
import com.example.lenovo.playandroid.presenter.zl.ZlPresenter;
import com.example.lenovo.playandroid.utils.DataCleanManager;
import com.example.lenovo.playandroid.utils.ShareUtil;
import com.example.lenovo.playandroid.view.zl.ZlView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 18 on 2019/3/5.
 */

public class SettingFragment extends BaseFragment<ZlView, ZlPresenter<ZlView>> implements ZlView {

    @BindView(R.id.cb_setting_cache)
    AppCompatCheckBox mCbSettingCache;
    @BindView(R.id.cb_setting_image)
    AppCompatCheckBox mCbSettingImage;
    @BindView(R.id.cb_setting_night)
    AppCompatCheckBox mCbSettingNight;
    @BindView(R.id.ll_setting_feedback)
    TextView mLlSettingFeedback;
    @BindView(R.id.tv_setting_clear)
    TextView mTvSettingClear;
    @BindView(R.id.ll_setting_clear)
    LinearLayout mLlSettingClear;


    @Override
    public void BannerData(Object bannerdata) {

    }

    @Override
    public void MainData(Object maindata) {

    }

    @Override
    public void Login(Object logindata) {

    }

    @Override
    public void Register(Object registerdata) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected int creatrLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initData() {

        List<DecisionGlide> glideList = DataBaseMannger.getIntrance().selectGlide();
        Boolean isbo = glideList.get(0).getIsbo();
        Boolean isbo1 = glideList.get(1).getIsbo();
        Boolean isbo2 = glideList.get(2).getIsbo();
        mCbSettingNight.setChecked(isbo1);
        mCbSettingImage.setChecked(isbo);
        mCbSettingImage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                List<DecisionGlide> decisionGlides = DataBaseMannger.getIntrance().selectGlide();
                Long id = decisionGlides.get(0).getId();
                DataBaseMannger.getIntrance().updateGlide(new DecisionGlide(id,isChecked));
            }
        });

        mCbSettingNight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    List<DecisionGlide> decisionGlides = DataBaseMannger.getIntrance().selectGlide();
                    Long id = decisionGlides.get(1).getId();
                    DataBaseMannger.getIntrance().updateGlide(new DecisionGlide(id,isChecked));
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra("a",false);
                    startActivity(intent);
                    MainActivity activity= (MainActivity) getActivity();
                    activity.finish();
                }else {
                    List<DecisionGlide> decisionGlides = DataBaseMannger.getIntrance().selectGlide();
                    Long id = decisionGlides.get(1).getId();
                    DataBaseMannger.getIntrance().updateGlide(new DecisionGlide(id,isChecked));
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra("a",false);
                    startActivity(intent);
                    MainActivity activity= (MainActivity) getActivity();
                    activity.finish();
                }
            }
        });
        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(getContext());
            mTvSettingClear.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected ZlPresenter<ZlView> createPresenter() {
        return new ZlPresenter<>();
    }


    @OnClick({R.id.cb_setting_cache, R.id.setting_auto_cache_group, R.id.cb_setting_image, R.id.cb_setting_night, R.id.ll_setting_feedback, R.id.ll_setting_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_setting_cache:
                break;
            case R.id.setting_auto_cache_group:
                break;
            case R.id.cb_setting_image:
                break;
            case R.id.cb_setting_night:
                break;
            case R.id.ll_setting_feedback:
                ShareUtil.sendEmail(mContext, "");
                break;
            case R.id.ll_setting_clear:
                DataCleanManager.clearAllCache(mContext);
                try {
                    String totalCacheSize = DataCleanManager.getTotalCacheSize(mContext);
                    mTvSettingClear.setText(totalCacheSize);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }
    }
}
