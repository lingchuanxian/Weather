package cn.smlcx.weather.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smlcx.weather.Base.BaseActivity;
import cn.smlcx.weather.R;
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
    private String userName;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initViews() {
        userName = getIntent().getExtras().get("userName").toString();
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(userName);
        conversation.markAllMessagesAsRead();
        mToolbar.setTitle(userName);
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

    }

    @OnClick(R.id.send)
    void onClick() {
        if (mChatContent.getText().toString().equals("")) {
            ToastUtil.show(mContext,"请输入文本内容");
            return;
        }else{
            EMMessage message = EMMessage.createTxtSendMessage(mChatContent.getText().toString(), userName);
            EMClient.getInstance().chatManager().sendMessage(message);
        }
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
