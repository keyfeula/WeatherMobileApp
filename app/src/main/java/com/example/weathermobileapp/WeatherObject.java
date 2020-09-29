package com.example.weathermobileapp;

public class WeatherObject {

    private String main; //Main weather condition for the day, ex. Clouds
    private String description; //Description of weather, ex. scattered clouds

    public WeatherObject(String main, String description) {
        this.main = main;
        this.description = description;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

}
