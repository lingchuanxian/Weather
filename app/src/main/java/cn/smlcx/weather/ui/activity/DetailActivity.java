package cn.smlcx.weather.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import cn.smlcx.weather.Base.BaseActivity;
import cn.smlcx.weather.R;
import cn.smlcx.weather.widget.LoadingWebView;

/**
 * Created by lcx on 2017/5/9.
 */

public class DetailActivity extends BaseActivity {
    protected final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.webView)
    LoadingWebView mWebView;
    private String url;
    private String title;
    @Override
    protected int attachLayoutRes() {
        return R.layout.common_detail;
    }

    @Override
    protected void initViews() {
        url = (String) getIntent().getExtras().get("url");
        title = (String) getIntent().getExtras().get("title");
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        mWebView.loadMessageUrl(url);
        mWebView.addProgressBar();
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initInjector() {

    }
}
