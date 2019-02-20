package com.zhangbo.retrofit.retrofit_rxjava.http.viewmodel;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.zhangbo.retrofit.retrofit_rxjava.http.event.BaseActionEvent;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 14:09
 * desc   : 消息发送方的 BaseViewModel
 * version: 1.0
 */
public class BaseViewModel extends ViewModel implements IViewModelAction {

    private MutableLiveData<BaseActionEvent> mActionLiveData;

    protected LifecycleOwner mLifecycleOwner;

    public BaseViewModel() {
        mActionLiveData = new MutableLiveData<>();
    }

    @Override
    public void startLoading() {
        startLoading(null);
    }

    @Override
    public void startLoading(String message) {
        BaseActionEvent baseActionEvent = new BaseActionEvent(BaseActionEvent.SHOW_LOADING_DIALOG);
        baseActionEvent.setMessage(message);
        mActionLiveData.setValue(baseActionEvent);
    }

    @Override
    public void dismissLoading() {
        mActionLiveData.setValue(new BaseActionEvent(BaseActionEvent.DISMISS_LOADING_DIALOG));
    }

    @Override
    public void showToast(String message) {
        BaseActionEvent baseActionEvent = new BaseActionEvent(BaseActionEvent.SHOW_TOAST);
        baseActionEvent.setMessage(message);
        mActionLiveData.setValue(baseActionEvent);
    }

    @Override
    public void finish() {
        mActionLiveData.setValue(new BaseActionEvent(BaseActionEvent.FINISH));
    }

    @Override
    public void finishWithResultOk() {
        mActionLiveData.setValue(new BaseActionEvent(BaseActionEvent.FINISH_WITH_RESULT_OK));
    }

    @Override
    public MutableLiveData<BaseActionEvent> getActionLiveData() {
        return mActionLiveData;
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner){
        this.mLifecycleOwner = lifecycleOwner;
    }
}
