package cn.smlcx.weather.ui.fragment;

import cn.smlcx.weather.Base.BaseFragment;
import cn.smlcx.weather.R;


public class NewsFragment extends BaseFragment {
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViews() {
        mToolbar.setTitle("新闻头条");
    }

    @Override
    protected void initData() {

    }
}
