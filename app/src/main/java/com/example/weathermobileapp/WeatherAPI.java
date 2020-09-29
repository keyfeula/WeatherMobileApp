package com.example.weathermobileapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {
    //Weather forecast API = api.openweathermap.org/data/2.5/forecast?q={city}&appid={API key}&units=imperial
    //APIkey = e067eee45d14bc72b30b317fdae19bb9

    @GET("forecast")
    Call<WeatherRoot> getWeather(@Query("q") String city,
                                     @Query("appid") String APIkey,
                                     @Query("units") String imperialUnits);

}
