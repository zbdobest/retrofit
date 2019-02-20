package com.zhangbo.retrofit.retrofit_rxjava.http.retrofit;

import com.zhangbo.retrofit.retrofit_rxjava.http.bean.BaseResponseBody;
import com.zhangbo.retrofit.retrofit_rxjava.http.callback.RequestCallBack;
import com.zhangbo.retrofit.retrofit_rxjava.http.callback.RequestMultiplyCallBack;
import com.zhangbo.retrofit.retrofit_rxjava.http.subscriber.BaseSubscriber;
import com.zhangbo.retrofit.retrofit_rxjava.http.viewmodel.BaseViewModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 15:48
 * desc   :
 * version: 1.0
 */
public abstract class BaseRemoteDataSource {

    private CompositeDisposable compositeDisposable;

    private BaseViewModel mBaseViewModel;

    public BaseRemoteDataSource(BaseViewModel baseViewModel) {
        this.compositeDisposable = new CompositeDisposable();
        this.mBaseViewModel = baseViewModel;
    }

    protected <T> T getService(Class<T> clz) {
        return RetrofitManagement.getInstance().getService(clz);
    }

    protected <T> T getService(Class<T> clz, String host) {
        return RetrofitManagement.getInstance().getService(clz, host);
    }

    private <T> ObservableTransformer<BaseResponseBody<T>, T> applySchedulers() {
        return RetrofitManagement.getInstance().applySchedulers();
    }

    protected <T> void execute(Observable observable, RequestCallBack<T> callback) {
        execute(observable, new BaseSubscriber<>(mBaseViewModel, callback), true);
    }

    protected <T> void execute(Observable observable, RequestMultiplyCallBack<T> callback) {
        execute(observable, new BaseSubscriber<>(mBaseViewModel, callback), true);
    }

    public void executeWithoutDismiss(Observable observable, Observer observer) {
        execute(observable, observer, false);
    }

    private void execute(Observable observable, Observer observer, boolean isDismiss) {
        Disposable disposable = (Disposable) observable
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(applySchedulers())
                .compose(isDismiss ? loadingTransformer() : loadingTransformerWithoutDismiss())
                .subscribeWith(observer);
        addDisposable(disposable);
    }

    private void addDisposable(Disposable disposable){
        compositeDisposable.add(disposable);
    }

    public void dispose(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }

    private void startLoading(){
        if(mBaseViewModel != null){
            mBaseViewModel.startLoading();
        }
    }

    private void dismissLoading(){
        if(null != mBaseViewModel){
            mBaseViewModel.dismissLoading();
        }
    }

    private <T> ObservableTransformer<T, T> loadingTransformer() {
        return observable -> observable
                .subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> startLoading())
                .doFinally(this::dismissLoading);
    }

    private <T> ObservableTransformer<T, T> loadingTransformerWithoutDismiss() {
        return observable -> observable
                .subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> startLoading());
    }

}
