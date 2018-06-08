package com.list.asus.weather2.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * @author 14512 on 2018/6/8
 */
public class AQIWeather {

    /**
     * basic : {"cid":"CN101040100","location":"重庆","parent_city":"重庆","admin_area":"重庆","cnty":"中国","lat":"29.56376076","lon":"106.55046082","tz":"+8.00"}
     * update : {"loc":"2018-06-08 19:57","utc":"2018-06-08 11:57"}
     * status : ok
     * air_now_city : {"aqi":"46","qlty":"优","main":"-","pm25":"25","pm10":"45","no2":"28","so2":"7","co":"0.62","o3":"70","pub_time":"2018-06-08 19:00"}
     */

    private Basic basic;
    private Update update;
    private String status;
    @SerializedName("air_now_city")
    private AQIWeather.AirNowCity airNowCity;

    public static AQIWeather objectFromData(String str) {

        return new Gson().fromJson(str, AQIWeather.class);
    }

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AirNowCity getAirNowCity() {
        return airNowCity;
    }

    public void setAirNowCity(AirNowCity airNowCity) {
        this.airNowCity = airNowCity;
    }

    public static class AirNowCity {
        /**
         * aqi : 46
         * qlty : 优
         * main : -
         * pm25 : 25
         * pm10 : 45
         * no2 : 28
         * so2 : 7
         * co : 0.62
         * o3 : 70
         * pub_time : 2018-06-08 19:00
         */

        private String aqi;
        private String qlty;
        private String main;
        private String pm25;
        private String pm10;
        private String no2;
        private String so2;
        private String co;
        private String o3;
        @SerializedName("pub_time")
        private String pubTime;

        public static AirNowCity objectFromData(String str) {

            return new Gson().fromJson(str, AirNowCity.class);
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getQlty() {
            return qlty;
        }

        public void setQlty(String qlty) {
            this.qlty = qlty;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getNo2() {
            return no2;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getSo2() {
            return so2;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }

        public String getCo() {
            return co;
        }

        public void setCo(String co) {
            this.co = co;
        }

        public String getO3() {
            return o3;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }

        public String getPubTime() {
            return pubTime;
        }

        public void setPubTime(String pubTime) {
            this.pubTime = pubTime;
        }
    }
}
