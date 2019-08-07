package com.example.daye5.modles.home.modle;

import android.util.Log;

import com.example.daye5.api.ApiService;
import com.example.daye5.bean.BaseResponse;
import com.example.daye5.http.HttpManager;
import com.example.daye5.modles.home.bean.BannerData;
import com.example.daye5.modles.home.bean.HomeDataBean;
import com.example.daye5.modles.home.bean.HomeDatasBean;
import com.example.daye5.modles.home.contract.MainContract;
import com.example.daye5.utils.RxUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomeModle implements MainContract.HomeModle {


    @Override
    public void getData(final CallBack callBack, int pum) {
        HttpManager.getInstance().getServer(ApiService.class)
                .getArticleList(pum)
                .compose(RxUtils.<BaseResponse<HomeDataBean>>rxScheduleThread())
                .compose(RxUtils.<HomeDataBean>changeResult())
                .subscribe(new Observer<HomeDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("qiao", "onSubscribe: "+d);
                    }

                    @Override
                    public void onNext(HomeDataBean homeDataBean) {
                        List<HomeDatasBean> datas = homeDataBean.getDatas();
                        callBack.showArtSuccess(datas);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("qiao", "onError: "+e.getMessage());
                        callBack.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        HttpManager.getInstance().getServer(ApiService.class)
                .getBannerData()
                .compose(RxUtils.<BaseResponse<List<BannerData>>>rxScheduleThread())
                .compose(RxUtils.<List<BannerData>>changeResult())
                .subscribe(new Observer<List<BannerData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("qiao", "onSubscribe: "+d);
                    }

                    @Override
                    public void onNext(List<BannerData> bannerData) {
                        callBack.showBannSuccess(bannerData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("qiao", "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("qiao", "onComplete: ");
                    }
                });


    }
}
