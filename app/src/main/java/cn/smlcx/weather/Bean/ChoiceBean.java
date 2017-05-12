package cn.smlcx.weather.Bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/5/5.
 */

public class ChoiceBean implements Serializable{

    /**
     * reason : 请求成功
     * result : {"list":[{"id":"wechat_20170509015051","title":"十本充满东方意蕴的幼儿绘本","source":"亲子教育","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-22979008.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170509015051"}],"totalPage":63334,"ps":1,"pno":1}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * list : [{"id":"wechat_20170509015051","title":"十本充满东方意蕴的幼儿绘本","source":"亲子教育","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-22979008.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170509015051"}]
         * totalPage : 63334
         * ps : 1
         * pno : 1
         */

        private int totalPage;
        private int ps;
        private int pno;
        private List<ListBean> list;

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getPs() {
            return ps;
        }

        public void setPs(int ps) {
            this.ps = ps;
        }

        public int getPno() {
            return pno;
        }

        public void setPno(int pno) {
            this.pno = pno;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : wechat_20170509015051
             * title : 十本充满东方意蕴的幼儿绘本
             * source : 亲子教育
             * firstImg : http://zxpic.gtimg.com/infonew/0/wechat_pics_-22979008.jpg/640
             * mark :
             * url : http://v.juhe.cn/weixin/redirect?wid=wechat_20170509015051
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

            @Inject
            public ListBean() {
            }
        }
    }
}
