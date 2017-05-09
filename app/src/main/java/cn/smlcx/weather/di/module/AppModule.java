package cn.smlcx.weather.di.module;

import javax.inject.Singleton;

import cn.smlcx.weather.app.App;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jess on 8/4/16.
 */
@Module
public class AppModule {
    private App mApplication;

    public AppModule(App application) {
        this.mApplication = application;
    }

    @Singleton
    @Provides
    public App provideApplication() {
        return mApplication;
    }
}
