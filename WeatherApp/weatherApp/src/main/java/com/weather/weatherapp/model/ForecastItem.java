package com.weather.weatherapp.model;

public class ForecastItem {
    private String name;
    private double temperature;
    private String icon;

    public String getName() {
        return name;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getIcon() {
        return icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
