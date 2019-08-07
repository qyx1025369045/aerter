package com.example.daye5.modles.publics.modle;

import android.util.Log;

import com.example.daye5.api.ApiService;
import com.example.daye5.bean.BaseResponse;
import com.example.daye5.http.HttpManager;
import com.example.daye5.modles.publics.bean.PublicBean;
import com.example.daye5.modles.publics.contrat.PublicContract;
import com.example.daye5.utils.RxUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PublicModlers implements PublicContract.PublicModle {
    @Override
    public void getData(final PublicCallBack callBack) {
        HttpManager.getInstance().getServer(ApiService.class)
                .getWxChapterListData()
                .compose(RxUtils.<BaseResponse<List<PublicBean>>>rxScheduleThread())
                .compose(RxUtils.<List<PublicBean>>changeResult())
                .subscribe(new Observer<List<PublicBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("qiao", "onSubscribe: " + d);
                    }

                    @Override
                    public void onNext(List<PublicBean> publicBeans) {
                        callBack.showSuccess(publicBeans);
                        Log.e("qiao", "onNext: " + publicBeans);
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
