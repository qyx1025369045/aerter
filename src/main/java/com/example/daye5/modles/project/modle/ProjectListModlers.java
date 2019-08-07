package com.example.daye5.modles.project.modle;

import android.util.Log;

import com.example.daye5.api.ApiService;
import com.example.daye5.bean.BaseResponse;
import com.example.daye5.http.HttpManager;
import com.example.daye5.modles.project.bean.ProjectListDataBean;
import com.example.daye5.modles.project.bean.ProjectListDatasBean;
import com.example.daye5.modles.project.contrat.ProjectListContract;
import com.example.daye5.utils.RxUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ProjectListModlers implements ProjectListContract.ProjectListModle {

    private static final String TAG = "ProjectListModlers";

    @Override
    public void getData(final ProCallBack callBack, final int pag, final int cid) {
        HttpManager.getInstance().getServer(ApiService.class)
                .getProjectListData(pag, cid)
                .compose(RxUtils.<BaseResponse<ProjectListDataBean>>rxScheduleThread())
                .compose(RxUtils.<ProjectListDataBean>changeResult())
                .subscribe(new Observer<ProjectListDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " + d);
                    }

                    @Override
                    public void onNext(ProjectListDataBean listDataBean) {
                        callBack.showSuccess(listDataBean, pag, cid);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.showError(e.getMessage());
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
