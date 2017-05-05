package cn.smlcx.weather.ui.fragment;


import cn.smlcx.weather.Base.BaseFragment;
import cn.smlcx.weather.R;

public class ChoiceFragment extends BaseFragment {
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_choice;
    }

    @Override
    protected void initViews() {
        mToolbar.setTitle("微信精选");
    }

    @Override
    protected void initData() {

    }
}
