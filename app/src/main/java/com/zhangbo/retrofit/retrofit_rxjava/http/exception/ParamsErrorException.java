package com.zhangbo.retrofit.retrofit_rxjava.http.exception;

import com.zhangbo.retrofit.retrofit_rxjava.http.config.HTTPCode;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 14:43
 * desc   : 参数错误异常
 * version: 1.0
 */
public class ParamsErrorException extends BaseException {
    public ParamsErrorException() {
        super("参数错误", HTTPCode.CODE_PARAMS_ERROR);
    }
}
