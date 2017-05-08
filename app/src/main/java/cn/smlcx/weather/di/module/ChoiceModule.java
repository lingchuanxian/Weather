package cn.smlcx.weather.di.module;

import cn.smlcx.weather.di.scope.FragmentScope;
import cn.smlcx.weather.mvp.model.ChoiceListModel;
import cn.smlcx.weather.mvp.presenter.ChoiceListPresenter;
import cn.smlcx.weather.mvp.view.ViewContract;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jess on 9/4/16 11:10
 * Contact with jess.yan.effort@gmail.com
 */
@Module
public class ChoiceModule {
    private ViewContract.ChoiceListView view;
    /**
     * 构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     */
    public ChoiceModule(ViewContract.ChoiceListView view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    ViewContract.ChoiceListView provideChoiceListView(){
        return this.view;
    }

    @FragmentScope
    @Provides
    ChoiceListPresenter provideChoiceListPresenter(){
        return new ChoiceListPresenter(this.view);
    }
    @FragmentScope
    @Provides
    ChoiceListModel provideChoiceListModel(){
        return new ChoiceListModel();
    }
}
