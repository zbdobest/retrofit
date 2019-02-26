package com.zhangbo.retrofit.retrofit_rxjava.imageloader.inter;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/26 10:40
 * desc   : * 图片加载过程的回调
 * 回调监听这些方法不一定所有的库都有
 * version: 1.0
 */
public interface ImageLoadProcessInterface {
    /**
     * 开始加载
     */
    void onLoadStarted();

    /**
     * 资源准备妥当
     */
    void onResourceReady();

    /**
     * 资源已经释放
     */
    void onLoadCleared();

    /**
     * 资源加载失败
     */
    void onLoadFailed();
}
