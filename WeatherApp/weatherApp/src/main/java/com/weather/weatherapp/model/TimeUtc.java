package com.weather.weatherapp.model;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * Used pattern:
 * Bridge (2/4)
 *
 */

public class TimeUtc implements TimeBridge {
    private long time;

    public TimeUtc(long time) {
        this.time = time;
    }

    @Override
    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date date = new Date(time * 1000);
        return sdf.format(date);
    }
}
