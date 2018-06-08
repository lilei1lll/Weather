package com.list.asus.weather2.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.list.asus.weather2.bean.AQIWeather;
import com.list.asus.weather2.bean.DailyForecastWeather;
import com.list.asus.weather2.bean.HourlyForecastWeather;
import com.list.asus.weather2.bean.SuggestionWeather;
import com.list.asus.weather2.bean.db.City;
import com.list.asus.weather2.bean.db.County;
import com.list.asus.weather2.bean.db.Province;
import com.list.asus.weather2.bean.NowWeather;

import org.json.JSONArray;
import org.json.JSONObject;

public class Utility {
    //将返回的JSON数据解析成Weather实体类
    public static NowWeather handleNowResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, NowWeather.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static DailyForecastWeather handleDailyResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, DailyForecastWeather.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static HourlyForecastWeather handleHourlyResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, HourlyForecastWeather.class);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static AQIWeather handleAQIResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, AQIWeather.class);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static SuggestionWeather handleSuggestionResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, SuggestionWeather.class);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    //解析和处理服务器返回的省级数据
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    //解析和处理服务器返回的市级数据
    public static boolean handleCityResponse(String response, int provinceId){
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for(int i = 0; i < allCities.length(); i++ ){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //解析并处理服务器返回的县级数据
    public static boolean handleCountyResponse(String response, int cityId){
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties =new JSONArray(response);
                for(int i = 0; i < allCounties.length(); i++){
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
