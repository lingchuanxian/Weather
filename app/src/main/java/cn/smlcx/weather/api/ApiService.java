package cn.smlcx.weather.api;

import cn.smlcx.weather.Bean.ChoiceBean;
import cn.smlcx.weather.Bean.NewsBean;
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
    Observable<ChoiceBean> getChoiceList(@Query("key") String key, @Query("pno") int pno, @Query("ps") int ps);

    /**
     * 获取新闻头条列表
     * http://v.juhe.cn/toutiao/index
     * @param type 类型,,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
     * @return
     */
    @GET("/toutiao/index")
    Observable<NewsBean> getNewsList(@Query("type") String type,@Query("key") String key);
}
