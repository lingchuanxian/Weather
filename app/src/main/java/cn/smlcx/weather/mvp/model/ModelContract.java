package cn.smlcx.weather.mvp.model;

import cn.smlcx.weather.Bean.ChoiceBean;
import cn.smlcx.weather.Bean.HttpResult;
import cn.smlcx.weather.Bean.NewsBean;
import cn.smlcx.weather.Bean.WeatherBean;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lcx on 2017/5/8.
 */

public class ModelContract {
    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface ChoiceListModel{
        Observable<ChoiceBean> getChoiceList(String key, int pno, int ps);;
    }

    interface NewsListModel{
        Observable<NewsBean> getNewsList(String type,String key);
    }

    interface WeatherModel{
        Observable<WeatherBean> getWeather(String app,String weaid,String appkey,String sign, String format);
    }
}
