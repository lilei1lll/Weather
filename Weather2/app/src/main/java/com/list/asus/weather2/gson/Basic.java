package com.list.asus.weather2.gson;

import com.google.gson.annotations.SerializedName;

public class Basic {
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update{

        @SerializedName("loc")
        public String updateLocTime;  //当地时间

        @SerializedName("utc")
        public String updateUtcTime;  //世界时间
    }
}
