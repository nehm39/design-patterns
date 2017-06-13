package com.weather.weatherapp.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.weather.weatherapp.R;
import com.weather.weatherapp.Utils;
import com.weather.weatherapp.api.model.CurrentWeatherApi;
import com.weather.weatherapp.model.Weather;
import com.weather.weatherapp.observable.Observers;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 * Used pattern:
 * Command (2/2)
 *
 */

public class YourWeatherFragment extends Fragment {

    private static final String defaultCity = "Rzeszow";

    private View fragmentView;
    private TextView txtTemperature;

    private ImageView refreshButton;
    private Weather loadedWeather;

    private OnWeatherLoadedListener onWeatherLoadedListener;

    public YourWeatherFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_your_weather, container, false);

        refreshButton = (ImageView) fragmentView.findViewById(R.id.refresh);
        txtTemperature = (TextView) fragmentView.findViewById(R.id.temperature);


        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeatherByCityName(defaultCity);
                YoYo.with(Techniques.Bounce)
                        .duration(2000)
                        .playOn(fragmentView.findViewById(R.id.refresh));
            }
        });
        LocationManager mLocMgr = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        String locationProvider = LocationManager.NETWORK_PROVIDER;
        android.location.Location location = mLocMgr.getLastKnownLocation(locationProvider);
        if (location != null) {
            getWeatherByCoordinates(location.getLatitude(), location.getLongitude());
        } else {
            getWeatherByCityName(defaultCity);
        }

        Button btnCelsius = (Button) fragmentView.findViewById(R.id.btn_celsius);
        Button btnKelvin = (Button) fragmentView.findViewById(R.id.btn_kelvin);
        Button btnFahrenheit = (Button) fragmentView.findViewById(R.id.btn_fahrenheit);

        View.OnClickListener temperatureConversionClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTemperature(v.getId());
            }
        };

        btnCelsius.setOnClickListener(temperatureConversionClickListener);
        btnKelvin.setOnClickListener(temperatureConversionClickListener);
        btnFahrenheit.setOnClickListener(temperatureConversionClickListener);

        return fragmentView;
    }

    @SuppressLint("SetTextI18n")
    private void convertTemperature(int buttonId) {
        String temp;
        switch (buttonId) {
            default:
            case R.id.btn_celsius:
                temp = Double.toString(loadedWeather.getTemperature().getTemperatureInC()) + " \u00B0C";
                break;
            case R.id.btn_kelvin:
                temp = Double.toString(loadedWeather.getTemperature().getTemperatureInK()) + " \u00B0K";
                break;
            case R.id.btn_fahrenheit:
                temp = Double.toString(loadedWeather.getTemperature().getTemperatureInF()) + " \u00B0F";
                break;
        }
        txtTemperature.setText(temp);
    }

    private void getWeatherByCityName(String cityName) {
        if (Utils.isNetworkAvailable(getActivity())) {
            Observers.getWeatherByCityName(cityName)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<CurrentWeatherApi>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(CurrentWeatherApi currentWeatherApi) {
                            loadedWeather = Utils.buildWeatherFromApi(currentWeatherApi);
                            if (onWeatherLoadedListener != null) {
                                //Clone pattern
                                onWeatherLoadedListener.onWeatherLoaded((Weather) loadedWeather.clone());
                            }
                            setViews();
                        }
                    });
        } else {
            Toast.makeText(getActivity(), "Network is unavailable", Toast.LENGTH_LONG).show();
        }
    }

    private void getWeatherByCoordinates(double lat, double lon) {
        Observers.getWeatherByCoordinates(lat, lon)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CurrentWeatherApi>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CurrentWeatherApi currentWeatherApi) {
                        loadedWeather = Utils.buildWeatherFromApi(currentWeatherApi);
                        if (onWeatherLoadedListener != null) {
                            //Clone pattern
                            onWeatherLoadedListener.onWeatherLoaded((Weather) loadedWeather.clone());
                        }
                        setViews();
                    }
                });
    }

    @SuppressLint("SetTextI18n")
    private void setViews() {
        TextView cityName = (TextView) fragmentView.findViewById(R.id.cityName);
        ImageView icon = (ImageView) fragmentView.findViewById(R.id.weatherIcon);
        TextView humidity = (TextView) fragmentView.findViewById(R.id.humidity);
        TextView pressure = (TextView) fragmentView.findViewById(R.id.pressure);
        TextView sunset = (TextView) fragmentView.findViewById(R.id.sunset);
        TextView sunrise = (TextView) fragmentView.findViewById(R.id.sunrise);

        cityName.setText(loadedWeather.getCityName());
        txtTemperature.setText(Double.toString(loadedWeather.getTemperature().getTemperatureInC()) + " \u00B0C");
        icon.setImageResource(Utils.getWeatherIconLocation(getActivity(), loadedWeather.getIcon()));
        humidity.setText(Integer.toString(loadedWeather.getHumidity()) + "%");
        pressure.setText(Integer.toString(loadedWeather.getPressure()) + "hpa");
        sunrise.setText(loadedWeather.getSunrise().getTime());
        sunset.setText(loadedWeather.getSunset().getTime());
    }

    /**
     * Command pattern
     */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnWeatherLoadedListener) {
            onWeatherLoadedListener = (OnWeatherLoadedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnWeatherLoadedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onWeatherLoadedListener = null;
    }

    public interface OnWeatherLoadedListener {
        void onWeatherLoaded(Weather weather);
    }
}
