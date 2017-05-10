package cn.smlcx.weather.di.module;

import cn.smlcx.weather.di.scope.FragmentScope;
import cn.smlcx.weather.mvp.model.ChoiceListModel;
import cn.smlcx.weather.mvp.model.NewsListModel;
import cn.smlcx.weather.mvp.presenter.ChoiceListPresenter;
import cn.smlcx.weather.mvp.presenter.NewsListPresenter;
import cn.smlcx.weather.mvp.view.ViewContract;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jess on 9/4/16 11:10
 * Contact with jess.yan.effort@gmail.com
 */
@Module
public class NewsModule {
    private ViewContract.NewsListView view;
    /**
     * 构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     */
    public NewsModule(ViewContract.NewsListView view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    ViewContract.NewsListView provideNewsListView(){
        return this.view;
    }

    @FragmentScope
    @Provides
    NewsListPresenter provideNewsListPresenter(){
        return new NewsListPresenter(this.view);
    }
    @FragmentScope
    @Provides
    NewsListModel provideNewsListModel(){
        return new NewsListModel();
    }
}
