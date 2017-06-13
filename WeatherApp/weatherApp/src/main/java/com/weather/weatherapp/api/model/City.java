package com.weather.weatherapp.api.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class City {

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("coord")
    public Coord coord;
    @SerializedName("country")
    public String country;
    @SerializedName("population")
    public int population;
    @SerializedName("sys")
    public Sys sys;

}