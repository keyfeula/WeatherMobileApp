package com.example.weathermobileapp;

public class WeatherRoot {

    private WeekForecast[] list; //Array of WeekForecast object that have weather data for the next 7 days

    public WeatherRoot(WeekForecast[] list) {
        this.list = list;
    }

    public WeekForecast[] getList() {
        return list;
    }
}
