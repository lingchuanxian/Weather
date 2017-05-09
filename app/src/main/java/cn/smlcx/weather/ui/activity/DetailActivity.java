package cn.smlcx.weather.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.smlcx.weather.Base.BaseActivity;
import cn.smlcx.weather.R;

/**
 * Created by lcx on 2017/5/9.
 */

public class DetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.webView)
    WebView mWebView;

    @Override
    protected int attachLayoutRes() {
        return R.layout.common_detail;
    }

    @Override
    protected void initViews() {
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
