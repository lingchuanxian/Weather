package cn.smlcx.weather.ui.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.smlcx.weather.Bean.ChatPreview;
import cn.smlcx.weather.R;

/**
 * Created by lcx on 2017/5/10.
 */

public class ChatAdapter extends BaseQuickAdapter<ChatPreview> {
    private Context mContext;
    public ChatAdapter(int layoutResId, List<ChatPreview> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }
    public ChatAdapter(List<ChatPreview> data) {
        super(data);
    }
    public ChatAdapter(View contentView, List<ChatPreview> data) {
        super(contentView, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChatPreview item) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        Date date = new Date(item.getEmConversation().getLastMessage().getMsgTime());
        helper.setText(R.id.chat_userName,item.getUsername())
                .setText(R.id.chat_date,sdf.format(date));
        if(item.getEmConversation().getLastMessage().getType().name().equals("TXT")){
            helper.setText(R.id.chat_text,item.getEmConversation().getLastMessage().getBody().toString().split("\"")[1].split("\"")[0]);
        }else if(item.getEmConversation().getLastMessage().getType().name().equals("IMAGE")){
            helper.setText(R.id.chat_text,"[图片]");
        }else{
            helper.setText(R.id.chat_text,item.getEmConversation().getLastMessage().getType().name()+item.getEmConversation().getLastMessage().getBody().toString());
        }

        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(item.getUsername());
        int num = conversation.getUnreadMsgCount();
        if(num <= 0){
            helper.setVisible(R.id.chat_num_unread,false);
        }else{
            helper.setVisible(R.id.chat_num_unread,true);
            helper.setText(R.id.chat_num_unread,num+"");
        }
    }
}
