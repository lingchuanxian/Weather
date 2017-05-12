package cn.smlcx.weather.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import cn.smlcx.weather.Base.BaseAdapter;
import cn.smlcx.weather.Base.BaseFragment;
import cn.smlcx.weather.Bean.NewsBean;
import cn.smlcx.weather.R;
import cn.smlcx.weather.di.component.DaggerChoiceComponent;
import cn.smlcx.weather.di.component.DaggerNewsComponent;
import cn.smlcx.weather.di.module.ChoiceModule;
import cn.smlcx.weather.di.module.NewsModule;
import cn.smlcx.weather.mvp.presenter.NewsListPresenter;
import cn.smlcx.weather.mvp.view.ViewContract;
import cn.smlcx.weather.ui.adapter.ChoiceAdapter;
import cn.smlcx.weather.ui.adapter.NewsAdapter;
import cn.smlcx.weather.widget.EmptyLayout;

import static cn.smlcx.weather.R.id.swipeRefreshLayout;


public class PersonalFragment extends BaseFragment<NewsListPresenter> implements ViewContract.NewsListView, SwipeRefreshLayout.OnRefreshListener {
    protected final String TAG = this.getClass().getSimpleName();
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

    @Override
    public void onRefresh() {
    }

    @Override
    public void showLoding() {
    }

    @Override
    public void hideLoding() {
    }

    @Override
    public void showErr(String err) {

    }
    @Override
    public void showNewsList(List<NewsBean.ResultBean.DataBean> mList) {
    }
}
