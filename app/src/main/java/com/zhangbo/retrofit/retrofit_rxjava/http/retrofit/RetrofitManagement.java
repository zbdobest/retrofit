package com.zhangbo.retrofit.retrofit_rxjava.http.retrofit;

import com.readystatesoftware.chuck.ChuckInterceptor;
import com.zhangbo.retrofit.retrofit_rxjava.BaseApplication;
import com.zhangbo.retrofit.retrofit_rxjava.BuildConfig;
import com.zhangbo.retrofit.retrofit_rxjava.http.bean.BaseResponseBody;
import com.zhangbo.retrofit.retrofit_rxjava.http.config.HTTPCode;
import com.zhangbo.retrofit.retrofit_rxjava.http.config.HttpConfig;
import com.zhangbo.retrofit.retrofit_rxjava.http.exception.ParamsErrorException;
import com.zhangbo.retrofit.retrofit_rxjava.http.exception.ServerResultException;
import com.zhangbo.retrofit.retrofit_rxjava.http.exception.TokenInvalidException;
import com.zhangbo.retrofit.retrofit_rxjava.http.interceptor.FilterInterceptor;
import com.zhangbo.retrofit.retrofit_rxjava.http.interceptor.HeaderInterceptor;
import com.zhangbo.retrofit.retrofit_rxjava.http.interceptor.HttpInterceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 14:47
 * desc   : retrofit管理类
 * version: 1.0
 */
public class RetrofitManagement {
    //超时时间设置
    private final int READ_TIME_OUT = 15000;
    private final int WRITE_TIME_OUT = 15000;
    private final int CONNECT_TIME_OUT = 15000;

    private final Map<String,Object> serviceMap = new ConcurrentHashMap<>();

    public RetrofitManagement() {
    }

    public static RetrofitManagement getInstance(){
        return RetrofitHolder.retrofitManagement;
    }

    public static class RetrofitHolder {
        private static final RetrofitManagement retrofitManagement = new RetrofitManagement();
    }

    private Retrofit createRetrofit(String url) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new HttpInterceptor())
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new FilterInterceptor())
                .retryOnConnectionFailure(true);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
            builder.addInterceptor(new ChuckInterceptor(BaseApplication.context));
        }
        OkHttpClient okHttpClient = builder.build();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    <T> ObservableTransformer<BaseResponseBody<T>,T> applySchedulers(){
        return observable -> observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(result ->{
                    switch (result.getCode()) {
                        case HTTPCode.CODE_SUCCESS:
                            return createData(result.getData());
                        case HTTPCode.CODE_TOKEN_INVALID:
                            throw  new TokenInvalidException();
                        case HTTPCode.CODE_PARAMS_ERROR:
                            throw new ParamsErrorException();
                        default:
                            return observable.create(new ObservableOnSubscribe<T>() {
                                @Override
                                public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                                    emitter.onError(new ServerResultException(result.getMsg(),result.getCode()));
                                }
                            });
                    }
                });
    }


    private <T> Observable<T> createData(T t){
        return Observable.create(new ObservableOnSubscribe<T>(){
            @Override
            public void subscribe(ObservableEmitter<T> emitter) {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                }catch (Exception e){
                    emitter.onError(e);
                }
            }
        });
    }

    <T> T getService(Class<T> clz){
        return getService(clz, HttpConfig.BASE_URL_WEATHER);
    }

    <T> T getService(Class<T> clz,String host){
        T value;
        if(serviceMap.containsKey(host)){
            Object obj = serviceMap.get(host);
            if(null == obj){
                value = createRetrofit(host).create(clz);
                serviceMap.put(host,value);
            }else {
                value = (T) obj;
            }
        }else {
            value = createRetrofit(host).create(clz);
            serviceMap.put(host,value);
        }
        return value;
    }
}
