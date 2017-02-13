package com.list.asus.weather2.gson;

public class AQI {

    public AQICity city;

    public class AQICity{

        public String aqi;

        public String no2;  //二氧化氮

        public String o3;  //臭氧

        public String pm10;

        public String pm25;

        public String qlty;  //空气质量

        public String so2;  //二氧化硫

    }
}
