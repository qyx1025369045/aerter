package com.example.daye5.modles.search.contrat;

import com.example.daye5.modles.search.bean.SeachBean;

import java.util.List;

public interface SeachContract {
    interface SeachView{
        void loginSuccess(List<SeachBean> seachBeans);
        void loginFiler(String error);
    }

    interface SeachPresenter{
        void getseach();
    }

    interface SeachModle{
        interface SeachCallBack{
            void regirSuccess(List<SeachBean> seachBeans);
            void regirFiler(String error);
        }
        void getRegir(SeachCallBack callBack);
    }
}
