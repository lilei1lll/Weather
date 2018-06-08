package com.list.asus.weather2.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 14512 on 2018/6/8
 */
public class DailyForecastWeather {


    /**
     * basic : {"cid":"CN101040100","location":"重庆","parent_city":"重庆","admin_area":"重庆","cnty":"中国","lat":"29.56376076","lon":"106.55046082","tz":"+8.00"}
     * update : {"loc":"2018-06-08 18:55","utc":"2018-06-08 10:55"}
     * status : ok
     * daily_forecast : [{"cond_code_d":"300","cond_code_n":"101","cond_txt_d":"阵雨","cond_txt_n":"多云","date":"2018-06-08","hum":"54","mr":"02:00","ms":"14:06","pcpn":"0.0","pop":"1","pres":"1005","sr":"05:54","ss":"19:53","tmp_max":"29","tmp_min":"22","uv_index":"5","vis":"10","wind_deg":"-1","wind_dir":"无持续风向","wind_sc":"1-2","wind_spd":"5"},{"cond_code_d":"101","cond_code_n":"300","cond_txt_d":"多云","cond_txt_n":"阵雨","date":"2018-06-09","hum":"66","mr":"02:35","ms":"15:04","pcpn":"0.0","pop":"11","pres":"1006","sr":"05:54","ss":"19:53","tmp_max":"31","tmp_min":"22","uv_index":"5","vis":"20","wind_deg":"-1","wind_dir":"无持续风向","wind_sc":"1-2","wind_spd":"2"},{"cond_code_d":"101","cond_code_n":"101","cond_txt_d":"多云","cond_txt_n":"多云","date":"2018-06-10","hum":"57","mr":"03:11","ms":"16:03","pcpn":"0.0","pop":"6","pres":"1005","sr":"05:54","ss":"19:54","tmp_max":"28","tmp_min":"22","uv_index":"5","vis":"18","wind_deg":"-1","wind_dir":"无持续风向","wind_sc":"1-2","wind_spd":"9"}]
     */

    private Basic basic;
    private Update update;
    private String status;
    @SerializedName("daily_forecast")
    private List<DailyForecast> dailyForecast = new ArrayList<>();

    public static DailyForecastWeather objectFromData(String str) {

        return new Gson().fromJson(str, DailyForecastWeather.class);
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

    public List<DailyForecast> getDailyForecast() {
        return dailyForecast;
    }

    public void setDailyForecast(List<DailyForecast> dailyForecast) {
        this.dailyForecast = dailyForecast;
    }


}
