package cn.smlcx.weather.mvp.model;

import cn.smlcx.weather.Base.BaseModel;
import cn.smlcx.weather.Bean.NewsBean;
import cn.smlcx.weather.Bean.WeatherBean;
import cn.smlcx.weather.api.ApiService;
import cn.smlcx.weather.api.RetrofitWrapper;
import cn.smlcx.weather.api.RetrofitWrapper2;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/10.
 */

public class WeatherModel extends BaseModel implements ModelContract.WeatherModel{

    @Override
    public Observable<WeatherBean> getWeather(String app, String weaid, String appkey, String sign, String format) {
        return RetrofitWrapper2.getInstance().create(ApiService.class).getWeather(app,weaid,appkey,sign,format);
    }
}
