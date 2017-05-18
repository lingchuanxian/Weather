package cn.smlcx.weather.Bean;

import com.hyphenate.chat.EMConversation;

/**
 * Created by lcx on 2017/5/18.
 */

public class ChatPreview {
    private String username;
    private EMConversation emConversation;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public EMConversation getEmConversation() {
        return emConversation;
    }

    public void setEmConversation(EMConversation emConversation) {
        this.emConversation = emConversation;
    }
}
