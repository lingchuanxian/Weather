package cn.smlcx.weather.di.component;

import cn.smlcx.weather.di.module.ChoiceModule;
import cn.smlcx.weather.di.module.NewsModule;
import cn.smlcx.weather.di.scope.FragmentScope;
import cn.smlcx.weather.ui.fragment.ChoiceFragment;
import cn.smlcx.weather.ui.fragment.NewsFragment;
import dagger.Component;

/**
 * Created by lcx on 2017/5/8.
 */
@FragmentScope
@Component(modules = NewsModule.class)
public interface NewsComponent {
    void inject(NewsFragment fragment);
}
