package cn.smlcx.weather.di.module;

import cn.smlcx.weather.di.scope.FragmentScope;
import cn.smlcx.weather.mvp.model.NewsListModel;
import cn.smlcx.weather.mvp.model.WeatherModel;
import cn.smlcx.weather.mvp.presenter.NewsListPresenter;
import cn.smlcx.weather.mvp.presenter.WeatherPresenter;
import cn.smlcx.weather.mvp.view.ViewContract;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jess on 9/4/16 11:10
 * Contact with jess.yan.effort@gmail.com
 */
@Module
public class WeatherModule {
    private ViewContract.WeatherView view;
    /**
     * 构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     */
    public WeatherModule(ViewContract.WeatherView view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    ViewContract.WeatherView provideWeatherView(){
        return this.view;
    }

    @FragmentScope
    @Provides
    WeatherPresenter provideWeatherPresenter(){
        return new WeatherPresenter(this.view);
    }
    @FragmentScope
    @Provides
    WeatherModel provideWeatherModel(){
        return new WeatherModel();
    }
}
