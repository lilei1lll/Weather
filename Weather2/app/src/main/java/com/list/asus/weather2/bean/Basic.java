package com.list.asus.weather2.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Basic {

    /**
     * cid : CN101040100
     * location : 重庆
     * parent_city : 重庆
     * admin_area : 重庆
     * cnty : 中国
     * lat : 29.56376076
     * lon : 106.55046082
     * tz : +8.00
     */

    private String cid;
    private String location;
    @SerializedName("parent_city")
    private String parentCity;
    @SerializedName("admin_area")
    private String adminArea;
    private String cnty;
    private String lat;
    private String lon;
    private String tz;

    public static Basic objectFromData(String str) {

        return new Gson().fromJson(str, Basic.class);
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getParentCity() {
        return parentCity;
    }

    public void setParentCity(String parentCity) {
        this.parentCity = parentCity;
    }

    public String getAdminArea() {
        return adminArea;
    }

    public void setAdminArea(String adminArea) {
        this.adminArea = adminArea;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }
}
