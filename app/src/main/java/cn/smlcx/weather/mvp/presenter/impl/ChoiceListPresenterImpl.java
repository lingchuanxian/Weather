/*
package cn.smlcx.weather.mvp.presenter.impl;

import android.util.Log;

import cn.smlcx.weather.Bean.ChoiceBean;
import cn.smlcx.weather.Bean.HttpResult;
import cn.smlcx.weather.mvp.model.ModelContract;
import cn.smlcx.weather.mvp.presenter.PresenterContract;
import cn.smlcx.weather.mvp.view.ViewContract;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

*/
/**
 * Created by lcx on 2017/5/5.
 *//*


public class ChoiceListPresenterImpl implements PresenterContract.ChoiceListPresenter{
    private ViewContract.ChoiceListView mView;
    private ModelContract.ChoiceListModel mModel;

    public ChoiceListPresenterImpl(ViewContract.ChoiceListView view, ModelContract.ChoiceListModel model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void getChoiceList(String key, int pno, int ps) {
        mModel.getChoiceList(key,pno,ps)
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
                .doOnNext(new Action1<HttpResult<ChoiceBean>>(){
                    @Override
                    public void call(HttpResult<ChoiceBean> result) {

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Subscriber<HttpResult<ChoiceBean>>() {
                    @Override
                    public void onCompleted() {
                        Log.e("result", "complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("result", e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResult<ChoiceBean> result) {

                    }
                });

    }
}
*/
