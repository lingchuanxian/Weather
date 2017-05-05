package cn.smlcx.weather.ui.activity;

import android.support.v4.view.ViewPager;

import com.lzy.widget.AlphaIndicator;

import butterknife.BindView;
import cn.smlcx.weather.Base.BaseActivity;
import cn.smlcx.weather.R;
import cn.smlcx.weather.ui.adapter.MainAdapter;

public class MainActivity extends BaseActivity {
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
    }

    @Override
    protected void initData() {
    }

}
