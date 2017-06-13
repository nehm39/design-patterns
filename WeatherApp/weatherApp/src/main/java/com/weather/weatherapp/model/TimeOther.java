package com.weather.weatherapp.model;

import com.weather.weatherapp.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * Used pattern:
 * Bridge (3/4)
 *
 */

public class TimeOther implements TimeBridge{
    private long time;

    public TimeOther(long time) {
        this.time = time;
    }

    @Override
    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date date = new Date(time * 1000);
        return sdf.format(date) + " " + Utils.getCurrentTimezoneOffset();
    }
}
