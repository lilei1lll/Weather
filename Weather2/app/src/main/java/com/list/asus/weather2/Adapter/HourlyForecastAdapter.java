package com.list.asus.weather2.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.list.asus.weather2.R;
import com.list.asus.weather2.bean.HourlyForecast;

import java.util.List;

/**
 * Created by HP on 2017/2/14.
 */

public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder> {
    private Context mContext;
    private List<HourlyForecast> hourlyForecastList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView hourlyInfoIv;
        TextView hourlyDateText;
        TextView hourlyInfoText;
        TextView hourlyTempText;

        public ViewHolder(View view) {
            super(view);
            hourlyInfoIv = (ImageView) view.findViewById(R.id.hourly_info_iv);
            hourlyDateText = (TextView) view.findViewById(R.id.hourly_time_text);
            hourlyInfoText = (TextView) view.findViewById(R.id.hourly_info_text);
            hourlyTempText = (TextView) view.findViewById(R.id.hourly_temp_text);
        }
    }

    public HourlyForecastAdapter(List<HourlyForecast> hourlyForecastList) {
        this.hourlyForecastList = hourlyForecastList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.forecast_hourly_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HourlyForecast hourlyForecast = hourlyForecastList.get(position);
        String icon = "file:///android_asset/cond_icon_heweather/" +
                hourlyForecast.getCondCode() +".png";
        Glide.with(mContext).load(icon).into(holder.hourlyInfoIv);
        holder.hourlyDateText.setText(String.valueOf(hourlyForecast.getTime()).substring(11));
        holder.hourlyInfoText.setText(hourlyForecast.getCondTxt());
        holder.hourlyTempText.setText(hourlyForecast.getTmp() + "℃");
    }

    @Override
    public int getItemCount() {
        return hourlyForecastList.size();
    }
}
