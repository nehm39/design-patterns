package com.weather.weatherapp.observable;

import com.weather.weatherapp.api.RetrofitSingleton;
import com.weather.weatherapp.api.model.CurrentWeatherApi;
import com.weather.weatherapp.api.model.ForecastApi;

import java.io.IOException;

import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

/**
 *
 * Used pattern:
 * Observer
 *
 */

public class Observers {

    public static Observable<CurrentWeatherApi> getWeatherByCityName(final String cityName) {
        return Observable.create(new Observable.OnSubscribe<CurrentWeatherApi>() {
            @Override
            public void call(Subscriber<? super CurrentWeatherApi> subscriber) {
                try {
                    Response<CurrentWeatherApi> response = RetrofitSingleton.getClient().getWeatherByCityName(cityName).execute();
                    subscriber.onNext(response.body());
                    subscriber.onCompleted();
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public static Observable<CurrentWeatherApi> getWeatherByCoordinates(final double lat, final double lon) {
        return Observable.create(new Observable.OnSubscribe<CurrentWeatherApi>() {
            @Override
            public void call(Subscriber<? super CurrentWeatherApi> subscriber) {
                try {
                    Response<CurrentWeatherApi> response = RetrofitSingleton.getClient().getWeatherByCoordinates(lat, lon).execute();
                    subscriber.onNext(response.body());
                    subscriber.onCompleted();
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public static Observable<ForecastApi> getForecastByCityName(final String cityName) {
        return Observable.create(new Observable.OnSubscribe<ForecastApi>() {
            @Override
            public void call(Subscriber<? super ForecastApi> subscriber) {
                try {
                    Response<ForecastApi> response = RetrofitSingleton.getClient().getForecastByCityName(cityName).execute();
                    subscriber.onNext(response.body());
                    subscriber.onCompleted();
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
