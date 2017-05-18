package cn.smlcx.weather.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

import cn.smlcx.weather.Base.BaseFragment;
import cn.smlcx.weather.Bean.NewsBean;
import cn.smlcx.weather.R;
import cn.smlcx.weather.mvp.presenter.NewsListPresenter;
import cn.smlcx.weather.mvp.view.ViewContract;


public class ContactsFragment extends BaseFragment<NewsListPresenter> implements ViewContract.NewsListView, SwipeRefreshLayout.OnRefreshListener {
    protected final String TAG = this.getClass().getSimpleName();
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initViews() {
        mToolbar.setTitle("通讯录");
    }

    @Override
    protected void initData() {
        List<String> usernames = null;
        try {
            usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
            for (int i = 0; i < usernames.size(); i++) {
                Log.e(TAG, "initData: "+usernames );
            }
        } catch (HyphenateException e) {
            e.printStackTrace();
            Log.e(TAG, "initData: "+e.getMessage() );
        }
    }
    @Override
    protected void initInjector() {
    }

    @Override
    public void onRefresh() {
    }

    @Override
    public void showNewsList(List<NewsBean.ResultBean.DataBean> mList) {
    }
}
