package com.example.weathermobileapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private Button getWeatherBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWeatherBtn = findViewById(R.id.getWeatherBtn);
        getWeatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWeatherActivity();
            }
        });

    }

    public void openWeatherActivity(){

        EditText edit =  (EditText) findViewById(R.id.cityNameText);
        String city = edit.getText().toString();

        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra("cityName", city); //Pass temperatures for week to new activity
        startActivity(intent);

    }

}