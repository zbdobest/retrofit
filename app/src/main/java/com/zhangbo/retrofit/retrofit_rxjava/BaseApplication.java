package com.zhangbo.retrofit.retrofit_rxjava;

import android.app.Application;
import android.content.Context;

/**
 * author : zhangbo
 * e-mail : zhangbo81@jd.com
 * date   : 2019/2/19 16:44
 * desc   :
 * version: 1.0
 */
public class BaseApplication extends Application {

    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }


}
