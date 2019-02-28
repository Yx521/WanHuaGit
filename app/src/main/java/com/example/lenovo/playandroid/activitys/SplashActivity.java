package com.example.lenovo.playandroid.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.lenovo.playandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.one_animation)
    LottieAnimationView mOneAnimation;
    @BindView(R.id.two_animation)
    LottieAnimationView mTwoAnimation;
    @BindView(R.id.three_animation)
    LottieAnimationView mThreeAnimation;
    @BindView(R.id.four_animation)
    LottieAnimationView mFourAnimation;
    @BindView(R.id.five_animation)
    LottieAnimationView mFiveAnimation;
    @BindView(R.id.six_animation)
    LottieAnimationView mSixAnimation;
    @BindView(R.id.seven_animation)
    LottieAnimationView mSevenAnimation;
    @BindView(R.id.eight_animation)
    LottieAnimationView mEightAnimation;
    @BindView(R.id.nine_animation)
    LottieAnimationView mNineAnimation;
    @BindView(R.id.ten_animation)
    LottieAnimationView mTenAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);


        new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mOneAnimation.setAnimation("W.json");
                mOneAnimation.loop(true);
                mOneAnimation.playAnimation();
                mTwoAnimation.setAnimation("A.json");
                mTwoAnimation.loop(true);
                mTwoAnimation.playAnimation();
                mThreeAnimation.setAnimation("N.json");
                mThreeAnimation.loop(true);
                mThreeAnimation.playAnimation();
                mFourAnimation.setAnimation("A.json");
                mFourAnimation.loop(true);
                mFourAnimation.playAnimation();
                mFiveAnimation.setAnimation("N.json");
                mFiveAnimation.loop(true);
                mFiveAnimation.playAnimation();
                mSixAnimation.setAnimation("D.json");
                mSixAnimation.loop(true);
                mSixAnimation.playAnimation();
                mSevenAnimation.setAnimation("R.json");
                mSevenAnimation.loop(true);
                mSevenAnimation.playAnimation();
                mEightAnimation.setAnimation("O.json");
                mEightAnimation.loop(true);
                mEightAnimation.playAnimation();
                mNineAnimation.setAnimation("O.json");
                mNineAnimation.loop(true);
                mNineAnimation.playAnimation();
                mTenAnimation.setAnimation("I.json");
                mTenAnimation.loop(true);
                mTenAnimation.playAnimation();

            }

            @Override
            public void onFinish() {
                mOneAnimation.cancelAnimation();
                mTwoAnimation.cancelAnimation();
                mThreeAnimation.cancelAnimation();
                mFourAnimation.cancelAnimation();
                mFiveAnimation.cancelAnimation();
                mSixAnimation.cancelAnimation();
                mSevenAnimation.cancelAnimation();
                mEightAnimation.cancelAnimation();
                mNineAnimation.cancelAnimation();
                mTenAnimation.cancelAnimation();

                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
        }.start();
    }
}
