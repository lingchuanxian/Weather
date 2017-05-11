package cn.smlcx.weather.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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


public class PersonalFragment extends BaseFragment implements OnTabSelectListener {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "头条", "社会", "国内"
            , "国际", "娱乐", "体育", "军事","科技","财经","时尚"
    };

    private MyPagerAdapter mAdapter;
    @BindView(R.id.tl_4)
    SlidingTabLayout mTl4;
    Unbinder unbinder;
    @BindView(R.id.viewPage)
    ViewPager mViewPage;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initViews() {
        mToolbar.setTitle("个人中心");

        for (String title : mTitles) {
            mFragments.add(SimpleCardFragment.getInstance(title));
        }
        mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPage.setAdapter(mAdapter);


        mTl4.setViewPager(mViewPage);

    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initInjector() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
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
