package com.weather.weatherapp.model;

/**
 *
 * Used pattern:
 * Adapter (3/3)
 *
 */

public class TemperatureReporter implements TemperatureInfo {
    Temperature temperature;

    public TemperatureReporter() {
        temperature = new Temperature();
    }

    @Override
    public double getTemperatureInF() {
        return convertCtoF(temperature.getTemperature());
    }

    @Override
    public void setTemperatureInF(double temperatureInF) {
        temperature.setTemperature(convertFtoC(temperatureInF));
    }

    @Override
    public double getTemperatureInC() {
        return temperature.getTemperature();
    }

    @Override
    public void setTemperatureInC(double temperatureInC) {
        temperature.setTemperature(temperatureInC);
    }

    @Override
    public double getTemperatureInK() {
        return convertCtoK(temperature.getTemperature());
    }

    @Override
    public void setTemperatureInK(double temperatureInK) {
        temperature.setTemperature(convertKtoC(temperatureInK));
    }

    private double convertFtoC(double f) {
        return ((f - 32) * 5 / 9);
    }

    private double convertCtoF(double c) {
        return ((c * 9 / 5) + 32);
    }

    private double convertCtoK(double c) {
        return c + 273.15;
    }

    private double convertKtoC(double k) {
        return k - 273.15;
    }
}
