package cn.smlcx.weather.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import cn.smlcx.weather.Base.BasePresenter;
import cn.smlcx.weather.Bean.ChoiceBean;
import cn.smlcx.weather.api.ApiService;
import cn.smlcx.weather.api.RetrofitWrapper;
import cn.smlcx.weather.app.Constant;
import cn.smlcx.weather.di.scope.ActivityScope;
import cn.smlcx.weather.mvp.model.ChoiceListModel;
import cn.smlcx.weather.mvp.view.ViewContract;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/6.
 */
@ActivityScope
public class ChoiceListPresenter extends BasePresenter<ChoiceListModel,ViewContract.ChoiceListView>{
    private boolean isFirst = true;
    @Inject
    public ChoiceListPresenter(ViewContract.ChoiceListView rootView) {
        super(rootView);
    }

    public void requestChoiceList(final int pageIndex){
        RetrofitWrapper.getInstance().create(ApiService.class).getChoiceList(Constant.Choice_key,pageIndex,15)
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
                .subscribe(new Subscriber<ChoiceBean>() {
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
                    public void onNext(ChoiceBean result) {
                        List<ChoiceBean.ResultBean.ListBean> list = result.getResult().getList();
                        mRootView.showChoiceList(list);
                    }
                });

    }
}
