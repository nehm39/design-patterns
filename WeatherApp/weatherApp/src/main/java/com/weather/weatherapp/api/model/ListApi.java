package com.weather.weatherapp.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ListApi {

    @SerializedName("dt")
    public int dt;
    @SerializedName("main")
    public MainApi main;
    @SerializedName("weather")
    public List<WeatherApi> weather = new ArrayList<>();
    @SerializedName("clouds")
    public Clouds clouds;
    @SerializedName("wind")
    public Wind wind;
    @SerializedName("rain")
    public Rain rain;
    @SerializedName("sys")
    public Sys sys;
    @SerializedName("dt_txt")
    public String dtTxt;
}
