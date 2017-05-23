package cn.smlcx.weather;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class Imhelper {
 public EMConversation getConversation(String userName){
     return EMClient.getInstance().chatManager().getConversation(userName);
 }
}
