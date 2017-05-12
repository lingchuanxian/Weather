package cn.smlcx.weather.api;

import cn.smlcx.weather.Bean.ChoiceBean;
import cn.smlcx.weather.Bean.NewsBean;
import cn.smlcx.weather.Bean.WeatherBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lcx on 2017/5/4.
 */

public interface ApiService {
    /**
     * 获取5-7天的天气情况
     * @param app  接口 此处应为:weather.future
     * @param weaid 城市气象编号，可以是beijing(拼音)/北京(城市名)/1(NowAPI定义的编号*推荐)/101010100(气象局编号)/202.104.153.201(ip地址)
     * @param appkey 使用API的唯一凭证
     * @param sign md5后的32位密文
     * @param format 返回数据格式（json|xml）
     * @return
     */
    @GET("/")
    Observable<WeatherBean> getWeather(@Query("app") String app, @Query("weaid") String weaid, @Query("appkey") String appkey, @Query("sign") String sign, @Query("format") String format);
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
