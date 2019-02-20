package com.zhangbo.retrofit.retrofit_rxjava.http.exception;

import com.zhangbo.retrofit.retrofit_rxjava.http.config.HTTPCode;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 16:48
 * desc   :
 * version: 1.0
 */
public class ConnectionException extends BaseException{

    public ConnectionException() {
        super("网络异常", HTTPCode.CODE_CONNECTION_FAILD);
    }
}
