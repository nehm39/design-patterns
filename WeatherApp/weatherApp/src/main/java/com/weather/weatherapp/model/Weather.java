package com.weather.weatherapp.model;

import com.weather.weatherapp.Utils;

/**
 *
 * Used patterns:
 * Builder (1/2)
 * Prototype
 * Bridge (4/4)
 *
 */

@SuppressWarnings("unused")
public class Weather implements Cloneable {
    private String cityName;
    private double lat;
    private double lon;
    private TemperatureInfo temperature;
    private int humidity;
    private int pressure;
    private TemperatureInfo temp_min;
    private TemperatureInfo temp_max;
    private String icon;
    private TimeBridge sunrise;
    private TimeBridge sunset;

    private Weather(WeatherBuilder builder) {
        this.cityName = builder.cityName;
        this.lat = builder.lat;
        this.lon = builder.lon;
        this.temperature = builder.temperature;
        this.humidity = builder.humidity;
        this.pressure = builder.pressure;
        this.temp_min = builder.temp_min;
        this.temp_max = builder.temp_max;
        this.icon = builder.icon;
        this.sunrise = builder.sunrise;
        this.sunset = builder.sunset;
    }

    public String getCityName() {
        return cityName;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public TemperatureInfo getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public TemperatureInfo getTemp_min() {
        return temp_min;
    }

    public TemperatureInfo getTemp_max() {
        return temp_max;
    }

    public String getIcon() {
        return icon;
    }

    public TimeBridge getSunset() {
        return sunset;
    }

    public TimeBridge getSunrise() {
        return sunrise;
    }

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    public static class WeatherBuilder {
        private String cityName;
        private double lat;
        private double lon;
        private TemperatureInfo temperature;
        private int humidity;
        private int pressure;
        private TemperatureInfo temp_min;
        private TemperatureInfo temp_max;
        private String icon;
        private TimeBridge sunrise;
        private TimeBridge sunset;

        public WeatherBuilder(String cityName) {
            this.cityName = cityName;
        }

        public WeatherBuilder(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public WeatherBuilder cityName(String cityName) {
            this.cityName = cityName;
            return this;
        }

        public WeatherBuilder temperature(double temperature) {
            TemperatureInfo temperatureInfo = new TemperatureReporter();
            temperatureInfo.setTemperatureInC(temperature);
            this.temperature = temperatureInfo;
            return this;
        }

        public WeatherBuilder humidity(int humidity) {
            this.humidity = humidity;
            return this;
        }

        public WeatherBuilder pressure(int pressure) {
            this.pressure = pressure;
            return this;
        }

        public WeatherBuilder temp_min(double temp_min) {
            TemperatureInfo temperatureInfo = new TemperatureReporter();
            temperatureInfo.setTemperatureInC(temp_min);
            this.temp_min = temperatureInfo;
            return this;
        }

        public WeatherBuilder temp_max(double temp_max) {
            TemperatureInfo temperatureInfo = new TemperatureReporter();
            temperatureInfo.setTemperatureInC(temp_max);
            this.temp_max = temperatureInfo;
            return this;
        }

        public WeatherBuilder icon(String icon) {
            this.icon = icon;
            return this;
        }

        /**
         * Bridge pattern
         */

        public WeatherBuilder sunrise(long sunrise) {
            if (Utils.isUTC()) {
                this.sunrise = new TimeUtc(sunrise);
            } else {
                this.sunrise = new TimeOther(sunrise);
            }
            return this;
        }

        public WeatherBuilder sunset(long sunset) {
            if (Utils.isUTC()) {
                this.sunset = new TimeUtc(sunset);
            } else {
                this.sunset = new TimeOther(sunset);
            }
            return this;
        }

        public Weather build() {
            return new Weather(this);
        }
    }
}
