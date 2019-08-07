package com.example.daye5.modles.know.model;

import android.util.Log;

import com.example.daye5.api.ApiService;
import com.example.daye5.bean.BaseResponse;
import com.example.daye5.http.HttpManager;
import com.example.daye5.modles.know.contrat.KnowleListContract;
import com.example.daye5.modles.project.bean.ProjectListDataBean;
import com.example.daye5.modles.project.bean.ProjectListDatasBean;
import com.example.daye5.utils.RxUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class KnowListModle implements KnowleListContract.KnowListModle {
    @Override
    public void getData(final KonwListCallBack callBack, int page, int cid) {
        HttpManager.getInstance().getServer(ApiService.class)
                .getKnowledgeListData(page, cid)
                .compose(RxUtils.<BaseResponse<ProjectListDataBean>>rxScheduleThread())
                .compose(RxUtils.<ProjectListDataBean>changeResult())
                .subscribe(new Observer<ProjectListDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("qiao", "onSubscribe: " + d);
                    }

                    @Override
                    public void onNext(ProjectListDataBean bean) {

                        callBack.showSuccess(bean);
                        Log.e("qiao", "onNext: " + bean);
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
