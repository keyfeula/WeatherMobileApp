package com.example.weathermobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button getWeatherBtn;
    /*private String[] tempArr = new String[10];
    private String[] weatherCondArr = new String[10];
    private String errorMsg = "None";
    String APIkey = "e067eee45d14bc72b30b317fdae19bb9";
    String units = "imperial";*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWeatherBtn = findViewById(R.id.getWeatherBtn);
        getWeatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getWeather();
                openWeatherActivity();
                //textViewResult.append("Temp test: " + tempArr[0]);
            }
        });


    }



    public void openWeatherActivity(){

        EditText edit =  (EditText) findViewById(R.id.cityNameText);
        String city = edit.getText().toString();

        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra("cityName", city); //Pass temperatures for week to new activity
        //intent.putExtra("conditionsArray", weatherCondArr); //Pass weather conditions for week to new activity
        //intent.putExtra("errorMessage", errorMsg); //Pass error message from possible failed API call to new activity
        startActivity(intent);

    }




    /*private void getWeather(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);
        Call<WeatherRoot> call = weatherAPI.getWeather("Phoenix", APIkey, units);

        call.enqueue(new Callback<WeatherRoot>() {
            @Override
            public void onResponse(Call<WeatherRoot> call, Response<WeatherRoot> response) {

                if(!response.isSuccessful()){
                    errorMsg = "Code" + response.code();
                    return;
                }

                WeatherRoot weather = response.body();
                int tempSize = 0;
                int condSize = 0;
                for(int i = 0; i < weather.getHourly3().length; i++) {
                    if (weather.getHourly3()[i].getDateText().contains("00:00:00")) {
                        tempArr[tempSize] = weather.getHourly3()[i].getWeatherMain().getMax(); //Copy max temps for 7 days to tempArr array
                        tempSize++;
                    }
                    if (weather.getHourly3()[i].getDateText().contains("12:00:00")){
                        weatherCondArr[condSize] = weather.getHourly3()[i].getWeather()[0].getDescription(); //Copy weather conditions to weatherCond array
                        condSize++;
                    }
                }

            }

            @Override
            public void onFailure(Call<WeatherRoot> call, Throwable t) {
                errorMsg = t.getMessage();
            }

        });

    }*/


}