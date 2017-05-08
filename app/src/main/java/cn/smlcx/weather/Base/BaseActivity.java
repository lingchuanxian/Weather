package cn.smlcx.weather.Base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.smlcx.weather.app.App;

/**
 * Created by lcx on 2017/5/4.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity{
    @Inject
    protected P mPresenter;

    public Context mContext;
    public App app;
    private Unbinder mUnbinder;
    //public  Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutRes());
        mUnbinder = ButterKnife.bind(this);
        init();
        initViews();
        initData();
    }

    private void init() {
        mContext = this;
        app = (App) getApplication();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
           /* if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
               //将侧边栏顶部延伸至status bar
                mDrawerLayout.setFitsSystemWindows(true);
                //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
                mDrawerLayout.setClipToPadding(false);
            }*/
        }
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @LayoutRes
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
     * 创建presenter
     */
    protected abstract void createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
