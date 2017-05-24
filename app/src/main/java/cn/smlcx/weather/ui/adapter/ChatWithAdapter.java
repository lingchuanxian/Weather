package cn.smlcx.weather.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;

import java.util.List;

import cn.smlcx.weather.R;

/**
 * Created by lcx on 2017/5/10.
 */

public class ChatWithAdapter extends RecyclerView.Adapter<ChatWithAdapter.ChatViewHolder> {

    private List<EMMessage> chatBeanList;
    private LayoutInflater inflater;

    public ChatWithAdapter(Context context,List<EMMessage> chatBeans){
        this.chatBeanList=chatBeans;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ChatViewHolder holder;
        //根据ViewType值创建不同的ViewHolder
        if(viewType==0) {
            holder = new ChatViewHolder(inflater.inflate(R.layout.item_chat_other, parent, false));
        }else{
            holder = new ChatViewHolder(inflater.inflate(R.layout.item_chat_self, parent, false));
        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        if(chatBeanList.get(position).direct()==EMMessage.Direct.RECEIVE){
            return 0;
        }else{//if(chatBeanList.get(position).direct() == EMMessage.Direct.RECEIVE)
            return 1;
        }

    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        holder.tvContent.setText(chatBeanList.get(position).getBody().toString().split("\"")[1].split("\"")[0]);
    }

    @Override
    public int getItemCount() {
        return chatBeanList.size();
    }
    //简单实现的ViewHolder，开发中最好再做些优化可以参考上一篇
    class ChatViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;
        public ChatViewHolder(View view) {
            super(view);
            tvContent = (TextView) view.findViewById(R.id.c_content);
        }
    }
}