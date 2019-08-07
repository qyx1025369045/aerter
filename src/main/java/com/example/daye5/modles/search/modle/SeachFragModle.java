package com.example.daye5.modles.search.modle;

import android.util.Log;

import com.example.daye5.api.ApiService;
import com.example.daye5.bean.BaseResponse;
import com.example.daye5.http.HttpManager;
import com.example.daye5.modles.home.bean.HomeDataBean;
import com.example.daye5.modles.search.contrat.SeachFragContract;
import com.example.daye5.utils.RxUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SeachFragModle implements SeachFragContract.SeachFragModle {
    @Override
    public void getRegir(final SeachFragCallBack callBack, int page, String k) {
        HttpManager.getInstance().getServer(ApiService.class)
                .getSearchResultList(page,k)
                .compose(RxUtils.<BaseResponse<HomeDataBean>>rxScheduleThread())
                .compose(RxUtils.<HomeDataBean>changeResult())
                .subscribe(new Observer<HomeDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("qiao", "onSubscribe: " + d);
                    }

                    @Override
                    public void onNext(HomeDataBean bean) {
                        callBack.regirSuccess(bean.getDatas());
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.regirFiler(e.getMessage());
                        Log.e("qiao", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
