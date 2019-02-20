package com.zhangbo.retrofit.retrofit_rxjava.http.exception;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 15:13
 * desc   :
 * version: 1.0
 */
public class ServerResultException extends BaseException{

    public ServerResultException(String message, int errorCode) {
        super(message, errorCode);
    }
}
