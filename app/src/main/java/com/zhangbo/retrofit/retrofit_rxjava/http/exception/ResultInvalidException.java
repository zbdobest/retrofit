package com.zhangbo.retrofit.retrofit_rxjava.http.exception;

import com.zhangbo.retrofit.retrofit_rxjava.http.config.HTTPCode;

/**
 * author : zhangbo
 * e-mail : zhangbo81@jd.com
 * date   : 2019/2/19 16:50
 * desc   :
 * version: 1.0
 */
public class ResultInvalidException extends BaseException {

    public ResultInvalidException() {
        super("非200", HTTPCode.CODE_UNKNOWN);
    }
}
