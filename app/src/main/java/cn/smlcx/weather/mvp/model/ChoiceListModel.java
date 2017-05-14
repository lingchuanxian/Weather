package cn.smlcx.weather.mvp.model;

import javax.inject.Inject;

import cn.smlcx.weather.Base.BaseModel;
import cn.smlcx.weather.Bean.ChoiceBean;
import cn.smlcx.weather.api.ApiService;
import cn.smlcx.weather.api.RetrofitWrapper;
import cn.smlcx.weather.di.scope.ActivityScope;
import rx.Observable;

/**
 * Created by lcx on 2017/5/8.
 */
@ActivityScope
public class ChoiceListModel implements ModelContract.ChoiceListModel{
    @Inject
    public ChoiceListModel() {
    }

    @Override
    public Observable<ChoiceBean> getChoiceList(String key, int pno, int ps) {

        return RetrofitWrapper.getInstance().create(ApiService.class).getChoiceList(key,pno,ps);
    }
}
