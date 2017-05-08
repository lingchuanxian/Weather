package cn.smlcx.weather.mvp.model;

import java.util.List;

import cn.smlcx.weather.Base.BaseModel;
import cn.smlcx.weather.Bean.ChoiceBean;
import cn.smlcx.weather.Bean.HttpResult;
import rx.Observable;

/**
 * Created by lcx on 2017/5/5.
 */

public interface ModelContract {
    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface ChoiceListModel extends BaseModel{
        Observable<HttpResult<ChoiceBean>> getChoiceList(String key, int pno, int ps);
    }
}
