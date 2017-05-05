package cn.smlcx.weather.ui.fragment;

import cn.smlcx.weather.Base.BaseFragment;
import cn.smlcx.weather.R;


public class WeatherFragment extends BaseFragment {
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_weather;
    }

    @Override
    protected void initViews() {
        mToolbar.setTitle("最新天气");
    }

    @Override
    protected void initData() {

    }
}
