package com.list.asus.weather2.bean;

import com.google.gson.Gson;

/**
 * @author 14512 on 2018/6/8
 */
public class Update {

    /**
     * loc : 2018-06-08 18:55
     * utc : 2018-06-08 10:55
     */

    private String loc;
    private String utc;

    public static Update objectFromData(String str) {

        return new Gson().fromJson(str, Update.class);
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }
}
