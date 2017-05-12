package cn.smlcx.weather.api.cache;


import java.io.File;

import cn.smlcx.weather.Bean.ChoiceBean;
import cn.smlcx.weather.api.ApiService;
import cn.smlcx.weather.app.Constant;
import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.Reply;
import io.rx_cache.internal.RxCache;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class Repository {

    public static Repository init(File cacheDir) {
        return new Repository(cacheDir);
    }

    private final CacheProviders cacheProviders;
    private final ApiService api;

    public Repository(File cacheDir) {
        cacheProviders = new RxCache.Builder()
                .persistence(cacheDir)
                .using(CacheProviders.class);

        api = new Retrofit.Builder()
                .baseUrl(Constant.IP)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService.class);
    }

    public Observable<Reply<ChoiceBean>> getChoiceList(String key,int pno,int ps,final boolean update){
        return cacheProviders.getChoiceList(api.getChoiceList(key,pno,ps),new DynamicKey(pno), new EvictDynamicKey(update));
    }


    private static class ResponseError {
        private final String message;

        public ResponseError(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
