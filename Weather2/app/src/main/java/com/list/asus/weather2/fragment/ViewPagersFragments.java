package com.list.asus.weather2.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.list.asus.weather2.Adapter.HourlyForecastAdapter;
import com.list.asus.weather2.C;
import com.list.asus.weather2.R;
import com.list.asus.weather2.bean.AQIWeather;
import com.list.asus.weather2.bean.DailyForecast;
import com.list.asus.weather2.bean.DailyForecastWeather;
import com.list.asus.weather2.bean.HourlyForecastWeather;
import com.list.asus.weather2.bean.NowWeather;
import com.list.asus.weather2.bean.SuggestionWeather;
import com.list.asus.weather2.service.AutoUpdateService;
import com.list.asus.weather2.util.HttpUtil;
import com.list.asus.weather2.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by HP on 2017/2/16.
 */

public class ViewPagersFragments extends Fragment {
    private String weatherIdCity;
    public  void setWeatherID(String id){
        weatherIdCity = id;
    }

    private ScrollView weatherLayout;
    private TextView titleUpdateLocTime, titleUpdateUtcTime, cityNameText,
            degreeText, weatherInfoText, sensibleTempText,
            relativeHumidityText, precipitationText, windDirection1Text, windDirection2Text,
    //AQI
    aqiText, no2Text, o3Text, pm10Text, pm25Text, qltyText,so2Text,
    //Suggestion
    airText, comfortText, carWashText, sportText,
            dressSuggestionText, fluText, travelText, ultravioletText;
    private LinearLayout forecastDailyLayout;
    public SwipeRefreshLayout swipeRefreshLayout;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.view_page_item, null);

        initView();
        weatherLayout.setVisibility(View.INVISIBLE);
        getWeather();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestWeather(weatherIdCity);
            }
        });
        return view;
    }

    private void getWeather() {
        SharedPreferences prefsWeather = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        String nowString  = prefsWeather.getString("now_weather"+weatherIdCity,null);
        String dailyString = prefsWeather.getString("daily_weather"+weatherIdCity, null);
        String hourlyString = prefsWeather.getString("hourly_weather"+weatherIdCity, null);
        String aqiString = prefsWeather.getString("aqi_weather"+weatherIdCity, null);
        String suggestionString = prefsWeather.getString("suggestion_weather"+weatherIdCity, null);
        if (C.Judge(C.cityNameArray, weatherIdCity) && nowString != null || dailyString != null
                || hourlyString != null || aqiString != null || suggestionString != null) {
            NowWeather nowWeather = Utility.handleNowResponse(nowString);
            DailyForecastWeather dailyForecastWeather = Utility.handleDailyResponse(dailyString);
            HourlyForecastWeather hourlyForecastWeather = Utility.handleHourlyResponse(hourlyString);
            AQIWeather aqiWeather = Utility.handleAQIResponse(aqiString);
            SuggestionWeather suggestionWeather = Utility.handleSuggestionResponse(suggestionString);

            if (nowWeather != null) {
                showNowWeather(nowWeather);
            }
            if (dailyForecastWeather != null) {
                showDailyWeather(dailyForecastWeather);
            }
            if (hourlyForecastWeather != null) {
                showHourlyWeather(hourlyForecastWeather);
            }
            if (aqiWeather != null) {
                showAQIWeather(aqiWeather);
            }
            if (suggestionWeather != null) {
                showSuggestionWeather(suggestionWeather);
            }
        } else {
            requestWeather(weatherIdCity);
        }
    }

    private void initView() {
        weatherLayout = (ScrollView) view.findViewById(R.id.weather_layout);
        cityNameText = (TextView) view.findViewById(R.id.city_name_text);
        titleUpdateLocTime = (TextView) view.findViewById(R.id.title_update_loc_time);
        titleUpdateUtcTime = (TextView) view.findViewById(R.id.title_update_utc_time);
        //Now
        degreeText = (TextView) view.findViewById(R.id.degree_text);
        sensibleTempText = (TextView) view.findViewById(R.id.sensible_temp_text);
        relativeHumidityText = (TextView) view.findViewById(R.id.relative_humidity_text);
        precipitationText = (TextView) view.findViewById(R.id.precipitation_text);
        windDirection1Text = (TextView) view.findViewById(R.id.wind_direction_text);
        windDirection2Text = (TextView) view.findViewById(R.id.wind_text);
        weatherInfoText = (TextView) view.findViewById(R.id.weather_into_text);
        //Daily布局
        forecastDailyLayout = (LinearLayout) view.findViewById(R.id.forecast_daily_layout);
        //AQI
        aqiText = (TextView) view.findViewById(R.id.aqi_text);
        no2Text = (TextView) view.findViewById(R.id.no2_text);
        o3Text = (TextView) view.findViewById(R.id.o3_text);
        pm10Text = (TextView) view.findViewById(R.id.pm10_text);
        pm25Text = (TextView) view.findViewById(R.id.pm25_text);
        qltyText = (TextView) view.findViewById(R.id.qlty_text);
        so2Text = (TextView) view.findViewById(R.id.so2_text);
        //Suggestion
        comfortText = (TextView) view.findViewById(R.id.comfort_text);
        carWashText = (TextView) view.findViewById(R.id.car_wash_text);
        sportText = (TextView) view.findViewById(R.id.sport_text);
        dressSuggestionText = (TextView) view.findViewById(R.id.dress_suggestion_text);
        fluText = (TextView) view.findViewById(R.id.flu_text);
        airText = (TextView) view.findViewById(R.id.air_text);
        travelText = (TextView) view.findViewById(R.id.travel_text);
        ultravioletText = (TextView) view.findViewById(R.id.ultraviolet_text);
        //下拉刷新
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

    }

    //根据城市id请求天气信息
    public void requestWeather(final String weatherId) {
        getNowWeather(weatherId);
        getDailyWeather(weatherId);
        getHourlyWeather(weatherId);
        getSuggestionWeather(weatherId);
        getAQIWeather(weatherId);
        //开启后台服务，进行更新
        Intent intent = new Intent(getActivity(), AutoUpdateService.class);
        getActivity().startService(intent);
    }

    private void getAQIWeather(final String weatherId) {
        String nowUrl = HttpUtil.BASE_URL + "air/now?location=" + weatherId + HttpUtil.KEY;
        HttpUtil.sendOkHttpRequest(nowUrl, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getActivity(), "无法连接数据，获取天气信息失败",
//                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseText = response.body().string();
                final AQIWeather weather = Utility.handleAQIResponse(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && HttpUtil.STATUS.equals(weather.getStatus())){
                            SharedPreferences.Editor editor = PreferenceManager
                                    .getDefaultSharedPreferences(getActivity())
                                    .edit();
                            editor.putString("aqi_weather" + weatherId,responseText);
                            editor.apply();
                            showAQIWeather(weather);
                        }else {
//                            Toast.makeText(getActivity(), "获取天气信息失败",
//                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void getSuggestionWeather(final String weatherId) {
        String nowUrl = HttpUtil.BASE_URL + "weather/lifestyle?location=" + weatherId + HttpUtil.KEY;
        HttpUtil.sendOkHttpRequest(nowUrl, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getActivity(), "无法连接数据，获取天气信息失败",
//                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseText = response.body().string();
                final SuggestionWeather weather = Utility.handleSuggestionResponse(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && HttpUtil.STATUS.equals(weather.getStatus())){
                            SharedPreferences.Editor editor = PreferenceManager
                                    .getDefaultSharedPreferences(getActivity())
                                    .edit();
                            editor.putString("suggestion_weather" + weatherId,responseText);
                            editor.apply();
                            showSuggestionWeather(weather);
                        }else {
//                            Toast.makeText(getActivity(), "获取天气信息失败",
//                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void getHourlyWeather(final String weatherId) {
        String nowUrl = HttpUtil.BASE_URL + "weather/hourly?location=" + weatherId + HttpUtil.KEY;
        HttpUtil.sendOkHttpRequest(nowUrl, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getActivity(), "无法连接数据，获取天气信息失败",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseText = response.body().string();
                final HourlyForecastWeather weather = Utility.handleHourlyResponse(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && HttpUtil.STATUS.equals(weather.getStatus())){
                            SharedPreferences.Editor editor = PreferenceManager
                                    .getDefaultSharedPreferences(getActivity())
                                    .edit();
                            editor.putString("hourly_weather" + weatherId,responseText);
                            editor.apply();
                            showHourlyWeather(weather);
                        }else {
//                            Toast.makeText(getActivity(), "获取天气信息失败",
//                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void getDailyWeather(final String weatherId) {
        String nowUrl = HttpUtil.BASE_URL + "weather/forecast?location=" + weatherId + HttpUtil.KEY;
        HttpUtil.sendOkHttpRequest(nowUrl, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getActivity(), "无法连接数据，获取天气信息失败",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseText = response.body().string();
                final DailyForecastWeather weather = Utility.handleDailyResponse(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && HttpUtil.STATUS.equals(weather.getStatus())){
                            SharedPreferences.Editor editor = PreferenceManager
                                    .getDefaultSharedPreferences(getActivity())
                                    .edit();
                            editor.putString("daily_weather" + weatherId,responseText);
                            editor.apply();
                            showDailyWeather(weather);
                        }else {
//                            Toast.makeText(getActivity(), "获取天气信息失败",
//                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void getNowWeather(final String weatherId) {
        String nowUrl = HttpUtil.BASE_URL + "weather/now?location=" + weatherId + HttpUtil.KEY;
        HttpUtil.sendOkHttpRequest(nowUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "无法连接数据，获取天气信息失败",
                                Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseText = response.body().string();
                final NowWeather weather = Utility.handleNowResponse(responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && HttpUtil.STATUS.equals(weather.getStatus())){
                            SharedPreferences.Editor editor = PreferenceManager
                                    .getDefaultSharedPreferences(getActivity())
                                    .edit();
                            editor.putString("now_weather" + weatherId,responseText);
                            editor.apply();
                            showNowWeather(weather);
                            swipeRefreshLayout.setRefreshing(false);
                        }else {
                            Toast.makeText(getActivity(), "获取天气信息失败",
                                    Toast.LENGTH_SHORT).show();
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                });
            }
        });
    }

    //处理并展示Weather实体类中的数据

    private void showNowWeather(NowWeather weather) {
        String cityName = weather.getBasic().getLocation();
        String updateLocTime = "loc：" + weather.getUpdate().getLoc();
        String updateUtcTime = "utc：" + weather.getUpdate().getUtc();
        String degree = weather.getNow().getTmp() + "℃";
        String sensibleTemp = "体感温度：" + weather.getNow().getFl() + "℃";
        String relativeHumidity = "相对湿度：" + weather.getNow().getHum() + "%";
        String precipitation = "降水量：" + weather.getNow().getPcpn() + "mm";
        String windDirection1 = "风向：" + weather.getNow().getWindDeg() + "°";
        String windDirection2 = "风向：" + weather.getNow().getWindDir();
        String weatherInfo = weather.getNow().getCondTxt();
        cityNameText.setText(cityName);
        titleUpdateLocTime.setText(updateLocTime);
        titleUpdateUtcTime.setText(updateUtcTime);
        degreeText.setText(degree);
        sensibleTempText.setText(sensibleTemp);
        relativeHumidityText.setText(relativeHumidity);
        precipitationText.setText(precipitation);
        windDirection1Text.setText(windDirection1);
        windDirection2Text.setText(windDirection2);
        weatherInfoText.setText(weatherInfo);
    }

    @SuppressLint("SetTextI18n")
    private void showDailyWeather(DailyForecastWeather weather) {
        //加载daily布局
        forecastDailyLayout.removeAllViews();
        for (DailyForecast dailyForecast : weather.getDailyForecast()){
            View view = LayoutInflater.from(getContext()).inflate(R.layout.forecast_daily_item,
                    forecastDailyLayout, false);
            TextView dateText = (TextView) view.findViewById(R.id.days_text);
            TextView infoText = (TextView) view.findViewById(R.id.info_text);
            TextView maxText = (TextView) view.findViewById(R.id.min_max_text);
            dateText.setText(dailyForecast.getDate());
            infoText.setText(dailyForecast.getCondTxtD() + " " + dailyForecast.getCondTxtN());
            maxText.setText(dailyForecast.getTmpMin() + "℃" + "~" + dailyForecast.getTmpMax() + "℃");
            forecastDailyLayout.addView(view);
        }
    }

    private void showHourlyWeather(HourlyForecastWeather weather) {
        //加载Hourly布局
        RecyclerView recyclerView = (RecyclerView)
                view.findViewById(R.id.forecast_hourly_recycler_view_layout);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        HourlyForecastAdapter hourlyForecastAdapter =
                new HourlyForecastAdapter(weather.getHourly());
        recyclerView.setAdapter(hourlyForecastAdapter);
        Log.e("fragment", weather.getHourly().size()+"");
    }

    private void showSuggestionWeather(SuggestionWeather weather) {
        String comfort = "舒适度指数：" + weather.getLifestyle().get(0).getBrf()
                + "\n\n" + weather.getLifestyle().get(0).getTxt();
        String carWash = "洗车指数：" + weather.getLifestyle().get(6).getBrf()
                + "\n\n" + weather.getLifestyle().get(6).getTxt();
        String sport = "运动指数：" + weather.getLifestyle().get(3).getBrf()
                + "\n\n" + weather.getLifestyle().get(3).getTxt();
        String dressSuggestion = "穿衣指数：" + weather.getLifestyle().get(1).getBrf()
                + "\n\n" + weather.getLifestyle().get(1).getTxt();
        String flu = "感冒指数：" + weather.getLifestyle().get(2).getBrf()
                + "\n\n" + weather.getLifestyle().get(2).getTxt();
        String air = "空气条件：" + weather.getLifestyle().get(7).getBrf()
         + "\n\n" + weather.getLifestyle().get(7).getTxt();
        String ultraviolet = "紫外线指数：" + weather.getLifestyle().get(5).getBrf()
                + "\n\n" + weather.getLifestyle().get(5).getTxt();
        String travel = "旅行指数：" + weather.getLifestyle().get(4).getBrf()
                + "\n\n" + weather.getLifestyle().get(4).getTxt();
        comfortText.setText(comfort);
        carWashText.setText(carWash);
        sportText.setText(sport);
        dressSuggestionText.setText(dressSuggestion);
        fluText.setText(flu);
        airText.setText(air);
        ultravioletText.setText(ultraviolet);
        travelText.setText(travel);
        weatherLayout.setVisibility(View.VISIBLE);
    }

    private void showAQIWeather(AQIWeather weather) {
        AQIWeather.AirNowCity aqi = weather.getAirNowCity();
        if (aqi != null){
            aqiText.setText(aqi.getAqi());
            no2Text.setText(aqi.getNo2());
            o3Text.setText(aqi.getO3());
            pm10Text.setText(aqi.getPm10());
            pm25Text.setText(aqi.getPm25());
            qltyText.setText(aqi.getQlty());
            so2Text.setText(aqi.getSo2());
        }
    }
}
