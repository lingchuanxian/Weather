package cn.smlcx.weather.api.cache;

import java.util.concurrent.TimeUnit;

import cn.smlcx.weather.Bean.ChoiceBean;
import io.rx_cache.DynamicKey;
import io.rx_cache.EvictProvider;
import io.rx_cache.LifeCache;
import io.rx_cache.Reply;
import rx.Observable;

/**
 * Created by lcx on 2017/5/10.
 */

public interface CacheProviders {
    //这里设置缓存失效时间为2分钟。
    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<ChoiceBean>> getChoiceList(Observable<ChoiceBean> oRepos, DynamicKey pno,EvictProvider evictProvider);
}
