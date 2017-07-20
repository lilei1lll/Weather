package com.list.asus.weather2.gson;

import com.google.gson.annotations.SerializedName;

public class Suggestion {

    //@SerializedName("air")
    public Air air;

    @SerializedName("comf")
    public Comfort comfort;

    @SerializedName("cw")
    public CarWash carWash;

    @SerializedName("drsg")
    public DressSuggestion dressSuggestion;

    @SerializedName("flu")
    public Flu flu;

    @SerializedName("sport")
    public Sport sport;

    @SerializedName("trav")
    public Travel travel;

    @SerializedName("uv")
    public Ultraviolet ultraviolet;

    public class Comfort{
        @SerializedName("brf")
        public String info1;

        @SerializedName("txt")
        public String info;
    }

    public class CarWash{
        @SerializedName("brf")
        public String info1;

        @SerializedName("txt")
        public String info;
    }

    public class Sport{
        @SerializedName("brf")
        public String info1;

        @SerializedName("txt")
        public String info;
    }

    public class DressSuggestion {
        @SerializedName("brf")
        public String info1;

        @SerializedName("txt")
        public String info;
    }

    public class Flu {
        @SerializedName("brf")
        public String info1;

        @SerializedName("txt")
        public String info;
    }

    public class Air {
        @SerializedName("brf")
        public String info1;

        @SerializedName("txt")
        public String info;
    }

    public class Travel {
        @SerializedName("brf")
        public String info1;

        @SerializedName("txt")
        public String info;
    }

    public class Ultraviolet {
        @SerializedName("brf")
        public String info1;

        @SerializedName("txt")
        public String info;
    }
}
