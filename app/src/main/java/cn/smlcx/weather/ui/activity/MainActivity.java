package cn.smlcx.weather.ui.activity;

import android.support.v4.view.ViewPager;

import com.lzy.widget.AlphaIndicator;

import butterknife.BindView;
import cn.smlcx.weather.Base.BaseActivity;
import cn.smlcx.weather.R;
import cn.smlcx.weather.mvp.presenter.ChoiceListPresenter;
import cn.smlcx.weather.ui.adapter.MainAdapter;

public class MainActivity extends BaseActivity<ChoiceListPresenter>{
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
        mViewPager.setCurrentItem(1);

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
