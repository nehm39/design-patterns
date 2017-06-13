package com.weather.weatherapp.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.weather.weatherapp.R;
import com.weather.weatherapp.Utils;
import com.weather.weatherapp.api.model.ForecastApi;
import com.weather.weatherapp.model.ForecastItem;
import com.weather.weatherapp.observable.Observers;
import com.weather.weatherapp.recycler.ForecastAdapter;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ForecastFragment extends Fragment {

    private View fragmentView;
    private EditText etxtLoadForecast;
    private RecyclerView recyclerView;
    private ForecastAdapter forecastAdapter;


    public ForecastFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_forecast, container, false);

        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.forecastRecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        Button btnLoad = (Button) fragmentView.findViewById(R.id.btn_load);
        etxtLoadForecast = (EditText) fragmentView.findViewById(R.id.etxt_load);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etxtLoadForecast.getText().toString())) {
                    Toast.makeText(getActivity(), "You need to add location", Toast.LENGTH_SHORT).show();
                    return;
                }

                Observers.getForecastByCityName(etxtLoadForecast.getText().toString())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ForecastApi>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("WEATHER_ERROR", e.getMessage());
                            }

                            @Override
                            public void onNext(ForecastApi forecastApi) {
                                List<ForecastItem> forecast = Utils.convertForecastFromApi(forecastApi);
                                forecastAdapter = new ForecastAdapter(getActivity(), forecast);
                                recyclerView.setAdapter(forecastAdapter);
                            }
                        });
            }
        });


        return fragmentView;
    }

}
