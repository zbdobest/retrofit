package com.zhangbo.retrofit.retrofit_rxjava.http.callback;

import com.zhangbo.retrofit.retrofit_rxjava.http.exception.BaseException;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 14:21
 * desc   :
 * version: 1.0
 */
public interface RequestMultiplyCallBack<T> extends RequestCallBack<T> {

    void onFail(BaseException exception);

}
