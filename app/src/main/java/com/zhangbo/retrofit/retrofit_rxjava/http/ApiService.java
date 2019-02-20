package com.zhangbo.retrofit.retrofit_rxjava.http;

import com.zhangbo.retrofit.retrofit_rxjava.weather.bean.Weather;
import com.zhangbo.retrofit.retrofit_rxjava.http.bean.BaseResponseBody;
import com.zhangbo.retrofit.retrofit_rxjava.http.config.HttpConfig;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 17:02
 * desc   :
 * version: 1.0
 */
public interface ApiService {

    @Headers({HttpConfig.HTTP_REQUEST_TYPE_KEY + ":"+HttpConfig.HTTP_REQUEST_WEATHER})
    @GET("onebox/weather/query")
    Observable<BaseResponseBody<Weather>> queryWeather(@Query("cityname")String cityname);
}
