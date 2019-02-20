package com.zhangbo.retrofit.retrofit_rxjava.http.exception;

import com.zhangbo.retrofit.retrofit_rxjava.http.config.HTTPCode;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 14:45
 * desc   : token失效
 * version: 1.0
 */
public class TokenInvalidException extends BaseException {

    public TokenInvalidException() {
        super("token失效", HTTPCode.CODE_TOKEN_INVALID);
    }
}
