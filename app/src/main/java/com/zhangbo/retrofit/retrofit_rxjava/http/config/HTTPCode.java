package com.zhangbo.retrofit.retrofit_rxjava.http.config;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 14:35
 * desc   : 网络请求code码
 * version: 1.0
 */
public class HTTPCode {

    //请求成功
    public static final int CODE_SUCCESS = 0;
    //未知错误
    public static final int CODE_UNKNOWN = 999;
    //参数错误
    public static final int CODE_PARAMS_ERROR = -1;
    //token失效
    public static final int CODE_TOKEN_INVALID = -2;
    //连接失败
    public static final int CODE_CONNECTION_FAILD = -3;
    //禁止访问
    public static final int CODE_FORBIDDEN = -4;
}
