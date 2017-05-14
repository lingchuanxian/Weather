package cn.smlcx.weather.mvp.model;

import cn.smlcx.weather.Base.BaseModel;
import cn.smlcx.weather.Bean.ChoiceBean;
import cn.smlcx.weather.Bean.NewsBean;
import cn.smlcx.weather.Bean.WeatherBean;
import rx.Observable;

/**
 * Created by lcx on 2017/5/8.
 */

public interface ModelContract {
    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface ChoiceListModel extends BaseModel{
        Observable<ChoiceBean> getChoiceList(String key, int pno, int ps);;
    }

    interface NewsListModel extends BaseModel{
        Observable<NewsBean> getNewsList(String type,String key);
    }

    interface WeatherModel extends BaseModel{
        Observable<WeatherBean> getWeather(String app,String weaid,String appkey,String sign, String format,Boolean isEvictCache);
    }
}
