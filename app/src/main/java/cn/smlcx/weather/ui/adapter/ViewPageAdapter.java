package cn.smlcx.weather.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.smlcx.weather.ui.fragment.ChoiceFragment;
import cn.smlcx.weather.ui.fragment.NewsFragment;
import cn.smlcx.weather.ui.fragment.PersonalFragment;
import cn.smlcx.weather.ui.fragment.WeatherFragment;

/**
 * Created by lcx on 2017/5/5.
 */

public class ViewPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();
    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
        WeatherFragment wf = new WeatherFragment();
        ChoiceFragment cf = new ChoiceFragment();
        NewsFragment nf = new NewsFragment();
        PersonalFragment pf = new PersonalFragment();
        fragments.add(wf);
        fragments.add(cf);
        fragments.add(nf);
        fragments.add(pf);
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
