package cn.smlcx.weather.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.smlcx.weather.R;

import static cn.smlcx.weather.R.id.toolbar;

/**
 * Created by lcx on 2017/5/5.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    protected final String TAG = this.getClass().getSimpleName();
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
        initViews();
        initData();
        return view;
    }


    private void init() {
        mToolbar = (Toolbar) view.findViewById(toolbar);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_drawer_home);

        Log.e(TAG, "onCreateView");
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
