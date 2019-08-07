package com.example.daye5.modles.uaseful.modle;

import android.util.Log;

import com.example.daye5.api.ApiService;
import com.example.daye5.bean.BaseResponse;
import com.example.daye5.http.HttpManager;
import com.example.daye5.modles.uaseful.bean.UsefulBean;
import com.example.daye5.modles.uaseful.contrat.UsefContract;
import com.example.daye5.utils.RxUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UsefulModle implements UsefContract.UsefModle {
    @Override
    public void getData(final UsefCallBack callBack) {
        HttpManager.getInstance().getServer(ApiService.class)
                .getUsefulSites()
                .compose(RxUtils.<BaseResponse<List<UsefulBean>>>rxScheduleThread())
                .compose(RxUtils.<List<UsefulBean>>changeResult())
                .subscribe(new Observer<List<UsefulBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("qiao", "onSubscribe: " + d);
                    }

                    @Override
                    public void onNext(List<UsefulBean> usefulBeans) {
                        callBack.showSuccess(usefulBeans);
                        Log.e("qiao", "onNext: " + usefulBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("qiao", "onError: " + e.getMessage());
                        callBack.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
