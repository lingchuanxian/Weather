package cn.smlcx.weather.ui.activity;

import android.support.v4.view.ViewPager;
import android.util.Log;

import com.lzy.widget.AlphaIndicator;

import butterknife.BindView;
import cn.smlcx.weather.Base.BaseActivity;
import cn.smlcx.weather.Bean.ChoiceBean;
import cn.smlcx.weather.Bean.HttpResult;
import cn.smlcx.weather.R;
import cn.smlcx.weather.api.RetrofitWrapper;
import cn.smlcx.weather.di.component.ChoiceComponent;
import cn.smlcx.weather.di.module.ChoiceModule;
import cn.smlcx.weather.mvp.model.ModelContract;
import cn.smlcx.weather.mvp.presenter.ChoiceListPresenter;
import cn.smlcx.weather.mvp.presenter.PresenterContract;
import cn.smlcx.weather.mvp.view.ViewContract;
import cn.smlcx.weather.ui.adapter.MainAdapter;
import rx.Observable;

public class MainActivity extends BaseActivity<ChoiceListPresenter> {
    protected final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.alphaIndicator)
    AlphaIndicator mAlphaIndicator;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        mAlphaIndicator.setViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(3);

       /* ChoiceComponent component = DaggerChoiceComponent.builder().choiceModule(new ChoiceModule()).build();
        component.inject(this);*/
    }

    @Override
    protected void initData() {
        //RetrofitWrapper.getInstance().create()
       // mPresenter.requestChoiceList("d975b5fe029c0691fe5d683cb68b86ac",1,2);
    }

    @Override
    protected void createPresenter() {

    }


}
