package com.zhangbo.retrofit.retrofit_rxjava.imageloader;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.zhangbo.retrofit.retrofit_rxjava.imageloader.config.ImageConfig;
import com.zhangbo.retrofit.retrofit_rxjava.imageloader.glide.ImageLoadByGlide;
import com.zhangbo.retrofit.retrofit_rxjava.imageloader.inter.ImageLoadInterface;
import com.zhangbo.retrofit.retrofit_rxjava.imageloader.inter.ImageLoadProcessInterface;
import com.zhangbo.retrofit.retrofit_rxjava.imageloader.picasso.ImageLoadByPicasso;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/26 10:37
 * desc   : 图片加载基础的工具类
 * version: 1.0
 */
public class ImageLoadBaseTool {
    private static final String TAG = "ImageTool";
    private static ImageLoadInterface imageLoad = null;

    static {
//        imageLoad = new ImageLoadByGlide();//glide
        imageLoad = new ImageLoadByPicasso();//picasso
    }

    /**
     * imageView中加载项目内资源
     *
     * @param mContext
     * @param view
     * @param resId
     */
    public static void display(Context mContext, final ImageView view, @DrawableRes int resId) {
        display(mContext, view, null, resId);

    }

    /**
     * 加载网络图片/本地图片
     *
     * @param mContext
     * @param view
     * @param url
     */
    public static void display(Context mContext, ImageView view, String url) {

        display(mContext, view, url,-1);
    }

    /**
     * 加载图片
     *
     * @param mContext     上下文
     * @param view         imageview
     * @param url          图片地址
     * @param defaultImage 默认显示内容
     */
    public static void display(Context mContext, ImageView view, String url, int defaultImage) {
        display(mContext, view, url, defaultImage, null);
    }

    /**
     * @param mContext
     * @param view
     * @param url
     * @param imageLoadProcessInterface
     */
    public static void display(Context mContext, ImageView view, String url, ImageLoadProcessInterface imageLoadProcessInterface) {
        display(mContext, view, url, -1, imageLoadProcessInterface);
    }

    /**
     * @param mContext                  上下文
     * @param view                      imageview
     * @param url                       地址
     * @param defaultImage              默认图片
     * @param imageLoadProcessInterface 监听
     */
    public static void display(Context mContext, ImageView view, String url, int defaultImage, ImageLoadProcessInterface imageLoadProcessInterface) {
        display(mContext, view, url, defaultImage, -1, imageLoadProcessInterface);
    }

    public static void display(Context mContext, ImageView view, String url, int defaultImage, int failImage, ImageLoadProcessInterface imageLoadProcessInterface) {
        display(mContext, view, url, new ImageConfig(defaultImage, failImage, 0), imageLoadProcessInterface);
    }

    public static void display(Context mContext, ImageView view, String url, ImageConfig config, ImageLoadProcessInterface imageLoadProcessInterface) {
        displayUrl(mContext, view, url, config, imageLoadProcessInterface);
    }

    /**
     * glide加载图片
     *
     * @param imageView view
     * @param url       url
     */
    private static void displayUrl(Context mContext, final ImageView imageView, final String url, final ImageConfig config, final ImageLoadProcessInterface imageLoadProcessInterface) {

        try {
            imageLoad.display(mContext, imageView, url, config, imageLoadProcessInterface);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 恢复加载图片
     *
     * @param context
     */
    public static void resumeLoad(Context context, String url) {
        if (imageLoad != null) {
            imageLoad.resumeLoad(context, url);
        }
    }

    /**
     * 清除一个资源的加载
     *
     * @param context
     */
    public static void clearImageView(Context context, ImageView imageView, String url) {
        if (imageLoad != null) {
            imageLoad.clearImageView(context, imageView, url);
        }
    }

    /**
     * 暂停加载图片
     *
     * @param context
     */
    public static void pauseLoad(Context context, String url) {
        if (imageLoad != null) {
            imageLoad.pauseLoad(context, url);
        }
    }
}
