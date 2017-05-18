package cn.smlcx.weather.ui.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.smlcx.weather.Bean.ChatPrevire;
import cn.smlcx.weather.R;

/**
 * Created by lcx on 2017/5/10.
 */

public class ChatAdapter extends BaseQuickAdapter<ChatPrevire> {

    public ChatAdapter(int layoutResId, List<ChatPrevire> data) {
        super(layoutResId, data);
    }
    public ChatAdapter(List<ChatPrevire> data) {
        super(data);
    }
    public ChatAdapter(View contentView, List<ChatPrevire> data) {
        super(contentView, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChatPrevire item) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        Date date = new Date(item.getDate());
        helper.setText(R.id.chat_userName,item.getUsername())
                .setText(R.id.chat_date,sdf.format(date))
                .setText(R.id.chat_text,item.getLastMsg());
    }
}
