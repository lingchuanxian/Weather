package cn.smlcx.weather.Bean;

/**
 * Created by Administrator on 2017/5/5.
 */

public class ChoiceBean {
    /**
     * id : wechat_20170505056426
     * title : 都是樱桃红有什么不一样的？
     * source : 中国南红玛瑙
     * firstImg : http://zxpic.gtimg.com/infonew/0/wechat_pics_-22571256.jpg/640
     * mark :
     * url : http://v.juhe.cn/weixin/redirect?wid=wechat_20170505056426
     */

    private String id;
    private String title;
    private String source;
    private String firstImg;
    private String mark;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFirstImg() {
        return firstImg;
    }

    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ChoiceBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", firstImg='" + firstImg + '\'' +
                ", mark='" + mark + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
