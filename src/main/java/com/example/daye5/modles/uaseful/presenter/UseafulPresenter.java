package com.example.daye5.modles.uaseful.presenter;

import com.example.daye5.base.BasePresenter;
import com.example.daye5.modles.uaseful.bean.UsefulBean;
import com.example.daye5.modles.uaseful.contrat.UsefContract;
import com.example.daye5.modles.uaseful.modle.UsefulModle;

import java.util.List;

public class UseafulPresenter<V extends UsefContract.UsefView> extends BasePresenter<V> implements UsefContract.UsefPresenter, UsefContract.UsefModle.UsefCallBack {
    private UsefContract.UsefModle modle = new UsefulModle();

    @Override
    public void gethttp() {
        if (mView != null) {
            modle.getData(this);
        }
    }

    @Override
    public void showSuccess(List<UsefulBean> useful) {
        mView.showSuccess(useful);
    }

    @Override
    public void showError(String error) {
        mView.showError(error);
    }
}
