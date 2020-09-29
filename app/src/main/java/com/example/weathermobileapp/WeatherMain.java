package com.example.weathermobileapp;

public class WeatherMain {

    //Using strings rather than doubles because I don't do calculations with these values
    private String temp; //temp during middle of day
    private String temp_min; //min temp
    private String temp_max; //max temp
    private String humidity; //night temp

    public WeatherMain(String temp, String temp_min, String temp_max, String humidity) {
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.humidity = humidity;
    }

    public String getTemp() {
        return temp;
    }

    public String getMin() {
        return temp_min;
    }

    public String getMax() {
        return temp_max;
    }

    public String getHumidity() {
        return humidity;
    }

}
