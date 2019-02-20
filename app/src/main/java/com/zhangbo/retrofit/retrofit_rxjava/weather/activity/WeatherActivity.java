package com.zhangbo.retrofit.retrofit_rxjava.weather.activity;

import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhangbo.retrofit.retrofit_rxjava.R;
import com.zhangbo.retrofit.retrofit_rxjava.base.activity.BaseActivity;

import com.zhangbo.retrofit.retrofit_rxjava.weather.bean.Weather;
import com.zhangbo.retrofit.retrofit_rxjava.weather.viewmodel.WeatherViewModel;
import com.zhangbo.retrofit.retrofit_rxjava.weather.viewmodel.base.LViewModelProviders;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 17:23
 * desc   :
 * version: 1.0
 */
public class WeatherActivity  extends BaseActivity {
    private static final String TAG = "QueryWeatherActivity";

    private WeatherViewModel weatherViewModel;

    private EditText et_cityName;

    private TextView tv_weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_weather);
        et_cityName = findViewById(R.id.et_cityName);
        tv_weather = findViewById(R.id.tv_weather);
    }

    @Override
    protected ViewModel initViewModel() {
        weatherViewModel = LViewModelProviders.of(this, WeatherViewModel.class);
        weatherViewModel.getWeatherLiveData().observe(this, this::handlerWeather);
        return weatherViewModel;
    }

    private void handlerWeather(Weather weather) {
        StringBuilder result = new StringBuilder();
        for (Weather.InnerWeather.NearestWeather nearestWeather : weather.getData().getWeather()) {
            result.append("\n\n").append(new Gson().toJson(nearestWeather));
        }
        tv_weather.setText(result.toString());
    }

    public void queryWeather(View view) {
        tv_weather.setText(null);
        weatherViewModel.queryWeather(et_cityName.getText().toString());
    }

}
