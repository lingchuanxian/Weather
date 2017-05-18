package cn.smlcx.weather.Bean;

/**
 * Created by lcx on 2017/5/18.
 */

public class ChatPrevire {
    private String username;
    private long date;
    private String lastMsg;
    private int unReadCount;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public int getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(int unReadCount) {
        this.unReadCount = unReadCount;
    }

    public ChatPrevire() {
    }

    public ChatPrevire(String username, long date, String lastMsg, int unReadCount) {
        this.username = username;
        this.date = date;
        this.lastMsg = lastMsg;
        this.unReadCount = unReadCount;
    }
}
