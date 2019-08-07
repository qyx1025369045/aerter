package com.example.daye5.modles.collection.modle;

import android.util.Log;

import com.example.daye5.api.ApiService;
import com.example.daye5.bean.BaseResponse;
import com.example.daye5.http.HttpManager;
import com.example.daye5.modles.collection.bean.ColleBean;
import com.example.daye5.modles.collection.contract.CollectContract;
import com.example.daye5.utils.RxUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CollectModle implements CollectContract.CollectModle {
    @Override
    public void getData(final CollectCallBack callBack, int pum) {
        HttpManager.getInstance().getServer(ApiService.class)
                .getCollectList(pum)
                .compose(RxUtils.<BaseResponse<ColleBean>>rxScheduleThread())
                .compose(RxUtils.<ColleBean>changeResult())
                .subscribe(new Observer<ColleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("qiao", "onSubscribe: " + d);
                    }

                    @Override
                    public void onNext(ColleBean colleBean) {
                        callBack.showArtSuccess(colleBean.getDatas());
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
