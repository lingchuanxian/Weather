package cn.smlcx.weather.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.yinglan.alphatabs.AlphaTabView;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.smlcx.weather.Base.BaseActivity;
import cn.smlcx.weather.R;
import cn.smlcx.weather.mvp.presenter.ChoiceListPresenter;
import cn.smlcx.weather.ui.adapter.ViewPageAdapter;

public class MainActivity extends BaseActivity<ChoiceListPresenter> {
    protected final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator mAlphaIndicator;
    @BindView(R.id.weather)
    AlphaTabView mWeather;
    @BindView(R.id.choices)
    AlphaTabView mChoices;
    @BindView(R.id.news)
    AlphaTabView mNews;
    @BindView(R.id.me)
    AlphaTabView mMe;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mViewPager.setAdapter(new ViewPageAdapter(getSupportFragmentManager()));
        mAlphaIndicator.setViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(3);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
