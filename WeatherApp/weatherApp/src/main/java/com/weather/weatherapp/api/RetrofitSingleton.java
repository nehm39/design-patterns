package com.weather.weatherapp.api;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * Used pattern:
 * Singleton
 *
 */

public class RetrofitSingleton {

    private static final String API_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String API_KEY = "b105b525924f837912d1ed803cf28137";
    private static ApiService apiService;
    private static Retrofit retrofit;

    public static ApiService getClient() {
        if (apiService == null) {
            apiService = getRetrofit().create(ApiService.class);
        }
        return apiService;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            Gson gson = new Gson();

            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
            okHttpClientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    HttpUrl url = request.url().newBuilder()
                            .addQueryParameter("appid",API_KEY)
                            .addQueryParameter("units", "metric")
                            .build();
                    request = request.newBuilder().url(url).build();
                    return chain.proceed(request);
                }
            });
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(interceptor);

            retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClientBuilder.build())
                    .build();
        }
        return retrofit;
    }
}
