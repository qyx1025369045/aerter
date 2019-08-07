package com.example.daye5.modles.navgi.presenter;

import com.example.daye5.base.BasePresenter;
import com.example.daye5.modles.navgi.bean.NavightBean;
import com.example.daye5.modles.navgi.contrat.NavgerContract;
import com.example.daye5.modles.navgi.model.NavgerModles;

import java.util.List;

public class NavgerPresenters<V extends NavgerContract.NavgerView> extends BasePresenter<V> implements NavgerContract.NavgerPresenter, NavgerContract.NavgerModle.CallBack {
    private NavgerContract.NavgerModle modle = new NavgerModles();

    @Override
    public void gethttp() {
        if (mView != null) {
            modle.getData(this);
        }
    }

    @Override
    public void showSuccess(List<NavightBean> navlist) {
        mView.showSuccess(navlist);
    }

    @Override
    public void showError(String error) {
        mView.showError(error);
    }
}
