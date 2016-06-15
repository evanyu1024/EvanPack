package com.evan.demo.data.bean;

/**
 * Created by evanyu on 16/6/14.
 */
public class Weather {


    /**
     * success : 1
     * result : {"weaid":"1","days":"2016-06-14","week":"星期二","cityno":"beijing","citynm":"北京","cityid":"101010100","temperature":"26℃/18℃","temperature_curr":"19℃","humidity":"77%","weather":"雷阵雨","weather_icon":"http://api.k780.com:88/upload/weather/d/4.gif","weather_icon1":"","wind":"东风","winp":"2级","temp_high":"26","temp_low":"18","temp_curr":"19","humi_high":"0","humi_low":"0","weatid":"5","weatid1":"","windid":"10","winpid":"203"}
     */

    private String success;
    /**
     * weaid : 1
     * days : 2016-06-14
     * week : 星期二
     * cityno : beijing
     * citynm : 北京
     * cityid : 101010100
     * temperature : 26℃/18℃
     * temperature_curr : 19℃
     * humidity : 77%
     * weather : 雷阵雨
     * weather_icon : http://api.k780.com:88/upload/weather/d/4.gif
     * weather_icon1 :
     * wind : 东风
     * winp : 2级
     * temp_high : 26
     * temp_low : 18
     * temp_curr : 19
     * humi_high : 0
     * humi_low : 0
     * weatid : 5
     * weatid1 :
     * windid : 10
     * winpid : 203
     */

    private ResultBean result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private String days;
        private String week;
        private String citynm;
        private String temperature;
        private String temperature_curr;
        private String humidity;
        private String weather;

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

        public String getCitynm() {
            return citynm;
        }

        public void setCitynm(String citynm) {
            this.citynm = citynm;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getTemperature_curr() {
            return temperature_curr;
        }

        public void setTemperature_curr(String temperature_curr) {
            this.temperature_curr = temperature_curr;
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
    }
}
