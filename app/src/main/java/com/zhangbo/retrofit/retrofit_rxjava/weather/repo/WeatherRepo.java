package com.zhangbo.retrofit.retrofit_rxjava.weather.repo;

import android.arch.lifecycle.MutableLiveData;

import com.apkfuns.logutils.LogUtils;
import com.zhangbo.retrofit.retrofit_rxjava.http.bean.BaseRo;
import com.zhangbo.retrofit.retrofit_rxjava.http.callback.RequestCallBack;
import com.zhangbo.retrofit.retrofit_rxjava.http.callback.RequestMultiplyCallBack;
import com.zhangbo.retrofit.retrofit_rxjava.http.exception.BaseException;
import com.zhangbo.retrofit.retrofit_rxjava.weather.bean.Weather;
import com.zhangbo.retrofit.retrofit_rxjava.weather.datasource.inter.IWeatherDataSource;


/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 17:19
 * desc   :
 * version: 1.0
 */
public class WeatherRepo extends BaseRo<IWeatherDataSource> {
    public WeatherRepo(IWeatherDataSource remoteDataSource) {
        super(remoteDataSource);
    }

    public MutableLiveData<Weather> queryWeather(String cityName) {
        MutableLiveData<Weather> weatherMutableLiveData = new MutableLiveData<>();
        remoteDataSource.queryWeather(cityName, new RequestCallBack<Weather>() {
            @Override
            public void onSuccess(Weather weather) {
                weatherMutableLiveData.setValue(weather);
            }

            @Override
            public void onError(int code, String msg) {
                LogUtils.e("errorCode"+code+"----"+"errorMsg"+msg);
            }
        });
        return weatherMutableLiveData;
    }

}
