package com.zhangbo.retrofit.retrofit_rxjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhangbo.retrofit.retrofit_rxjava.weather.activity.WeatherActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void queryWeather(View view){
        startActivity(new Intent(this, WeatherActivity.class));
    }
}
