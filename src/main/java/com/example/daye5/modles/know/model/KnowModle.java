package com.example.daye5.modles.know.model;

import android.util.Log;

import com.example.daye5.api.ApiService;
import com.example.daye5.bean.BaseResponse;
import com.example.daye5.http.HttpManager;
import com.example.daye5.modles.know.bean.KnowDataBean;
import com.example.daye5.modles.know.contrat.KnowleContract;
import com.example.daye5.utils.RxUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class KnowModle implements KnowleContract.KnowModle {
    @Override
    public void getData(final CallBack callBack) {
        HttpManager.getInstance().getServer(ApiService.class)
                .getKnowledgeTreeData()
                .compose(RxUtils.<BaseResponse<List<KnowDataBean>>>rxScheduleThread())
                .compose(RxUtils.<List<KnowDataBean>>changeResult())
                .subscribe(new Observer<List<KnowDataBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("qiao", "onSubscribe: " + d);
                    }

                    @Override
                    public void onNext(List<KnowDataBean> knowDataBeans) {
                        callBack.showSuccess(knowDataBeans);
                        Log.e("qiao", "onNext: " + knowDataBeans);
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
