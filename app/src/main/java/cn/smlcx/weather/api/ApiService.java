package cn.smlcx.weather.api;

import cn.smlcx.weather.Bean.ChoiceBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lcx on 2017/5/4.
 */

public interface ApiService {
    /**
     * 获取微信精选列表
     * http://v.juhe.cn/weixin/query
     * @param key 申请的key
     * @param pno 页码
     * @param ps 每页条数
     * @return
     */
    @GET("/weixin/query")
    Observable<ChoiceBean> getChoiceList(@Query("key") String key, @Query("pno") int pno, @Query("ps") int ps);;
}
