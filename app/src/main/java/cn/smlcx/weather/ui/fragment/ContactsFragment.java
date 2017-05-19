package cn.smlcx.weather.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.smlcx.weather.Base.BaseFragment;
import cn.smlcx.weather.Bean.NewsBean;
import cn.smlcx.weather.R;
import cn.smlcx.weather.mvp.presenter.NewsListPresenter;
import cn.smlcx.weather.mvp.view.ViewContract;
import cn.smlcx.weather.ui.activity.ChatActivity;
import cn.smlcx.weather.ui.adapter.ContactAdapter;


public class ContactsFragment extends BaseFragment<NewsListPresenter> implements ViewContract.NewsListView, SwipeRefreshLayout.OnRefreshListener {
    protected final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.contacts_list)
    RecyclerView mContactsList;
    Unbinder unbinder;
    private List<String> userList = new ArrayList<>();
    private ContactAdapter mAdapter;
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void initViews() {
        mToolbar.setTitle("通讯录");
        initRecycleView();
        mAdapter = new ContactAdapter(R.layout.contact_item,userList,mContext);
        mContactsList.setAdapter(mAdapter);
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(mContext,ChatActivity.class);
                intent.putExtra("userName",userList.get(i));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        new Thread() {
            @Override
            public void run() {
                try {
                    List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    for (String name:usernames){
                        userList.add(name);
                    }
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        mAdapter.notifyDataSetChanged();
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

    private void initRecycleView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mContactsList.setLayoutManager(linearLayoutManager);
        mContactsList.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getActivity())
                        .color(Color.parseColor("#e0e0e0"))
                        .size(3)
                        .build());
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
}
