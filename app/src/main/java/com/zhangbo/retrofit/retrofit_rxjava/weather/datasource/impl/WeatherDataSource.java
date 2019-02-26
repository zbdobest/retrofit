package com.zhangbo.retrofit.retrofit_rxjava.weather.datasource.impl;

import com.zhangbo.retrofit.retrofit_rxjava.weather.bean.Weather;
import com.zhangbo.retrofit.retrofit_rxjava.api.ApiService;
import com.zhangbo.retrofit.retrofit_rxjava.http.callback.RequestCallBack;
import com.zhangbo.retrofit.retrofit_rxjava.http.retrofit.BaseRemoteDataSource;
import com.zhangbo.retrofit.retrofit_rxjava.http.viewmodel.BaseViewModel;
import com.zhangbo.retrofit.retrofit_rxjava.weather.datasource.inter.IWeatherDataSource;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 17:07
 * desc   :
 * version: 1.0
 */
public class WeatherDataSource extends BaseRemoteDataSource implements IWeatherDataSource {
    public WeatherDataSource(BaseViewModel mBaseViewModel) {
        super(mBaseViewModel);
    }

    @Override
    public void queryWeather(String cityname, RequestCallBack<Weather> callBack) {
        execute(getService(ApiService.class).queryWeather(cityname),callBack);
    }
}
