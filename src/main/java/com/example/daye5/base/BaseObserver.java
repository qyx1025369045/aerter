package com.example.daye5.base;

import com.example.daye5.http.ApiException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class BaseObserver<T> implements Observer<T> {

    //管理每次网络请求
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onSubscribe(Disposable d) {
        compositeDisposable.add(d);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    protected abstract <T> void onSuccess(T t);

    @Override
    public void onError(Throwable e) {
        //网络请求成功
        if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            int errorCode = apiException.getErrorCode();
            switch (errorCode) {
                case 1:
                    break;
            }
            error(apiException.getErrorCode());
        } else if (e instanceof HttpException) {
            error(e.getMessage());
        }

        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    protected abstract <T> void error(T error);

    @Override
    public void onComplete() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();

        }
    }
}