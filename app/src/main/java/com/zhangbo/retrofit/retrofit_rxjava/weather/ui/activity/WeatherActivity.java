package com.zhangbo.retrofit.retrofit_rxjava.weather.ui.activity;

import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.zhangbo.retrofit.retrofit_rxjava.R;
import com.zhangbo.retrofit.retrofit_rxjava.base.activity.BaseActivity;

import com.zhangbo.retrofit.retrofit_rxjava.imageloader.ImageLoadBaseTool;
import com.zhangbo.retrofit.retrofit_rxjava.imageloader.inter.ImageLoadProcessInterface;
import com.zhangbo.retrofit.retrofit_rxjava.weather.bean.Weather;
import com.zhangbo.retrofit.retrofit_rxjava.weather.viewmodel.WeatherViewModel;
import com.zhangbo.retrofit.retrofit_rxjava.base.viewmodelprovider.LViewModelProviders;

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

    private ImageView ivImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_weather);
        et_cityName = findViewById(R.id.et_cityName);
        tv_weather = findViewById(R.id.tv_weather);
        ivImageView = findViewById(R.id.iv_image);
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
        String path = "https://upload-images.jianshu.io/upload_images/5207488-9b7d8d755f83092b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1000/format/webp";

        ImageLoadBaseTool.display(this, ivImageView, path, new ImageLoadProcessInterface() {
            @Override
            public void onLoadStarted() {
                LogUtils.d("onLoadStarted");
            }

            @Override
            public void onResourceReady() {
                LogUtils.d("onResourceReady");
            }

            @Override
            public void onLoadCleared() {
                LogUtils.d("onLoadCleared");
            }

            @Override
            public void onLoadFailed() {
                LogUtils.d("onLoadFailed");
            }
        });
    }

}
