package com.list.asus.weather2.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by HP on 2017/2/13.
 */

public class HourlyForecast {

    public String date;

    @SerializedName("tmp")
    public String tmp;

    @SerializedName("cond")
    public More more;


    public class More{

        @SerializedName("txt")
        public String info;
    }
}
