package cn.smlcx.weather.ui.fragment;


import javax.inject.Inject;

import cn.smlcx.weather.Base.BaseFragment;
import cn.smlcx.weather.R;
import cn.smlcx.weather.di.component.DaggerChoiceComponent;
import cn.smlcx.weather.di.module.ChoiceModule;
import cn.smlcx.weather.mvp.presenter.ChoiceListPresenter;
import cn.smlcx.weather.mvp.view.ViewContract;

public class ChoiceFragment extends BaseFragment<ChoiceListPresenter> implements ViewContract.ChoiceListView{
    protected final String TAG = this.getClass().getSimpleName();
    @Inject
    ChoiceListPresenter mChoiceListPresenter;
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_choice;
    }

    @Override
    protected void initViews() {
        mToolbar.setTitle("微信精选");
        DaggerChoiceComponent
                .builder()
                .choiceModule(new ChoiceModule(this))
                .build()
                .inject(this);
        mPresenter.requestChoiceList("d975b5fe029c0691fe5d683cb68b86ac",1,2);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showLoding(String msg) {

    }

    @Override
    public void hideLoding() {

    }

    @Override
    public void showErr(String err) {

    }

    @Override
    public void showChoiceList() {

    }
}
