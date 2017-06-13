package com.weather.weatherapp.recycler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weather.weatherapp.R;
import com.weather.weatherapp.Utils;
import com.weather.weatherapp.model.ForecastItem;

import java.util.List;

/**
 *
 * Used pattern:
 * Adapter - android implementation of Adapter
 *
 */

public class ForecastAdapter extends RecyclerView.Adapter<WeatherViewHolder> {

    private List<ForecastItem> list;
    private Context context;

    public ForecastAdapter(Context context, List<ForecastItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new WeatherViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.temperature.setText(Double.toString(list.get(position).getTemperature()) + " \u00B0C");
        holder.icon.setImageResource(Utils.getWeatherIconLocation(context, list.get(position).getIcon()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}





