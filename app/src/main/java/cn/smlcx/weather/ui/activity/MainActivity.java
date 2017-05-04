package cn.smlcx.weather.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.lzy.widget.AlphaIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.smlcx.weather.Base.BaseActivity;
import cn.smlcx.weather.R;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.viewPager)
    ViewPager mViewPager;
    @InjectView(R.id.alphaIndicator)
    com.lzy.widget.AlphaIndicator mAlphaIndicator;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        AlphaIndicator alphaIndicator = (AlphaIndicator) findViewById(R.id.alphaIndicator);
        mAlphaIndicator.setViewPager(mViewPager);
    }

    @Override
    protected void initData() {
    }

    private class MainAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private String[] titles = {//
                "第一页\n\n重点看下面的的图标是渐变色，随着滑动距离的增加，颜色逐渐过度",//
                "第二页\n\n重点看下面的的图标是渐变色，随着滑动距离的增加，颜色逐渐过度",//
                "第三页\n\n重点看下面的的图标是渐变色，随着滑动距离的增加，颜色逐渐过度", //
                "第四页\n\n重点看下面的的图标是渐变色，随着滑动距离的增加，颜色逐渐过度"};

        public MainAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(TextFragment.newInstance(titles[0]));
            fragments.add(TextFragment.newInstance(titles[1]));
            fragments.add(TextFragment.newInstance(titles[2]));
            fragments.add(TextFragment.newInstance(titles[3]));
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
