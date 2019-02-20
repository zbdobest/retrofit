package com.zhangbo.retrofit.retrofit_rxjava.http.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.zhangbo.retrofit.retrofit_rxjava.http.event.BaseActionEvent;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 14:06
 * desc   :
 * version: 1.0
 */
public interface IViewModelAction {

    void startLoading();

    void startLoading(String message);

    void dismissLoading();

    void showToast(String message);

    void finish();

    void finishWithResultOk();

    MutableLiveData<BaseActionEvent> getActionLiveData();
}
