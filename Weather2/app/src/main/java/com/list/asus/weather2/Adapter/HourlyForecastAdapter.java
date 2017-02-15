package com.list.asus.weather2.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.list.asus.weather2.R;
import com.list.asus.weather2.gson.HourlyForecast;

import java.util.List;

/**
 * Created by HP on 2017/2/14.
 */

public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder> {

    private List<HourlyForecast> hourlyForecastList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView hourlyDateText;
        TextView hourlyInfoText;
        TextView hourlyTempText;

        public ViewHolder(View view) {
            super(view);
            hourlyDateText = (TextView) view.findViewById(R.id.hourly_time_text);
            hourlyInfoText = (TextView) view.findViewById(R.id.hourly_info_text);
            hourlyTempText = (TextView) view.findViewById(R.id.hourly_temp_text);
        }
    }

    public HourlyForecastAdapter(List<HourlyForecast> mhourlyForecastList) {
        hourlyForecastList = mhourlyForecastList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_hourly_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HourlyForecast hourlyForecast = hourlyForecastList.get(position);
        holder.hourlyDateText.setText(String.valueOf(hourlyForecast.date).substring(11));
        holder.hourlyInfoText.setText(hourlyForecast.more.info);
        holder.hourlyTempText.setText(hourlyForecast.tmp + "℃");
    }

    @Override
    public int getItemCount() {
        return hourlyForecastList.size();
    }
}
