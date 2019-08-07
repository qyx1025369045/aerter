package com.example.daye5.modles.login.modle;

import android.util.Log;

import com.example.daye5.api.ApiService;
import com.example.daye5.bean.BaseResponse;
import com.example.daye5.http.HttpManager;
import com.example.daye5.modles.login.bean.LoginData;
import com.example.daye5.modles.login.contrat.LoginContract;
import com.example.daye5.utils.RxUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginModles implements LoginContract.LoginModle {

    @Override
    public void getlogin(final LogCallBack callBack, String username, String password) {
        HttpManager.getInstance().getServer(ApiService.class)
                .login(username, password)
                .compose(RxUtils.<BaseResponse<LoginData>>rxScheduleThread())
                .compose(RxUtils.<LoginData>changeResult())
                .subscribe(new Observer<LoginData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("qiao", "onSubscribe: " + d);
                    }

                    @Override
                    public void onNext(LoginData loginData) {
                        callBack.loginSuccess(loginData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("qiao", "onError: " + e.getMessage());
                        callBack.loginFiler(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
