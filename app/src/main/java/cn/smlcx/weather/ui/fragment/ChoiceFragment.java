package cn.smlcx.weather.ui.fragment;


import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.paginate.Paginate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.smlcx.weather.Base.BaseFragment;
import cn.smlcx.weather.Base.BaseRecyclerViewAdapter;
import cn.smlcx.weather.Bean.ChoiceBean;
import cn.smlcx.weather.R;
import cn.smlcx.weather.di.component.DaggerChoiceComponent;
import cn.smlcx.weather.di.module.ChoiceModule;
import cn.smlcx.weather.mvp.presenter.ChoiceListPresenter;
import cn.smlcx.weather.mvp.view.ViewContract;
import cn.smlcx.weather.ui.activity.DetailActivity;
import cn.smlcx.weather.ui.adapter.ChoiceAdapter;
import cn.smlcx.weather.widget.SwipeRefreshView;

public class ChoiceFragment extends BaseFragment<ChoiceListPresenter> implements ViewContract.ChoiceListView, SwipeRefreshLayout.OnRefreshListener {
    protected final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.choice_list)
    RecyclerView mRecycleView;
    Unbinder unbinder;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    Unbinder unbinder1;
    private Paginate mPaginate;
    private boolean isLoadingMore;
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_choice;
    }

    @Override
    protected void initViews() {
        mToolbar.setTitle("微信精选");
        DaggerChoiceComponent
                .builder()
                .choiceModule(new ChoiceModule(this))
                .build()
                .inject(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecycleView.setLayoutManager(linearLayoutManager);
       /* mAdapter = new ChoiceAdapter(mChoice);
        mRecycleView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int viewType, Object data, int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("url", ((ChoiceBean.ResultBean.ListBean) data).getUrl());
                intent.putExtra("title", ((ChoiceBean.ResultBean.ListBean) data).getTitle());
                startActivity(intent);
            }
        });*/
    }

    @Override
    protected void initData() {
        mPresenter.requestChoiceList(true);
    }

    @Override
    public void onRefresh() {
        mPresenter.requestChoiceList(true);
    }

    @Override
    public void showLoding(String msg) {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoding() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showErr(String err) {

    }

    @Override
    public void showChoiceList(BaseRecyclerViewAdapter mAdapter) {
        mRecycleView.setAdapter(mAdapter);
        initPaginate();
        initRecycleView();

       /* mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int viewType, Object data, int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("url", ((ChoiceBean.ResultBean.ListBean) data).getUrl());
                intent.putExtra("title", ((ChoiceBean.ResultBean.ListBean) data).getTitle());
                startActivity(intent);
            }
        });*/
    }

    @Override
    public void startLoadMore() {
        isLoadingMore = true;
    }

    @Override
    public void endLoadMore() {
        isLoadingMore = false;
    }

    /**
     * 初始化Paginate,用于加载更多
     */
    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.requestChoiceList(false);
                }

                @Override
                public boolean isLoading() {
                    return false;
                }

                @Override
                public boolean hasLoadedAllItems() {
                    return false;
                }
            };

            mPaginate = Paginate.with(mRecycleView, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }

    /**
     * 初始化RecycleView
     */
    private void initRecycleView() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
