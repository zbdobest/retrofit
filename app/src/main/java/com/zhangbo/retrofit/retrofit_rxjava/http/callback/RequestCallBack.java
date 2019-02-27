package com.zhangbo.retrofit.retrofit_rxjava.http.callback;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 14:18
 * desc   : 自定义回调接口-请求成功
 * version: 1.0
 */
public interface RequestCallBack<T> {

    void onSuccess(T t);

    void onError(int code,String msg);
}
