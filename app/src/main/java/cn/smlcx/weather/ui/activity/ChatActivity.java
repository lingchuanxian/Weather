package cn.smlcx.weather.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smlcx.weather.Base.BaseActivity;
import cn.smlcx.weather.R;
import cn.smlcx.weather.ui.adapter.ChatWithAdapter;
import cn.smlcx.weather.utils.ToastUtil;

/**
 * Created by lcx on 2017/5/9.
 */

public class ChatActivity extends BaseActivity {
    protected final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.chat_content)
    EditText mChatContent;
    @BindView(R.id.lv_chart)
    RecyclerView mLvChart;
    @BindView(R.id.other_else)
    ImageView mOtherElse;
    @BindView(R.id.send)
    TextView mSend;
    private String userName;

    List<EMMessage> mDatas = new ArrayList<>();
    private ChatWithAdapter mAdapter;
    private EMConversation conversation;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initViews() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        userName = getIntent().getExtras().get("userName").toString();
        mToolbar.setTitle(userName);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initRecycleView();
        mAdapter = new ChatWithAdapter(mContext,mDatas);
        mLvChart.setAdapter(mAdapter);

        mChatContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged: ");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged: ");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "afterTextChanged: ");
                if(!mChatContent.getText().toString().equals("")){
                    mOtherElse.setVisibility(View.GONE);
                    mSend.setVisibility(View.VISIBLE);
                }else{
                    mOtherElse.setVisibility(View.VISIBLE);
                    mSend.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void initData() {
        conversation = EMClient.getInstance().chatManager().getConversation(userName);
        if(conversation!=null){
            List<EMMessage> messages = conversation.getAllMessages();
            for (int i = 0; i < messages.size(); i++) {
                Log.i(TAG, "initData: "+messages.get(i).toString());
                mDatas.add(messages.get(i));
            }
            mAdapter.notifyDataSetChanged();
            //指定会话消息未读数清零
            conversation.markAllMessagesAsRead();
        }
    }

    @OnClick(R.id.send)
    void onClick() {
        if (mChatContent.getText().toString().equals("")) {
            ToastUtil.show(mContext, "请输入文本内容");
            return;
        } else {
            EMMessage message = EMMessage.createTxtSendMessage(mChatContent.getText().toString(), userName);
            EMClient.getInstance().chatManager().sendMessage(message);
            mDatas.add(message);
            mAdapter.notifyDataSetChanged();
            mChatContent.setText("");
        }
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initInjector() {

    }


    /**
     * 初始化RecycleView
     */
    private void initRecycleView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mLvChart.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
