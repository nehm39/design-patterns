package com.weather.weatherapp.api.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Sys {

    @SerializedName("type")
    private int type;
    @SerializedName("id")
    private int id;
    @SerializedName("message")
    private double message;
    @SerializedName("country")
    private String country;
    @SerializedName("sunrise")
    private long sunrise;
    @SerializedName("sunset")
    private long sunset;
    @SerializedName("population")
    private int population;
    @SerializedName("pod")
    private String pod;

    public long getSunset() {
        return sunset;
    }

    public long getSunrise() {
        return sunrise;
    }
}