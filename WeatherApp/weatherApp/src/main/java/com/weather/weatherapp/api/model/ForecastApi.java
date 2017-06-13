package com.weather.weatherapp.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ForecastApi {

    @SerializedName("city")
    private City city;
    @SerializedName("cod")
    private String cod;
    @SerializedName("message")
    private double message;
    @SerializedName("cnt")
    private int cnt;
    @SerializedName("list")
    private List<ListApi> list = new ArrayList<>();

    public List<ListApi> getList() {
        return list;
    }
}