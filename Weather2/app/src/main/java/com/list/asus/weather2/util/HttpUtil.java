package com.list.asus.weather2.util;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author 14512
 */
public class HttpUtil {
    public static final String BASE_URL = "https://free-api.heweather.com/s6/";
    public static final String KEY = "&key=ba017b1a8a1f4f0fb5912bb2295d21a9";
    public static final String STATUS = "ok";

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
        Log.e("http", address);
    }

}
