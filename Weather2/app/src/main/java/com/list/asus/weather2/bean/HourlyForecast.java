package com.list.asus.weather2.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 2017/2/13.
 */

public class HourlyForecast {


    /**
     * cloud : 93
     * cond_code : 104
     * cond_txt : 阴
     * dew : 16
     * hum : 79
     * pop : 16
     * pres : 1009
     * time : 2018-06-08 22:00
     * tmp : 22
     * wind_deg : 171
     * wind_dir : 南风
     * wind_sc : 1-2
     * wind_spd : 10
     */

    private String cloud;
    @SerializedName("cond_code")
    private String condCode;
    @SerializedName("cond_txt")
    private String condTxt;
    private String dew;
    private String hum;
    private String pop;
    private String pres;
    private String time;
    private String tmp;
    @SerializedName("wind_deg")
    private String windDeg;
    @SerializedName("wind_dir")
    private String windDir;
    @SerializedName("wind_sc")
    private String windSc;
    @SerializedName("wind_spd")
    private String windSpd;

    public static HourlyForecast objectFromData(String str) {

        return new Gson().fromJson(str, HourlyForecast.class);
    }

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public String getCondCode() {
        return condCode;
    }

    public void setCondCode(String condCode) {
        this.condCode = condCode;
    }

    public String getCondTxt() {
        return condTxt;
    }

    public void setCondTxt(String condTxt) {
        this.condTxt = condTxt;
    }

    public String getDew() {
        return dew;
    }

    public void setDew(String dew) {
        this.dew = dew;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(String windDeg) {
        this.windDeg = windDeg;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public String getWindSc() {
        return windSc;
    }

    public void setWindSc(String windSc) {
        this.windSc = windSc;
    }

    public String getWindSpd() {
        return windSpd;
    }

    public void setWindSpd(String windSpd) {
        this.windSpd = windSpd;
    }
}
