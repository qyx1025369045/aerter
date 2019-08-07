package com.example.daye5.modles.regir.contrat;

import com.example.daye5.modles.login.bean.LoginData;

public interface RegirContract {
    interface RegirView{
        void loginSuccess(LoginData loginData);
        void loginFiler(String error);
    }

    interface RegirPresenter{
        void getRegir(String username, String password,String repassword);
    }

    interface RegirModle{
        interface RegCallBack{
            void regirSuccess(LoginData loginData);
            void regirFiler(String error);
        }
        void getRegir(RegCallBack callBack, String username, String password,String repassword);
    }
}
