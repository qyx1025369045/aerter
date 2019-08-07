package com.example.daye5.modles.search.contrat;

import com.example.daye5.modles.home.bean.HomeDatasBean;

import java.util.List;

public interface SeachFragContract {
    interface SeachFragView{
        void loginSuccess(List<HomeDatasBean> seachBeans);
        void loginFiler(String error);
    }

    interface SeachFragPresenter{
        void getseach(int page,String k);
    }

    interface SeachFragModle{
        interface SeachFragCallBack{
            void regirSuccess(List<HomeDatasBean> seachBeans);
            void regirFiler(String error);
        }
        void getRegir(SeachFragCallBack callBack,int page,String k);
    }
}
