package com.example.daye5.modles.search.modle;

import android.util.Log;

import com.example.daye5.api.ApiService;
import com.example.daye5.bean.BaseResponse;
import com.example.daye5.http.HttpManager;
import com.example.daye5.modles.search.bean.SeachBean;
import com.example.daye5.modles.search.contrat.SeachContract;
import com.example.daye5.utils.RxUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SeachModles implements SeachContract.SeachModle {
    @Override
    public void getRegir(final SeachCallBack callBack) {
        HttpManager.getInstance().getServer(ApiService.class)
                .getTopSearchData()
                .compose(RxUtils.<BaseResponse<List<SeachBean>>>rxScheduleThread())
                .compose(RxUtils.<List<SeachBean>>changeResult())
                .subscribe(new Observer<List<SeachBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("qiao", "onSubscribe: " + d);
                    }

                    @Override
                    public void onNext(List<SeachBean> seachBeans) {
                        callBack.regirSuccess(seachBeans);
                        Log.e("qiao", "onNext: " + seachBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("qiao", "onError: " + e.getMessage());
                        callBack.regirFiler(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
