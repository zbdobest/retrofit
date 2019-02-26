package com.zhangbo.retrofit.retrofit_rxjava;

import android.app.Application;
import android.content.Context;

import com.apkfuns.log2file.LogFileEngineFactory;
import com.apkfuns.logutils.LogUtils;

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
        LogUtils.getLogConfig()
                .configAllowLog(BuildConfig.DEBUG)
                .configTagPrefix("retrofit")
                .configShowBorders(true)
                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}");

        //# 支持写入日志到文件
        LogUtils.getLog2FileConfig().configLog2FileEnable(!BuildConfig.DEBUG)
                // targetSdkVersion >= 23 需要确保有写sdcard权限
                .configLog2FilePath("/sdcard/项目文件夹/logs/")
                .configLog2FileNameFormat("%d{yyyyMMdd}.txt")
                .configLogFileEngine(new LogFileEngineFactory(this));
    }


}
