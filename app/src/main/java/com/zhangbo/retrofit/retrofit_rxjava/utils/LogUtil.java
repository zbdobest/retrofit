package com.zhangbo.retrofit.retrofit_rxjava.utils;

import android.util.Log;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/26 11:01
 * desc   : 只有当LEVEL常量的值大于或等于对应日志级别值的时候，才会打印日志。
 * 开发阶段，将LEVEL赋值为VERBOSE，上线阶段将LEVEL赋值为NOTHING
 * version: 1.0
 */
public class LogUtil {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    /**控制想要打印的日志级别
     * 等于VERBOSE，则就会打印所有级别的日志
     * 等于WARN，则只会打印警告级别以上的日志
     * 等于NOTHING，则会屏蔽掉所有日志*/
    public static final int LEVEL = VERBOSE;


    public static void v(String tag, String msg){
        if(LEVEL <= VERBOSE){
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg){
        if(LEVEL <= DEBUG){
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg){
        if(LEVEL <= INFO){
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg){
        if(LEVEL <= WARN){
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg){
        if(LEVEL <= ERROR){
            Log.e(tag, msg);
        }
    }

}
