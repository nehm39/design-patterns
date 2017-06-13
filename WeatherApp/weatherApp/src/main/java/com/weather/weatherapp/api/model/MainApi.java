package com.weather.weatherapp.api.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class MainApi {

    @SerializedName("temp")
    private double temp;
    @SerializedName("pressure")
    private double pressure;
    @SerializedName("humidity")
    private int humidity;
    @SerializedName("temp_min")
    private double tempMin;
    @SerializedName("temp_max")
    private double tempMax;
    @SerializedName("sea_level")
    private double seaLevel;
    @SerializedName("grnd_level")
    private double grndLevel;
    @SerializedName("temp_kf")
    private double tempKf;

    public double getTemp() {
        return temp;
    }

    public double getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }
}