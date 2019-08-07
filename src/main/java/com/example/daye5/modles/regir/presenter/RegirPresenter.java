package com.example.daye5.modles.regir.presenter;

import com.example.daye5.base.BasePresenter;
import com.example.daye5.modles.login.bean.LoginData;
import com.example.daye5.modles.regir.contrat.RegirContract;
import com.example.daye5.modles.regir.modle.RegirModles;

public class RegirPresenter<V extends RegirContract.RegirView> extends BasePresenter<V> implements RegirContract.RegirPresenter, RegirContract.RegirModle.RegCallBack {
    private RegirContract.RegirModle modle = new RegirModles();

    @Override
    public void getRegir(String username, String password, String repassword) {
        if (mView != null) {
            modle.getRegir(this, username, password, repassword);
        }
    }

    @Override
    public void regirSuccess(LoginData loginData) {
        mView.loginSuccess(loginData);
    }

    @Override
    public void regirFiler(String error) {
        mView.loginFiler(error);
    }
}
