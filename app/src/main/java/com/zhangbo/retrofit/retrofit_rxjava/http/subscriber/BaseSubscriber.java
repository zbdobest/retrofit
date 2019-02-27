package com.zhangbo.retrofit.retrofit_rxjava.http.subscriber;

import android.widget.Toast;

import com.zhangbo.retrofit.retrofit_rxjava.BaseApplication;
import com.zhangbo.retrofit.retrofit_rxjava.http.callback.RequestCallBack;
import com.zhangbo.retrofit.retrofit_rxjava.http.callback.RequestMultiplyCallBack;
import com.zhangbo.retrofit.retrofit_rxjava.http.config.HTTPCode;
import com.zhangbo.retrofit.retrofit_rxjava.http.exception.BaseException;
import com.zhangbo.retrofit.retrofit_rxjava.http.exception.ServerResultException;
import com.zhangbo.retrofit.retrofit_rxjava.http.viewmodel.BaseViewModel;

import io.reactivex.observers.DisposableObserver;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 15:42
 * desc   :
 * version: 1.0
 */
public class BaseSubscriber<T> extends DisposableObserver<T> {

    private BaseViewModel mBaseViewModel;
    private RequestCallBack<T> mRequestCallBack;

    public BaseSubscriber(BaseViewModel mBaseViewModel) {
        this.mBaseViewModel = mBaseViewModel;
    }

    public BaseSubscriber(BaseViewModel mBaseViewModel, RequestCallBack<T> mRequestCallBack) {
        this.mBaseViewModel = mBaseViewModel;
        this.mRequestCallBack = mRequestCallBack;
    }



    @Override
    public void onNext(T t) {
        if(mRequestCallBack != null){
            mRequestCallBack.onSuccess(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if(e instanceof ServerResultException){
            if(mRequestCallBack != null){
                ServerResultException serverResultException = (ServerResultException) e;
                mRequestCallBack.onError(serverResultException.getErrorCode(),serverResultException.getMsg());
            }
        }else if(mRequestCallBack instanceof RequestMultiplyCallBack){
            RequestMultiplyCallBack requestMultiplyCallBack = (RequestMultiplyCallBack) mRequestCallBack;
            if(e instanceof BaseException){
                requestMultiplyCallBack.onFail((BaseException) e);
            }else {
                requestMultiplyCallBack.onFail(new BaseException(e.getMessage(),HTTPCode.CODE_UNKNOWN));
            }
        }else {
            if(mBaseViewModel == null){
                Toast.makeText(BaseApplication.context,e.getMessage(),Toast.LENGTH_SHORT).show();
            }else {
                mBaseViewModel.showToast(e.getMessage());
            }
        }

    }

    @Override
    public void onComplete() {

    }
}
