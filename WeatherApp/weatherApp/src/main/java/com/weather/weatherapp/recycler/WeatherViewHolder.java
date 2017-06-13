package com.weather.weatherapp.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.weather.weatherapp.R;

public class WeatherViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView temperature;
    ImageView icon;

    public WeatherViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.txt_name);
        temperature = (TextView) itemView.findViewById(R.id.txt_temp);
        icon = (ImageView) itemView.findViewById(R.id.img_icon);
    }
}