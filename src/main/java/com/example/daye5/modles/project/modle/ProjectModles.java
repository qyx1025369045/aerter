package com.example.daye5.modles.project.modle;

import android.util.Log;

import com.example.daye5.api.ApiService;
import com.example.daye5.bean.BaseResponse;
import com.example.daye5.http.HttpManager;
import com.example.daye5.modles.navgi.bean.NavightBean;
import com.example.daye5.modles.project.bean.ProjectBean;
import com.example.daye5.modles.project.contrat.ProjectContract;
import com.example.daye5.utils.RxUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ProjectModles implements ProjectContract.ProjectModle {
    @Override
    public void getData(final CallBack callBack) {
        HttpManager.getInstance().getServer(ApiService.class)
                .getProjectTreeData()
                .compose(RxUtils.<BaseResponse<List<ProjectBean>>>rxScheduleThread())
                .compose(RxUtils.<List<ProjectBean>>changeResult())
                .subscribe(new Observer<List<ProjectBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("qiao", "onSubscribe: " + d);
                    }

                    @Override
                    public void onNext(List<ProjectBean> navightBeans) {
                        callBack.showSuccess(navightBeans);
                        Log.e("qiao", "onNext: " + navightBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("qiao", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("qiao", "onComplete: " );
                    }
                });
    }
}
