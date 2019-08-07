package com.example.daye5.modles.navgi.model;

import android.util.Log;

import com.example.daye5.api.ApiService;
import com.example.daye5.bean.BaseResponse;
import com.example.daye5.http.HttpManager;
import com.example.daye5.modles.navgi.bean.NavightBean;
import com.example.daye5.modles.navgi.contrat.NavgerContract;
import com.example.daye5.utils.RxUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class NavgerModles implements NavgerContract.NavgerModle {
    @Override
    public void getData(final CallBack callBack) {
        HttpManager.getInstance().getServer(ApiService.class)
                .getNavigationListData()
                .compose(RxUtils.<BaseResponse<List<NavightBean>>>rxScheduleThread())
                .compose(RxUtils.<List<NavightBean>>changeResult())
                .subscribe(new Observer<List<NavightBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("qiao", "onSubscribe: " + d);
                    }

                    @Override
                    public void onNext(List<NavightBean> navightBeans) {
                        callBack.showSuccess(navightBeans);
                        Log.e("qiao", "onNext: " + navightBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.showError(e.getMessage());
                        Log.e("qiao", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
