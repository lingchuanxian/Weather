package cn.smlcx.weather.api;

import android.content.Context;

import cn.smlcx.weather.app.Constant;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 网络接口服务的包装类
 * Created by lcx on 2017/5/4.
 */
public class RetrofitWrapper2 {
    protected final String TAG = this.getClass().getSimpleName();
    private static RetrofitWrapper2 instance;
    private Context mContext;
    private Retrofit retrofit;

    private RetrofitWrapper2() {
        retrofit = new Retrofit.Builder().baseUrl(Constant.IP2)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
    /**
     * 单例模式
     *
     * @return
     */
    public static RetrofitWrapper2 getInstance() {
        if (instance == null) {
            synchronized (RetrofitWrapper2.class){
                if (instance==null){
                    instance = new RetrofitWrapper2();
                }
            }
        }
        return instance;
    }


    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

}
