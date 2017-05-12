package cn.smlcx.weather.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.smlcx.weather.Base.BaseFragment;
import cn.smlcx.weather.R;


public class NewsFragment extends BaseFragment implements OnTabSelectListener {
    protected final String TAG = this.getClass().getSimpleName();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "头条", "社会", "国内"
            , "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚"
    };
    private final String[] mType = {
            "toutiao", "shehui", "guonei"
            , "guoji", "yule", "tiyu", "junshi", "keji", "caijing", "shishang"
    };

    private NewsPagerAdapter mAdapter;
    @BindView(R.id.tl_4)
    SlidingTabLayout mTl4;
    Unbinder unbinder;
    @BindView(R.id.viewPage)
    ViewPager mViewPage;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViews() {
        mToolbar.setTitle("新闻头条");

        for (String type : mType) {
            Log.i(TAG, "initViews: "+type);
            mFragments.add(CommonNewsFragment.getInstance(type));
        }
        mAdapter = new NewsPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPage.setAdapter(mAdapter);
        mViewPage.setOffscreenPageLimit(10);

        mTl4.setViewPager(mViewPage);

    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initInjector() {

    }


    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

    }

    private class NewsPagerAdapter extends FragmentPagerAdapter {
        public NewsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
