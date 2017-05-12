package cn.smlcx.weather.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */

public class WeatherBean implements Serializable{

    /**
     * success : 1
     * result : [{"weaid":"141","days":"2017-05-11","week":"星期四","cityno":"fjfuzhou","citynm":"福州","cityid":"101230101","temperature":"32℃/23℃","humidity":"0%/0%","weather":"阴","weather_icon":"http://api.k780.com:88/upload/weather/d/2.gif","weather_icon1":"http://api.k780.com:88/upload/weather/n/2.gif","wind":"西南风","winp":"微风","temp_high":"32","temp_low":"23","humi_high":"0","humi_low":"0","weatid":"3","weatid1":"3","windid":"16","winpid":"125"},{"weaid":"141","days":"2017-05-12","week":"星期五","cityno":"fjfuzhou","citynm":"福州","cityid":"101230101","temperature":"32℃/21℃","humidity":"0%/0%","weather":"雷阵雨转中雨","weather_icon":"http://api.k780.com:88/upload/weather/d/4.gif","weather_icon1":"http://api.k780.com:88/upload/weather/n/8.gif","wind":"西南风","winp":"3-4级转微风","temp_high":"32","temp_low":"21","humi_high":"0","humi_low":"0","weatid":"5","weatid1":"9","windid":"16","winpid":"129"},{"weaid":"141","days":"2017-05-13","week":"星期六","cityno":"fjfuzhou","citynm":"福州","cityid":"101230101","temperature":"22℃/19℃","humidity":"0%/0%","weather":"中雨转多云","weather_icon":"http://api.k780.com:88/upload/weather/d/8.gif","weather_icon1":"http://api.k780.com:88/upload/weather/n/1.gif","wind":"东风转北风","winp":"3-4级转微风","temp_high":"22","temp_low":"19","humi_high":"0","humi_low":"0","weatid":"9","weatid1":"2","windid":"80","winpid":"129"},{"weaid":"141","days":"2017-05-14","week":"星期日","cityno":"fjfuzhou","citynm":"福州","cityid":"101230101","temperature":"30℃/19℃","humidity":"0%/0%","weather":"多云转阴","weather_icon":"http://api.k780.com:88/upload/weather/d/1.gif","weather_icon1":"http://api.k780.com:88/upload/weather/n/2.gif","wind":"东风转无持续风向","winp":"微风","temp_high":"30","temp_low":"19","humi_high":"0","humi_low":"0","weatid":"2","weatid1":"3","windid":"133","winpid":"125"},{"weaid":"141","days":"2017-05-15","week":"星期一","cityno":"fjfuzhou","citynm":"福州","cityid":"101230101","temperature":"23℃/18℃","humidity":"0%/0%","weather":"阴转大雨","weather_icon":"http://api.k780.com:88/upload/weather/d/2.gif","weather_icon1":"http://api.k780.com:88/upload/weather/n/9.gif","wind":"北风转无持续风向","winp":"微风","temp_high":"23","temp_low":"18","humi_high":"0","humi_low":"0","weatid":"3","weatid1":"10","windid":"126","winpid":"125"},{"weaid":"141","days":"2017-05-16","week":"星期二","cityno":"fjfuzhou","citynm":"福州","cityid":"101230101","temperature":"26℃/19℃","humidity":"0%/0%","weather":"雷阵雨转阴","weather_icon":"http://api.k780.com:88/upload/weather/d/4.gif","weather_icon1":"http://api.k780.com:88/upload/weather/n/2.gif","wind":"东北风转无持续风向","winp":"3-4级转微风","temp_high":"26","temp_low":"19","humi_high":"0","humi_low":"0","weatid":"5","weatid1":"3","windid":"136","winpid":"129"},{"weaid":"141","days":"2017-05-17","week":"星期三","cityno":"fjfuzhou","citynm":"福州","cityid":"101230101","temperature":"28℃/20℃","humidity":"0%/0%","weather":"阴","weather_icon":"http://api.k780.com:88/upload/weather/d/2.gif","weather_icon1":"http://api.k780.com:88/upload/weather/n/2.gif","wind":"东风转无持续风向","winp":"3-4级转微风","temp_high":"28","temp_low":"20","humi_high":"0","humi_low":"0","weatid":"3","weatid1":"3","windid":"133","winpid":"129"}]
     */

    private String success;
    private List<ResultBean> result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * weaid : 141
         * days : 2017-05-11
         * week : 星期四
         * cityno : fjfuzhou
         * citynm : 福州
         * cityid : 101230101
         * temperature : 32℃/23℃
         * humidity : 0%/0%
         * weather : 阴
         * weather_icon : http://api.k780.com:88/upload/weather/d/2.gif
         * weather_icon1 : http://api.k780.com:88/upload/weather/n/2.gif
         * wind : 西南风
         * winp : 微风
         * temp_high : 32
         * temp_low : 23
         * humi_high : 0
         * humi_low : 0
         * weatid : 3
         * weatid1 : 3
         * windid : 16
         * winpid : 125
         */

        private String weaid;
        private String days;
        private String week;
        private String cityno;
        private String citynm;
        private String cityid;
        private String temperature;
        private String humidity;
        private String weather;
        private String weather_icon;
        private String weather_icon1;
        private String wind;
        private String winp;
        private String temp_high;
        private String temp_low;
        private String humi_high;
        private String humi_low;
        private String weatid;
        private String weatid1;
        private String windid;
        private String winpid;

        public String getWeaid() {
            return weaid;
        }

        public void setWeaid(String weaid) {
            this.weaid = weaid;
        }

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getCityno() {
            return cityno;
        }

        public void setCityno(String cityno) {
            this.cityno = cityno;
        }

        public String getCitynm() {
            return citynm;
        }

        public void setCitynm(String citynm) {
            this.citynm = citynm;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWeather_icon() {
            return weather_icon;
        }

        public void setWeather_icon(String weather_icon) {
            this.weather_icon = weather_icon;
        }

        public String getWeather_icon1() {
            return weather_icon1;
        }

        public void setWeather_icon1(String weather_icon1) {
            this.weather_icon1 = weather_icon1;
        }

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }

        public String getWinp() {
            return winp;
        }

        public void setWinp(String winp) {
            this.winp = winp;
        }

        public String getTemp_high() {
            return temp_high;
        }

        public void setTemp_high(String temp_high) {
            this.temp_high = temp_high;
        }

        public String getTemp_low() {
            return temp_low;
        }

        public void setTemp_low(String temp_low) {
            this.temp_low = temp_low;
        }

        public String getHumi_high() {
            return humi_high;
        }

        public void setHumi_high(String humi_high) {
            this.humi_high = humi_high;
        }

        public String getHumi_low() {
            return humi_low;
        }

        public void setHumi_low(String humi_low) {
            this.humi_low = humi_low;
        }

        public String getWeatid() {
            return weatid;
        }

        public void setWeatid(String weatid) {
            this.weatid = weatid;
        }

        public String getWeatid1() {
            return weatid1;
        }

        public void setWeatid1(String weatid1) {
            this.weatid1 = weatid1;
        }

        public String getWindid() {
            return windid;
        }

        public void setWindid(String windid) {
            this.windid = windid;
        }

        public String getWinpid() {
            return winpid;
        }

        public void setWinpid(String winpid) {
            this.winpid = winpid;
        }
    }

    @Override
    public String toString() {
        return "WeatherBean{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}
