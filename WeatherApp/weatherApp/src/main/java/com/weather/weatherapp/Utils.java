package com.weather.weatherapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.weather.weatherapp.api.model.CurrentWeatherApi;
import com.weather.weatherapp.api.model.ForecastApi;
import com.weather.weatherapp.api.model.ListApi;
import com.weather.weatherapp.model.ForecastItem;
import com.weather.weatherapp.model.Weather;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Used pattern:
 * Builder (2/2)
 */

public class Utils {

    public static Weather buildWeatherFromApi(CurrentWeatherApi currentWeatherApi) {
        return new Weather.WeatherBuilder(currentWeatherApi.getCoord().getLat(), currentWeatherApi.getCoord().getLon())
                .cityName(currentWeatherApi.getName())
                .temperature((int) currentWeatherApi.getMain().getTemp())
                .humidity(currentWeatherApi.getMain().getHumidity())
                .pressure((int) currentWeatherApi.getMain().getPressure())
                .temp_min((int) currentWeatherApi.getMain().getTempMin())
                .temp_max((int) currentWeatherApi.getMain().getTempMax())
                .icon(currentWeatherApi.getWeather().get(0).getIcon())
                .sunrise(currentWeatherApi.getSys().getSunrise())
                .sunset(currentWeatherApi.getSys().getSunset())
                .build();
    }

    public static int getWeatherIconLocation(Context context, String icon) {
        return context.getResources().getIdentifier("drawable/w" + icon,
                null, context.getPackageName());
    }

    public static List<ForecastItem> convertForecastFromApi(ForecastApi forecastApi) {
        List<ForecastItem> list = new ArrayList<>();
        for (ListApi listApi : forecastApi.getList()) {
            ForecastItem forecastItem = new ForecastItem();
            forecastItem.setName(listApi.dtTxt);
            forecastItem.setIcon(listApi.weather.get(0).getIcon());
            forecastItem.setTemperature(listApi.main.getTemp());
            list.add(forecastItem);
        }
        return list;
    }

    public static boolean isUTC() {
        TimeZone tz = TimeZone.getDefault();
        Calendar cal = GregorianCalendar.getInstance(tz);
        int offsetInMillis = tz.getOffset(cal.getTimeInMillis());
        return offsetInMillis == 0;
    }

    @SuppressLint("DefaultLocale")
    public static String getCurrentTimezoneOffset() {
        TimeZone tz = TimeZone.getDefault();
        Calendar cal = GregorianCalendar.getInstance(tz);
        int offsetInMillis = tz.getOffset(cal.getTimeInMillis());
        String offset = String.format("%02d:%02d", Math.abs(offsetInMillis / 3600000), Math.abs((offsetInMillis / 60000) % 60));
        offset = "GMT" + (offsetInMillis >= 0 ? "+" : "-") + offset;
        return offset;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }
}
