package cn.smlcx.weather.di.component;

import cn.smlcx.weather.di.module.ChoiceModule;
import cn.smlcx.weather.di.scope.FragmentScope;
import cn.smlcx.weather.ui.fragment.ChoiceFragment;
import dagger.Component;

/**
 * Created by lcx on 2017/5/8.
 */
@FragmentScope
@Component(modules = ChoiceModule.class)
public interface ChoiceComponent {
    void inject(ChoiceFragment fragment);
}
