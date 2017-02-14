package com.list.asus.weather2.gson;

import com.google.gson.annotations.SerializedName;

public class Now {
    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    @SerializedName("fl")
    public String sensibleTemp;

    @SerializedName("hum")
    public String relativeHumidity;

    @SerializedName("pcpn")
    public String precipitation;

    @SerializedName("wind")
    public Wind wind;

    public class More {

        @SerializedName("txt")
        public String info;
    }

    public class Wind {

        @SerializedName("deg")
        public String windDirection1;

        @SerializedName("dir")
        public String windDirection2;
    }
}
