package com.zhangbo.retrofit.retrofit_rxjava.weather.datasource.inter;


import com.zhangbo.retrofit.retrofit_rxjava.http.callback.RequestCallBack;
import com.zhangbo.retrofit.retrofit_rxjava.weather.bean.Weather;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 17:08
 * desc   :
 * version: 1.0
 */
public interface IWeatherDataSource {

    void queryWeather(String cityname, RequestCallBack<Weather> callBack);
}
