package com.list.asus.weather2.bean;

import com.google.gson.Gson;

public class Suggestion {

    /**
     * type : comf
     * brf : 较舒适
     * txt : 今天夜间天气晴好，您在这种天气条件下，会感觉凉爽、舒适，偏热。
     */

    private String type;
    private String brf;
    private String txt;

    public static Suggestion objectFromData(String str) {

        return new Gson().fromJson(str, Suggestion.class);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrf() {
        return brf;
    }

    public void setBrf(String brf) {
        this.brf = brf;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
