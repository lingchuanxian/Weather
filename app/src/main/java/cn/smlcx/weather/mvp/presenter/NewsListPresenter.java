package cn.smlcx.weather.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import cn.smlcx.weather.Base.BasePresenter;
import cn.smlcx.weather.Bean.NewsBean;
import cn.smlcx.weather.api.ApiService;
import cn.smlcx.weather.api.RetrofitWrapper;
import cn.smlcx.weather.app.Constant;
import cn.smlcx.weather.di.scope.ActivityScope;
import cn.smlcx.weather.mvp.model.NewsListModel;
import cn.smlcx.weather.mvp.view.ViewContract;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/10.
 */
@ActivityScope
public class NewsListPresenter extends BasePresenter<NewsListModel,ViewContract.NewsListView> {
    private boolean isFirst = true;
    @Inject
    public NewsListPresenter( ViewContract.NewsListView rootView) {
        super( rootView);
    }

    public void requestNewsList(final int pageIndex){
        RetrofitWrapper.getInstance().create(ApiService.class).getNewsList("",Constant.News_key)
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if(pageIndex==1&&isFirst){
                            mRootView.showLoding();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Subscriber<NewsBean>() {
                    @Override
                    public void onCompleted() {
                        isFirst = false;
                        mRootView.hideLoding();
                    }
                    @Override
                    public void onError(Throwable e) {
                        mRootView.showErr(e.getMessage());
                    }
                    @Override
                    public void onNext(NewsBean result) {
                        List<NewsBean.ResultBean.DataBean> list = result.getResult().getData();
                        mRootView.showNewsList(list);
                    }
                });

    }
}
