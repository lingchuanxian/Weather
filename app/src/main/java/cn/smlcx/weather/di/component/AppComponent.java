package cn.smlcx.weather.di.component;

import javax.inject.Singleton;

import cn.smlcx.weather.app.App;
import cn.smlcx.weather.di.module.AppModule;
import dagger.Component;

/**
 * Created by jess on 8/4/16.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    App app();
}
