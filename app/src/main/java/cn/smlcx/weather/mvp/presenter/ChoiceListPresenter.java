package cn.smlcx.weather.mvp.presenter;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cn.smlcx.weather.Base.BasePresenter;
import cn.smlcx.weather.Base.BaseRecyclerViewAdapter;
import cn.smlcx.weather.Bean.ChoiceBean;
import cn.smlcx.weather.api.ApiService;
import cn.smlcx.weather.api.RetrofitWrapper;
import cn.smlcx.weather.app.Constant;
import cn.smlcx.weather.di.scope.ActivityScope;
import cn.smlcx.weather.mvp.model.ChoiceListModel;
import cn.smlcx.weather.mvp.view.ViewContract;
import cn.smlcx.weather.ui.activity.DetailActivity;
import cn.smlcx.weather.ui.adapter.ChoiceAdapter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/6.
 */
@ActivityScope
public class ChoiceListPresenter extends BasePresenter<ChoiceListModel,ViewContract.ChoiceListView>{
   private List<ChoiceBean.ResultBean.ListBean> mList = new ArrayList<>();
    private int pageIndex = 1;
    private BaseRecyclerViewAdapter mAdapter;
    @Inject
    public ChoiceListPresenter(ViewContract.ChoiceListView rootView) {
        super(rootView);
    }

    public void requestChoiceList(final Boolean pullToRefresh){
        if (mAdapter == null) {
            mAdapter = new ChoiceAdapter(mList);
            mRootView.showChoiceList(mAdapter);
        }
        if(!pullToRefresh){
            pageIndex++;
        }
        RetrofitWrapper.getInstance().create(ApiService.class).getChoiceList(Constant.Choice_key,pageIndex,10)
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行

                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        if (pullToRefresh) {
                            mRootView.hideLoding();//隐藏上拉刷新的进度条
                        }else{
                            mRootView.endLoadMore();//隐藏下拉加载更多的进度条
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Subscriber<ChoiceBean>() {
                    @Override
                    public void onCompleted() {
                        if (pullToRefresh) {
                            mRootView.hideLoding();//隐藏上拉刷新的进度条
                        }else{
                            mRootView.endLoadMore();//隐藏下拉加载更多的进度条
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("result", e.getMessage());
                    }

                    @Override
                    public void onNext(ChoiceBean result) {
                        List<ChoiceBean.ResultBean.ListBean> list = result.getResult().getList();
                        if(pullToRefresh){
                            mList = list;
                        }else{
                            mList.addAll(list);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });

    }
}
