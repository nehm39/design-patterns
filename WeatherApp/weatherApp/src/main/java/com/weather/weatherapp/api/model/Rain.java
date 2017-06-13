package com.weather.weatherapp.api.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Rain {

    @SerializedName("1h")
    private double _1h;

    @SerializedName("3h")
    public double _3h;

}