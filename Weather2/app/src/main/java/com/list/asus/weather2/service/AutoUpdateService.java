package com.list.asus.weather2.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import com.list.asus.weather2.bean.AQIWeather;
import com.list.asus.weather2.bean.DailyForecastWeather;
import com.list.asus.weather2.bean.HourlyForecastWeather;
import com.list.asus.weather2.bean.NowWeather;
import com.list.asus.weather2.bean.SuggestionWeather;
import com.list.asus.weather2.util.HttpUtil;
import com.list.asus.weather2.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
/**
 *用于后台自动更新天气
**/
public class AutoUpdateService extends Service {
    public AutoUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateWeather();
        updateBingPic();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 4 * 60 * 1000;  //4小时的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, AutoUpdateService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    //更新天气信息
    private void updateWeather() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather", null);
        if (weatherString != null) {
            //有缓存时直接解析更新天气数据
            NowWeather weather = Utility.handleNowResponse(weatherString);
            assert weather != null;
            String weatherId = weather.getBasic().getLocation();
            getNowWeather(weatherId);
            getDailyWeather(weatherId);
            getHourlyWeather(weatherId);
            getSuggestionWeather(weatherId);
            getAQIWeather(weatherId);
        }
    }

    private void getAQIWeather(final String weatherId) {
        String nowUrl = HttpUtil.BASE_URL + "air/now?location=" + weatherId + "" + HttpUtil.KEY;
        HttpUtil.sendOkHttpRequest(nowUrl, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseText = response.body().string();
                final AQIWeather weather = Utility.handleAQIResponse(responseText);
                if (weather != null && HttpUtil.STATUS.equals(weather.getStatus())){
                    SharedPreferences.Editor editor = PreferenceManager
                            .getDefaultSharedPreferences(AutoUpdateService.this)
                            .edit();
                    editor.putString("aqi_weather" + weatherId,responseText);
                    editor.apply();
                }
            }
        });
    }

    private void getSuggestionWeather(final String weatherId) {
        String nowUrl = HttpUtil.BASE_URL + "weather/lifestyle?location=" + weatherId + HttpUtil.KEY;
        HttpUtil.sendOkHttpRequest(nowUrl, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseText = response.body().string();
                final SuggestionWeather weather = Utility.handleSuggestionResponse(responseText);
                if (weather != null && HttpUtil.STATUS.equals(weather.getStatus())){
                    SharedPreferences.Editor editor = PreferenceManager
                            .getDefaultSharedPreferences(AutoUpdateService.this)
                            .edit();
                    editor.putString("suggestion_weather" + weatherId,responseText);
                    editor.apply();
                }
            }
        });
    }

    private void getHourlyWeather(final String weatherId) {
        String nowUrl = HttpUtil.BASE_URL + "weather/hourly?location=" + weatherId + HttpUtil.KEY;
        HttpUtil.sendOkHttpRequest(nowUrl, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseText = response.body().string();
                final HourlyForecastWeather weather = Utility.handleHourlyResponse(responseText);
                if (weather != null && HttpUtil.STATUS.equals(weather.getStatus())){
                    SharedPreferences.Editor editor = PreferenceManager
                            .getDefaultSharedPreferences(AutoUpdateService.this)
                            .edit();
                    editor.putString("hourly_weather" + weatherId,responseText);
                    editor.apply();
                }
            }
        });
    }

    private void getDailyWeather(final String weatherId) {
        String nowUrl = HttpUtil.BASE_URL + "weather/forecast?location=" + weatherId + HttpUtil.KEY;
        HttpUtil.sendOkHttpRequest(nowUrl, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseText = response.body().string();
                final DailyForecastWeather weather = Utility.handleDailyResponse(responseText);
                if (weather != null && HttpUtil.STATUS.equals(weather.getStatus())){
                    SharedPreferences.Editor editor = PreferenceManager
                            .getDefaultSharedPreferences(AutoUpdateService.this)
                            .edit();
                    editor.putString("daily_weather" + weatherId,responseText);
                    editor.apply();
                }
            }
        });
    }

    private void getNowWeather(final String weatherId) {
        String nowUrl = HttpUtil.BASE_URL + "weather/now?location=" + weatherId + HttpUtil.KEY;
        HttpUtil.sendOkHttpRequest(nowUrl, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseText = response.body().string();
                final NowWeather weather = Utility.handleNowResponse(responseText);
                if (weather != null && HttpUtil.STATUS.equals(weather.getStatus())){
                    SharedPreferences.Editor editor = PreferenceManager
                            .getDefaultSharedPreferences(AutoUpdateService.this)
                            .edit();
                    editor.putString("now_weather" + weatherId,responseText);
                    editor.apply();
                }
            }
        });
    }

    //更新背景图
    private void updateBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager
                        .getDefaultSharedPreferences(AutoUpdateService.this)
                        .edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
            }
        });
    }

}
