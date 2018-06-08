package com.list.asus.weather2.bean;

import com.google.gson.Gson;

/**
 * @author 14512
 */
public class NowWeather {


    /**
     * basic : {"cid":"CN101040100","location":"重庆","parent_city":"重庆","admin_area":"重庆","cnty":"中国","lat":"29.56376076","lon":"106.55046082","tz":"+8.00"}
     * update : {"loc":"2018-06-08 18:55","utc":"2018-06-08 10:55"}
     * status : ok
     * now : {"cloud":"18","cond_code":"104","cond_txt":"阴","fl":"25","hum":"74","pcpn":"0.0","pres":"1005","tmp":"23","vis":"14","wind_deg":"170","wind_dir":"南风","wind_sc":"1","wind_spd":"2"}
     */

    private Basic basic;
    private Update update;
    private String status;
    private Now now;

    public static NowWeather objectFromData(String str) {

        return new Gson().fromJson(str, NowWeather.class);
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

    public Now getNow() {
        return now;
    }

    public void setNow(Now now) {
        this.now = now;
    }

}
