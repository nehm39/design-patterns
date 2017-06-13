package com.weather.weatherapp.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.weather.weatherapp.MainActivity;
import com.weather.weatherapp.R;
import com.weather.weatherapp.Utils;
import com.weather.weatherapp.api.model.CurrentWeatherApi;
import com.weather.weatherapp.model.Weather;
import com.weather.weatherapp.observable.Observers;
import com.weather.weatherapp.recycler.WeatherAdapter;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FindWeatherFragment extends Fragment {

    private EditText etxtAddForecast;
    private WeatherAdapter weatherAdapter;

    public FindWeatherFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_find_weather, container, false);

        RecyclerView recyclerView = (RecyclerView) fragmentView.findViewById(R.id.weatherRecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        weatherAdapter = new WeatherAdapter(getActivity());
        recyclerView.setAdapter(weatherAdapter);

        if (savedInstanceState == null) {
            Weather yourWeather = ((MainActivity) getActivity()).getYourWeather();
            weatherAdapter.add(yourWeather);
        }

        Button btnAdd = (Button) fragmentView.findViewById(R.id.btn_add);
        etxtAddForecast = (EditText) fragmentView.findViewById(R.id.etxt_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etxtAddForecast.getText().toString())) {
                    Toast.makeText(getActivity(), "You need to add location", Toast.LENGTH_SHORT).show();
                    return;
                }

                Observers.getWeatherByCityName(etxtAddForecast.getText().toString())
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
                                Weather weather = Utils.buildWeatherFromApi(currentWeatherApi);
                                weatherAdapter.add(weather);
                                etxtAddForecast.setText("");
                            }
                        });
            }
        });

        return fragmentView;
    }

}
