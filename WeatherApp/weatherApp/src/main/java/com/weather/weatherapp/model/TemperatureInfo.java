package com.weather.weatherapp.model;

/**
 *
 * Used pattern:
 * Adapter (2/3)
 *
 */

public interface TemperatureInfo {
    public double getTemperatureInF();

    public void setTemperatureInF(double temperatureInF);

    public double getTemperatureInC();

    public void setTemperatureInC(double temperatureInC);

    public double getTemperatureInK();

    public void setTemperatureInK(double temperatureInK);
}
