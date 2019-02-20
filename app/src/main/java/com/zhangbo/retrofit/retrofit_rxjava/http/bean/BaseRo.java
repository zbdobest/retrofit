package com.zhangbo.retrofit.retrofit_rxjava.http.bean;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 16:35
 * desc   :
 * version: 1.0
 */
public class BaseRo<T> {
    protected T remoteDataSource;

    public BaseRo(T remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }
}
