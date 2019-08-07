package com.example.daye5.modles.search.presenter;

import com.example.daye5.base.BasePresenter;
import com.example.daye5.modles.search.bean.SeachBean;
import com.example.daye5.modles.search.contrat.SeachContract;
import com.example.daye5.modles.search.modle.SeachModles;

import java.util.List;

public class SeachPresenter<V extends SeachContract.SeachView> extends BasePresenter<V> implements SeachContract.SeachPresenter, SeachContract.SeachModle.SeachCallBack {
    private SeachContract.SeachModle modle = new SeachModles();

    @Override
    public void regirSuccess(List<SeachBean> seachBeans) {
        mView.loginSuccess(seachBeans);
    }

    @Override
    public void regirFiler(String error) {
        mView.loginFiler(error);
    }

    @Override
    public void getseach() {
        if (mView != null) {
            modle.getRegir(this);
        }
    }
}
