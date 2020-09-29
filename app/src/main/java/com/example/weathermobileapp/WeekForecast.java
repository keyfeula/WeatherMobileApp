package com.example.weathermobileapp;

public class WeekForecast {

    private WeatherObject[] weather; //Array of weather object that have conditions such as cloudy, rainy, etc.
    private WeatherMain main;
    private String dt_txt; //Date and time of weather data record

    public WeekForecast(WeatherObject[] weather, WeatherMain main, String dt_txt) {
        this.weather = weather;
        this.main = main;
        this.dt_txt = dt_txt;
    }

    public WeatherObject[] getWeather() {
        return weather;
    }

    public WeatherMain getWeatherMain() {
        return main;
    }

    public String getDateText() {
        return dt_txt;
    }
    
}
