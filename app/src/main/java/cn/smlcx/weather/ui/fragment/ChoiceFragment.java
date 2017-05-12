package cn.smlcx.weather.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.Unbinder;
import cn.smlcx.weather.Base.BaseAdapter;
import cn.smlcx.weather.Base.BaseFragment;
import cn.smlcx.weather.Bean.ChoiceBean;
import cn.smlcx.weather.R;
import cn.smlcx.weather.di.component.DaggerChoiceComponent;
import cn.smlcx.weather.di.module.ChoiceModule;
import cn.smlcx.weather.mvp.presenter.ChoiceListPresenter;
import cn.smlcx.weather.mvp.view.ViewContract;
import cn.smlcx.weather.ui.adapter.ChoiceAdapter;

import static cn.smlcx.weather.R.id.swipeRefreshLayout;

public class ChoiceFragment extends BaseFragment<ChoiceListPresenter> implements ViewContract.ChoiceListView, SwipeRefreshLayout.OnRefreshListener {
    protected final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.choice_list)
    RecyclerView mRecycleView;
    @BindView(swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    Unbinder unbinder;

    private BaseAdapter mAdapter;
    private List<ChoiceBean.ResultBean.ListBean> mData = new ArrayList<>();
    private int pageIndex = 1;
    boolean isLoading;
    @Inject
    ChoiceBean.ResultBean.ListBean data;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_choice;
    }

    @Override
    protected void initViews() {
        mToolbar.setTitle("微信精选");
        initRecycleView();
        mAdapter = new ChoiceAdapter(getActivity(), mData);
        mRecycleView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.requestChoiceList(1);
    }

    @Override
    protected void initInjector() {
        DaggerChoiceComponent
                .builder()
                .choiceModule(new ChoiceModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        mPresenter.requestChoiceList(pageIndex);
    }

    @Override
    public void showChoiceList(List<ChoiceBean.ResultBean.ListBean> mList) {
        if (pageIndex == 1) {
            mData.clear();
        }
        mAdapter.addAll(mList);
        mSwipeRefreshLayout.setRefreshing(false);
        isLoading = false;
        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
    }

    /**
     * 初始化RecycleView
     */
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
                    boolean isRefreshing = mSwipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
                        return;
                    }
                    if (!isLoading) {
                        isLoading = true;
                        pageIndex++;
                        mPresenter.requestChoiceList(pageIndex);
                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}