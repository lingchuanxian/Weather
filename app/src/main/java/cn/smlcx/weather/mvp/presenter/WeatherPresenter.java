package cn.smlcx.weather.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import cn.smlcx.weather.Base.BasePresenter;
import cn.smlcx.weather.Bean.NewsBean;
import cn.smlcx.weather.Bean.WeatherBean;
import cn.smlcx.weather.api.ApiService;
import cn.smlcx.weather.api.RetrofitWrapper;
import cn.smlcx.weather.api.RetrofitWrapper2;
import cn.smlcx.weather.app.Constant;
import cn.smlcx.weather.di.scope.ActivityScope;
import cn.smlcx.weather.mvp.model.NewsListModel;
import cn.smlcx.weather.mvp.model.WeatherModel;
import cn.smlcx.weather.mvp.view.ViewContract;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

import static android.R.attr.type;

/**
 * Created by Administrator on 2017/5/10.
 */
@ActivityScope
public class WeatherPresenter extends BasePresenter<WeatherModel,ViewContract.WeatherView> {
    private boolean isFirst = true;
    @Inject
    public WeatherPresenter(ViewContract.WeatherView rootView) {
        super( rootView);
    }

    public void requestWeather(final String weaid){
        RetrofitWrapper2.getInstance().create(ApiService.class).getWeather("weather.future",weaid,"25328","f1ee45ba1618e8076d0f011315370e84","json")
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if(isFirst){
                            mRootView.showLoding();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Subscriber<WeatherBean>() {
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
                    public void onNext(WeatherBean result) {
                        mRootView.showWeather(result);
                    }
                });

    }
}
