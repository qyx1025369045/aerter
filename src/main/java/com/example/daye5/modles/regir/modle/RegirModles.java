package com.example.daye5.modles.regir.modle;

import android.util.Log;

import com.example.daye5.api.ApiService;
import com.example.daye5.bean.BaseResponse;
import com.example.daye5.http.HttpManager;
import com.example.daye5.modles.login.bean.LoginData;
import com.example.daye5.modles.regir.contrat.RegirContract;
import com.example.daye5.utils.RxUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RegirModles implements RegirContract.RegirModle {
    private static final String TAG = "RegirModles";
    @Override
    public void getRegir(final RegCallBack callBack, String username, String password, String repassword) {
        HttpManager.getInstance().getServer(ApiService.class)
                .register(username, password, repassword)
                .compose(RxUtils.<BaseResponse<LoginData>>rxScheduleThread())
                .compose(RxUtils.<LoginData>changeResult())
                .subscribe(new Observer<LoginData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginData loginData) {
                        callBack.regirSuccess(loginData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                        callBack.regirFiler(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
