package com.weather.weatherapp.recycler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weather.weatherapp.R;
import com.weather.weatherapp.Utils;
import com.weather.weatherapp.model.Weather;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Used pattern:
 * Adapter - android implementation of Adapter
 *
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {

    private Context context;
    private List<Weather> weathers = new ArrayList<>();

    public WeatherAdapter(Context context) {
        this.context = context;
    }

    public void add (Weather weather) {
        weathers.add(weather);
        notifyDataSetChanged();
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new WeatherViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        holder.name.setText(weathers.get(position).getCityName());
        holder.temperature.setText(Double.toString(weathers.get(position).getTemperature().getTemperatureInC()) + " \u00B0C");
        holder.icon.setImageResource(Utils.getWeatherIconLocation(context, weathers.get(position).getIcon()));
    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }
}
