package com.example.weathermobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.Calendar;
import java.util.Locale;

public class WeatherActivity extends AppCompatActivity {

    private TextView day1Weather;
    private TextView day2Weather;
    private TextView day3Weather;
    private TextView day4Weather;
    private TextView day5Weather;
    private ImageView day1Img;
    private ImageView day2Img;
    private ImageView day3Img;
    private ImageView day4Img;
    private ImageView day5Img;
    private TextView day1Weekday;
    private TextView day2Weekday;
    private TextView day3Weekday;
    private TextView day4Weekday;
    private TextView day5Weekday;
    private String[] tempArr = new String[10];
    private String[] weatherCondArr = new String[10];
    private String errorMsg = "None";
    private String city = "";
    String APIkey = "e067eee45d14bc72b30b317fdae19bb9";
    String units = "imperial";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        day1Weather = findViewById(R.id.day1Weather);
        day2Weather = findViewById(R.id.day2Weather);
        day3Weather = findViewById(R.id.day3Weather);
        day4Weather = findViewById(R.id.day4Weather);
        day5Weather = findViewById(R.id.day5Weather);
        day1Img = findViewById(R.id.day1ImageView);
        day2Img = findViewById(R.id.day2ImageView);
        day3Img = findViewById(R.id.day3ImageView);
        day4Img = findViewById(R.id.day4ImageView);
        day5Img = findViewById(R.id.day5ImageView);
        day1Weekday = findViewById(R.id.day1WeekdayText);
        day2Weekday = findViewById(R.id.day2WeekdayText);
        day3Weekday = findViewById(R.id.day3WeekdayText);
        day4Weekday = findViewById(R.id.day4WeekdayText);
        day5Weekday = findViewById(R.id.day5WeekdayText);

        Intent intent = getIntent();
        city = intent.getStringExtra("cityName");

        getWeather();
    }

    private void displayForecast(){

        List<TextView> dayViews = new ArrayList<TextView>();
        dayViews.add(day1Weather); dayViews.add(day2Weather); dayViews.add(day3Weather); dayViews.add(day4Weather); dayViews.add(day5Weather);
        List<ImageView> imgViews = new ArrayList<ImageView>();
        imgViews.add(day1Img); imgViews.add(day2Img); imgViews.add(day3Img); imgViews.add(day4Img); imgViews.add(day5Img);

        //Display days of week
        Calendar sCalendar = Calendar.getInstance();
        String dayLongName = sCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        day1Weekday.append(dayLongName.substring(0,3));
        sCalendar.add(Calendar.DATE, 1);
        dayLongName = sCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        day2Weekday.append(dayLongName.substring(0,3));
        sCalendar.add(Calendar.DATE, 1);
        dayLongName = sCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        day3Weekday.append(dayLongName.substring(0,3));
        sCalendar.add(Calendar.DATE, 1);
        dayLongName = sCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        day4Weekday.append(dayLongName.substring(0,3));
        sCalendar.add(Calendar.DATE, 1);
        dayLongName = sCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        day5Weekday.append(dayLongName.substring(0,3));

        //Display five day forecast in each panel
        for(int i = 0; i < 5; i++){
            int dotIndex = tempArr[i].indexOf(".");
            String roundedTemp = "";
            if (dotIndex != -1)
            {
                roundedTemp = tempArr[i].substring(0 , dotIndex);
            }
            dayViews.get(i).append(roundedTemp + "\u00b0" + "F");
        }

        //Change svg icon to appropriate weather condition
        for (int i = 0; i < 5; i++){
            if (weatherCondArr[i].toLowerCase().contains("clear")) {
                imgViews.get(i).setImageResource(R.drawable.sunny);
            }
            else if (weatherCondArr[i].toLowerCase().contains("cloud")){
                if (weatherCondArr[i].toLowerCase().contains("overcast")){
                    imgViews.get(i).setImageResource(R.drawable.overcast);
                }
                else if (weatherCondArr[i].toLowerCase().contains("rain")){
                    imgViews.get(i).setImageResource(R.drawable.rain);
                }
                else{
                    imgViews.get(i).setImageResource(R.drawable.partlycloudy);
                }
            }
            else{
                imgViews.get(i).setImageResource(R.drawable.sunny);
            }
        }

    }

    private void getWeather() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);
        Call<WeatherRoot> call = weatherAPI.getWeather(city, APIkey, units);

        call.enqueue(new Callback<WeatherRoot>() {
            @Override
            public void onResponse(Call<WeatherRoot> call, Response<WeatherRoot> response) {

                if (!response.isSuccessful()) {
                    errorMsg = "Code" + response.code();
                    return;
                }

                WeatherRoot weather = response.body();
                int tempSize = 0;
                int condSize = 0;
                for (int i = 0; i < weather.getList().length; i++) {
                    if (weather.getList()[i].getDateText().contains("00:00:00")) {
                        tempArr[tempSize] = weather.getList()[i].getWeatherMain().getMax(); //Copy max temps for 7 days to tempArr array
                        tempSize++;
                    }
                    if (weather.getList()[i].getDateText().contains("12:00:00")) {
                        weatherCondArr[condSize] = weather.getList()[i].getWeather()[0].getDescription(); //Copy weather conditions to weatherCond array
                        condSize++;
                    }
                }

                displayForecast();

            }

            @Override
            public void onFailure(Call<WeatherRoot> call, Throwable t) {
                errorMsg = t.getMessage();
            }

        });

    }
}