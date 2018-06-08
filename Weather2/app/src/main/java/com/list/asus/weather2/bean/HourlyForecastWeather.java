package com.list.asus.weather2.bean;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 14512 on 2018/6/8
 */
public class HourlyForecastWeather {

    /**
     * basic : {"cid":"CN101040100","location":"重庆","parent_city":"重庆","admin_area":"重庆","cnty":"中国","lat":"29.56376076","lon":"106.55046082","tz":"+8.00"}
     * update : {"loc":"2018-06-08 19:57","utc":"2018-06-08 11:57"}
     * status : ok
     * hourly : [{"cloud":"93","cond_code":"104","cond_txt":"阴","dew":"16","hum":"79","pop":"16","pres":"1009","time":"2018-06-08 22:00","tmp":"22","wind_deg":"171","wind_dir":"南风","wind_sc":"1-2","wind_spd":"10"},{"cloud":"67","cond_code":"101","cond_txt":"多云","dew":"16","hum":"81","pop":"0","pres":"1010","time":"2018-06-09 01:00","tmp":"22","wind_deg":"139","wind_dir":"东南风","wind_sc":"1-2","wind_spd":"7"},{"cloud":"62","cond_code":"101","cond_txt":"多云","dew":"16","hum":"84","pop":"0","pres":"1009","time":"2018-06-09 04:00","tmp":"20","wind_deg":"141","wind_dir":"东南风","wind_sc":"1-2","wind_spd":"8"},{"cloud":"38","cond_code":"101","cond_txt":"多云","dew":"16","hum":"82","pop":"0","pres":"1008","time":"2018-06-09 07:00","tmp":"21","wind_deg":"166","wind_dir":"东南风","wind_sc":"1-2","wind_spd":"2"},{"cloud":"24","cond_code":"101","cond_txt":"多云","dew":"17","hum":"71","pop":"0","pres":"1008","time":"2018-06-09 10:00","tmp":"23","wind_deg":"37","wind_dir":"东北风","wind_sc":"1-2","wind_spd":"9"},{"cloud":"59","cond_code":"101","cond_txt":"多云","dew":"17","hum":"62","pop":"0","pres":"1007","time":"2018-06-09 13:00","tmp":"26","wind_deg":"81","wind_dir":"东风","wind_sc":"1-2","wind_spd":"3"},{"cloud":"34","cond_code":"100","cond_txt":"晴","dew":"16","hum":"58","pop":"0","pres":"1004","time":"2018-06-09 16:00","tmp":"28","wind_deg":"79","wind_dir":"东北风","wind_sc":"1-2","wind_spd":"4"},{"cloud":"35","cond_code":"100","cond_txt":"晴","dew":"17","hum":"54","pop":"0","pres":"1001","time":"2018-06-09 19:00","tmp":"27","wind_deg":"16","wind_dir":"东北风","wind_sc":"1-2","wind_spd":"11"}]
     */

    private Basic basic;
    private Update update;
    private String status;
    private List<HourlyForecast> hourly = new ArrayList<>();

    public static HourlyForecastWeather objectFromData(String str) {

        return new Gson().fromJson(str, HourlyForecastWeather.class);
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

    public List<HourlyForecast> getHourly() {
        return hourly;
    }

    public void setHourly(List<HourlyForecast> hourly) {
        this.hourly = hourly;
    }

}
