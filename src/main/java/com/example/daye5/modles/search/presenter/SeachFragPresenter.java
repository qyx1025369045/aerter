package com.example.daye5.modles.search.presenter;

import com.example.daye5.base.BasePresenter;
import com.example.daye5.modles.home.bean.HomeDatasBean;
import com.example.daye5.modles.search.contrat.SeachFragContract;
import com.example.daye5.modles.search.modle.SeachFragModle;

import java.util.List;

public class SeachFragPresenter<V extends SeachFragContract.SeachFragView> extends BasePresenter<V> implements SeachFragContract.SeachFragPresenter,SeachFragContract.SeachFragModle.SeachFragCallBack {
    private SeachFragContract.SeachFragModle modle = new SeachFragModle();

    @Override
    public void getseach(int page, String k) {
        if(mView != null){
            modle.getRegir(this,page,k);
        }
    }

    @Override
    public void regirSuccess(List<HomeDatasBean> seachBeans) {
        mView.loginSuccess(seachBeans);
    }

    @Override
    public void regirFiler(String error) {
        mView.loginFiler(error);
    }
}
