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


public class NewsFragment extends BaseFragment<NewsListPresenter> implements ViewContract.NewsListView, SwipeRefreshLayout.OnRefreshListener {
    protected final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.choice_list)
    RecyclerView mRecycleView;
    @BindView(swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    Unbinder unbinder;
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    Unbinder unbinder1;
    private BaseAdapter mAdapter;
    private List<NewsBean.ResultBean.DataBean> mData = new ArrayList<>();
    private int pageIndex = 1;
    boolean isLoading;
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViews() {
        mToolbar.setTitle("新闻头条");
        initRecycleView();
        mAdapter = new NewsAdapter(getActivity(), mData);
        mRecycleView.setAdapter(mAdapter);
    }

    private void initRecycleView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecycleView.setLayoutManager(linearLayoutManager);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == mAdapter.getItemCount()) {
                        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
                    }
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.requestNewsList(1);
    }
    @Override
    protected void initInjector() {
        DaggerNewsComponent
                .builder()
                .newsModule(new NewsModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        mPresenter.requestNewsList(pageIndex);
    }

    @Override
    public void showLoding() {
        mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
    }

    @Override
    public void hideLoding() {
        mEmptyLayout.hide();
    }

    @Override
    public void showErr(String err) {

    }
    @Override
    public void showNewsList(List<NewsBean.ResultBean.DataBean> mList) {
        if (pageIndex == 1) {
            mData.clear();
        }
        mAdapter.addAll(mList);
        mSwipeRefreshLayout.setRefreshing(false);
        isLoading = false;
        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
    }
}
