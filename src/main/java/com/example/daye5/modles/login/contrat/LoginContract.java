package com.example.daye5.modles.login.contrat;

import com.example.daye5.modles.login.bean.LoginData;

public interface LoginContract {
    interface LoginView{
        void loginSuccess(LoginData loginData);
        void loginFiler(String error);
    }

    interface LoginPresenter{
        void getLogein(String username,String password);
    }

    interface LoginModle{
        interface LogCallBack{
            void loginSuccess(LoginData loginData);
            void loginFiler(String error);
        }
        void getlogin(LogCallBack callBack,String username,String password);
    }
}
