package cn.smlcx.weather.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import java.util.List;

import cn.smlcx.weather.Base.BaseFragment;
import cn.smlcx.weather.Bean.NewsBean;
import cn.smlcx.weather.R;
import cn.smlcx.weather.mvp.presenter.NewsListPresenter;
import cn.smlcx.weather.mvp.view.ViewContract;


public class IntoTwoFragment extends BaseFragment<NewsListPresenter> implements ViewContract.NewsListView, SwipeRefreshLayout.OnRefreshListener {
    protected final String TAG = this.getClass().getSimpleName();
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_into_two;
    }

    @Override
    protected void initViews() {
        mToolbar.setVisibility(View.GONE);
    }


    @Override
    protected void initData() {
    }
    @Override
    protected void initInjector() {
    }

    @Override
    public void onRefresh() {
    }

    @Override
    public void showNewsList(List<NewsBean.ResultBean.DataBean> mList) {
    }
}
