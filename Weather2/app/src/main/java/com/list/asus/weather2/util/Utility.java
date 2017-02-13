package com.list.asus.weather2.util;

import android.util.Log;

import com.google.gson.Gson;
import com.list.asus.weather2.gson.Weather;

import org.json.JSONArray;
import org.json.JSONObject;

public class Utility {
    //将返回的JSON数据解析成Weather实体类
    public static Weather handleWeatherResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather5");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
