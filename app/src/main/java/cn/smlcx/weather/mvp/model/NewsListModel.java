package cn.smlcx.weather.mvp.model;

import cn.smlcx.weather.Base.BaseModel;
import cn.smlcx.weather.Bean.NewsBean;
import cn.smlcx.weather.api.ApiService;
import cn.smlcx.weather.api.RetrofitWrapper;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/10.
 */

public class NewsListModel extends BaseModel implements ModelContract.NewsListModel{
    @Override
    public Observable<NewsBean> getNewsList(String type,String key) {
        return RetrofitWrapper.getInstance().create(ApiService.class).getNewsList(type,key);
    }
}
