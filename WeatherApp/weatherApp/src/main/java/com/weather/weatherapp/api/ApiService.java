package com.weather.weatherapp.api;

import com.weather.weatherapp.api.model.CurrentWeatherApi;
import com.weather.weatherapp.api.model.ForecastApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * Used pattern:
 * Facade
 *
 */

public interface ApiService {

    @GET("weather")
    Call<CurrentWeatherApi> getWeatherByCityName(@Query("q") String cityName);

    @GET("forecast")
    Call<ForecastApi> getForecastByCityName(@Query("q") String cityName);

    @GET("weather")
    Call<CurrentWeatherApi> getWeatherByCoordinates(@Query("lat") double latitude, @Query("lon") double longitude);
}
