package com.list.asus.weather2.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.list.asus.weather2.db.ChooseActivityDatail;
import com.list.asus.weather2.R;

import java.util.List;

/**
 * Created by lilei on 2017/2/16.
 */

public class ChooseActivityRecyclerViewAdapter extends RecyclerView.Adapter<ChooseActivityRecyclerViewAdapter.ViewHolder>{

    private List<ChooseActivityDatail> ChoosedDatailList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView cityImge;
        TextView cityName;

        public ViewHolder(View view){
            super(view);
            cityImge = (ImageView) view.findViewById(R.id.choose_activity_city_image);
            cityName = (TextView) view.findViewById(R.id.choose_activity_city_name);
        }
    }

    public ChooseActivityRecyclerViewAdapter(List<ChooseActivityDatail> mChoosedDatails){
        this.ChoosedDatailList = mChoosedDatails;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_choose, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       ChooseActivityDatail chooseActivityDatail = ChoosedDatailList.get(position);
        holder.cityImge.setImageResource(chooseActivityDatail.getImageId());
        holder.cityName.setText(chooseActivityDatail.getName());
    }

    @Override
    public int getItemCount() {
        return ChoosedDatailList.size();
    }

}
