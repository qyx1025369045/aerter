package com.example.daye5.modles.publics.modle;

import android.util.Log;

import com.example.daye5.api.ApiService;
import com.example.daye5.bean.BaseResponse;
import com.example.daye5.http.HttpManager;
import com.example.daye5.modles.project.bean.ProjectListDataBean;
import com.example.daye5.modles.publics.contrat.PublicContract;
import com.example.daye5.modles.publics.contrat.PublicListContract;
import com.example.daye5.utils.RxUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PublicListModlers implements PublicListContract.PublicListModle {

    @Override
    public void getData(final PubliCallBack callBack, final int id, final int pag) {
        HttpManager.getInstance().getServer(ApiService.class)
                .getWxArticlesData(id,pag)
                .compose(RxUtils.<BaseResponse<ProjectListDataBean>>rxScheduleThread())
                .compose(RxUtils.<ProjectListDataBean>changeResult())
                .subscribe(new Observer<ProjectListDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("qiao", "onSubscribe: " + d);
                    }

                    @Override
                    public void onNext(ProjectListDataBean listDataBean) {
                        callBack.showSuccess(listDataBean,id,pag);
                        Log.e("qiao", "onNext: " + listDataBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.showError(e.getMessage());
                        Log.e("qiao", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("qiao", "onComplete: " );
                    }
                });
    }
}
