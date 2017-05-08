package cn.smlcx.weather.api;

import java.util.List;

import cn.smlcx.weather.Bean.ChoiceBean;
import cn.smlcx.weather.Bean.HttpResult;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lcx on 2017/5/4.
 */

public interface ApiService {
    @GET("/weixin/query")
    Observable<HttpResult<ChoiceBean>> getChoiceList(@Query("key") String key, @Query("pno") int pno, @Query("ps") int ps);;
}
