package com.example.daye5.modles.home.presenter;

import com.example.daye5.base.BasePresenter;
import com.example.daye5.modles.home.bean.BannerData;
import com.example.daye5.modles.home.bean.HomeDatasBean;
import com.example.daye5.modles.home.contract.MainContract;
import com.example.daye5.modles.home.modle.HomeModle;

import java.util.List;

public class HomePresenter<V extends MainContract.HomeView> extends BasePresenter<V> implements MainContract.HomePresenter, MainContract.HomeModle.CallBack {
    private MainContract.HomeModle modle = new HomeModle();

    @Override
    public void gethttp(int pum) {
        if (mView != null) {
            modle.getData(this, pum);
        }
    }


    @Override
    public void showArtSuccess(List<HomeDatasBean> list) {
        mView.showArtSuccess(list);
    }

    @Override
    public void showBannSuccess(List<BannerData> bannerData) {
        mView.showBannSuccess(bannerData);
    }

    @Override
    public void showError(String error) {
        mView.showError(error);
    }
}
