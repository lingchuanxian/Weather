package cn.smlcx.weather.di.module;

import cn.smlcx.weather.mvp.model.ModelContract;
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

    @Provides
    ViewContract.ChoiceListView provideChoiceListView(){
        return this.view;
    }

    @Provides
    ModelContract.ChoiceListModel provideChoiceListModel(ModelContract.ChoiceListModel model){
        return model;
    }
}
