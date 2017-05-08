package cn.smlcx.weather.Bean;

import java.util.List;

/**
 * Created by lcx on 2017/5/5.
 */

public class HttpResult<T> {

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

    public static class ResultBean<T> {
        /**
         * list : [{"id":"wechat_20170505056426","title":"都是樱桃红有什么不一样的？","source":"中国南红玛瑙","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-22571256.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170505056426"}]
         * totalPage : 60763
         * ps : 1
         * pno : 1
         */

        private int totalPage;
        private int ps;
        private int pno;
        private List<T> list;

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

        public List<T> getList() {
            return list;
        }

        public void setList(List<T> list) {
            this.list = list;
        }
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "reason='" + reason + '\'' +
                ", result=" + result +
                ", error_code=" + error_code +
                '}';
    }
}
