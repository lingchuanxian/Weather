package cn.smlcx.weather.ui.fragment;

import cn.smlcx.weather.Base.BaseFragment;
import cn.smlcx.weather.R;


public class PersonalFragment extends BaseFragment {
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initViews() {
        mToolbar.setTitle("个人中心");
    }

    @Override
    protected void initData() {

    }
    @Override
    protected void initInjector() {

    }
}
