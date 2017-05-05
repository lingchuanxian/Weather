package cn.smlcx.weather.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.smlcx.weather.ui.activity.TextFragment;

/**
 * Created by lcx on 2017/5/5.
 */

public class MainAdapter extends FragmentPagerAdapter {
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
