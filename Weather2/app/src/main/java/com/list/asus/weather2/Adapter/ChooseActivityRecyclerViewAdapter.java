package com.list.asus.weather2.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.list.asus.weather2.R;

import java.util.ArrayList;

/**
 * Created by lilei on 2017/2/16.
 */

public class ChooseActivityRecyclerViewAdapter extends RecyclerView.Adapter<ChooseActivityRecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> ChoosedDatailList = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView cityName;
        Button decreaseButton;

        public ViewHolder(View view){
            super(view);
            cityName = (TextView) view.findViewById(R.id.choose_activity_city_name);
            decreaseButton = (Button) view.findViewById(R.id.choose_activity_delete_button);
        }
    }

    public ChooseActivityRecyclerViewAdapter(ArrayList<String> mChoosedDatails){
        this.ChoosedDatailList = mChoosedDatails;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_recyclerview_content, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String cityName = ChoosedDatailList.get(position);
        holder.cityName.setText(cityName);

    }

    @Override
    public int getItemCount() {
        return ChoosedDatailList.size();
    }

}
