package cn.smlcx.weather.ui.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import cn.smlcx.weather.R;
import cn.smlcx.weather.utils.ToActivityUtil;

/**
 * Created by lcx on 2017/5/17.
 */

public class SplashActivity extends AppIntro {
    @Override
    public void init(Bundle savedInstanceState) {

        // Add your slide's fragments here.
        // AppIntro will automatically generate the dots indcator and buttons.
        addSlide(AppIntroFragment.newInstance("测试标题", "测试描述", R.mipmap.into_1, Color.parseColor("#2196F3")));
        addSlide(AppIntroFragment.newInstance("测试标题", "测试描述", R.mipmap.into_2, Color.parseColor("#2196F3")));
        //addSlide(new IntoTwoFragment());

        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));        // Hide Skip/Done button.
        showSkipButton(false);
        setProgressButtonEnabled(true);        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permisssion in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);
        setFadeAnimation();
        //setZoomAnimation();
        setFlowAnimation();
       //setSlideOverAnimation();
        //setDepthAnimation();
    }

    @Override
    public void onSkipPressed() {
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed() {
        ToActivityUtil.toNextActivity(this,LoginActivity.class);
        finish();
    }
}
