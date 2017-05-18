package cn.smlcx.weather.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.smlcx.weather.Base.BaseFragment;
import cn.smlcx.weather.Bean.AnyEventType;
import cn.smlcx.weather.Bean.ChatPreview;
import cn.smlcx.weather.Bean.NewsBean;
import cn.smlcx.weather.R;
import cn.smlcx.weather.mvp.presenter.NewsListPresenter;
import cn.smlcx.weather.mvp.view.ViewContract;
import cn.smlcx.weather.ui.activity.ChatActivity;
import cn.smlcx.weather.ui.adapter.ChatAdapter;
import cn.smlcx.weather.utils.ToActivityUtil;
import cn.smlcx.weather.utils.ToastUtil;


public class MsgFragment extends BaseFragment<NewsListPresenter> implements ViewContract.NewsListView, SwipeRefreshLayout.OnRefreshListener {
    protected final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.msg_list)
    RecyclerView mMsgList;
    Unbinder unbinder;
    private int unReadsum = 0;
    private ChatAdapter mAdapter;
    private List<ChatPreview> mData = new ArrayList<ChatPreview>();

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_msg;
    }

    @Override
    protected void initViews() {
        mToolbar.setTitle("消息");
        initRecycleView();
        mAdapter = new ChatAdapter(R.layout.msg_item, mData,mContext);
        mMsgList.setAdapter(mAdapter);

        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(mContext,ChatActivity.class);
                intent.putExtra("userName",mData.get(i).getUsername());
                startActivity(intent);
            }
        });
    }


    @Override
    protected void initData() {
        EMMessageListener msgListener = new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                Log.i(TAG, "onMessageReceived: " + messages.get(0).getBody());
                Log.i(TAG, "onMessageReceived: " + messages.get(0).getFrom());
                //收到消息
                unReadsum++;
                EventBus.getDefault().post(new AnyEventType("" + unReadsum));
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
            }

            @Override
            public void onMessageRead(List<EMMessage> messages) {
                //收到已读回执
            }

            @Override
            public void onMessageDelivered(List<EMMessage> message) {
                //收到已送达回执
            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
            }
        };
        EMClient.getInstance().chatManager().addMessageListener(msgListener);

        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();


        for (Map.Entry<String, EMConversation> map : conversations.entrySet()) {
            unReadsum += map.getValue().getUnreadMsgCount();
            ChatPreview cp = new ChatPreview();
            cp.setUsername(map.getKey());
           cp.setEmConversation(map.getValue());
            mData.add(cp);
        }
        mAdapter.notifyDataSetChanged();
        EventBus.getDefault().post(new AnyEventType("" + unReadsum));

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

    /**
     * 初始化RecycleView
     */
    private void initRecycleView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mMsgList.setLayoutManager(linearLayoutManager);
        mMsgList.addItemDecoration(
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
