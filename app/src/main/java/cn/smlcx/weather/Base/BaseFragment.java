package cn.smlcx.weather.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.smlcx.weather.R;
import cn.smlcx.weather.widget.EmptyLayout;

import static cn.smlcx.weather.R.id.toolbar;

/**
 * Created by lcx on 2017/5/5.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView{
    @Inject
    protected P mPresenter;
    @Nullable
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    public Toolbar mToolbar;
    public Context mContext;
    public View view;
    private Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(attachLayoutRes(), container, false);
        unbinder = ButterKnife.bind(this,view);
        init();
        initInjector();
        initViews();
        initData();
        return view;
    }


    private void init() {
        mToolbar = (Toolbar) view.findViewById(toolbar);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
    }

    protected abstract int attachLayoutRes();
    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化dagger2
     */
    protected abstract void initInjector();

    @Override
    public void showLoding() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
        }
    }

    @Override
    public void hideLoding() {
        if (mEmptyLayout != null) {
            mEmptyLayout.hide();
        }
    }

    @Override
    public void showErr(String err) {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
