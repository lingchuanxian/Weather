package cn.smlcx.weather.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import cn.smlcx.weather.Base.BaseAdapter;
import cn.smlcx.weather.Base.BaseFragment;
import cn.smlcx.weather.Bean.NewsBean;
import cn.smlcx.weather.R;
import cn.smlcx.weather.di.component.DaggerNewsComponent;
import cn.smlcx.weather.di.module.NewsModule;
import cn.smlcx.weather.mvp.presenter.NewsListPresenter;
import cn.smlcx.weather.mvp.view.ViewContract;
import cn.smlcx.weather.ui.adapter.NewsAdapter;
import cn.smlcx.weather.widget.EmptyLayout;

import static cn.smlcx.weather.R.id.swipeRefreshLayout;


public class CommonNewsFragment extends BaseFragment<NewsListPresenter> implements ViewContract.NewsListView, SwipeRefreshLayout.OnRefreshListener {
    private String mType;
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
    boolean isLoading;

    public static CommonNewsFragment getInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("type",type);
        CommonNewsFragment cf = new CommonNewsFragment();
        cf.setArguments(bundle);
        return cf;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_choice;
    }

    @Override
    protected void initViews() {
        mToolbar.setVisibility(View.GONE);
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
        mPresenter.requestNewsList(getBundleString());
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
        mPresenter.requestNewsList(getBundleString());
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
        mData.clear();
        mAdapter.addAll(mList);
        mSwipeRefreshLayout.setRefreshing(false);
        isLoading = false;
        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
    }

    public String getBundleString(){
        Bundle bundle = this.getArguments();
        if(bundle != null){
            return bundle.getString("type");
        }else{
            return "";
        }
    }
}