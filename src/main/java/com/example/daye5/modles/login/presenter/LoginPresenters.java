package com.example.daye5.modles.login.presenter;

import com.example.daye5.base.BasePresenter;
import com.example.daye5.modles.login.bean.LoginData;
import com.example.daye5.modles.login.contrat.LoginContract;
import com.example.daye5.modles.login.modle.LoginModles;

public class LoginPresenters<V extends LoginContract.LoginView> extends BasePresenter<V> implements LoginContract.LoginPresenter, LoginContract.LoginModle.LogCallBack {
    private LoginContract.LoginModle modle = new LoginModles();

    @Override
    public void getLogein(String username, String password) {
        if (mView != null) {
            modle.getlogin(this, username, password);
        }
    }

    @Override
    public void loginSuccess(LoginData loginData) {
        mView.loginSuccess(loginData);
    }

    @Override
    public void loginFiler(String error) {
        mView.loginFiler(error);
    }
}
