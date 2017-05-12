package cn.smlcx.weather.mvp.model;

import javax.inject.Inject;

import cn.smlcx.weather.Base.BaseModel;
import cn.smlcx.weather.Bean.WeatherBean;
import cn.smlcx.weather.di.scope.ActivityScope;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/10.
 */

@ActivityScope
public class WeatherModel extends BaseModel implements ModelContract.WeatherModel{
    @Inject
    public WeatherModel() {
    }

    @Override
    public Observable<WeatherBean> getWeather(String app, String weaid, String appkey, String sign, String format,Boolean isEvictCache) {
        return mService.getWeather(app,weaid,appkey,sign,format);
    }
}
