package com.zhangbo.retrofit.retrofit_rxjava.http.exception;

import com.zhangbo.retrofit.retrofit_rxjava.http.config.HTTPCode;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 14:22
 * desc   :
 * version: 1.0
 */
public class BaseException extends RuntimeException{

    private int errorCode = HTTPCode.CODE_UNKNOWN;

    public BaseException() {
    }

    public BaseException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
