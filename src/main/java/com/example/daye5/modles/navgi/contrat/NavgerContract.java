package com.example.daye5.modles.navgi.contrat;


import com.example.daye5.modles.know.bean.KnowDataBean;
import com.example.daye5.modles.navgi.bean.NavightBean;

import java.util.List;

public interface NavgerContract {
    interface NavgerView{
        void showSuccess(List<NavightBean> navlist);

        void showError(String error);
    }

    interface NavgerPresenter{
        void gethttp();
    }

    interface NavgerModle{
        interface CallBack{
            void showSuccess(List<NavightBean> navlist);

            void showError(String error);
        }

        void getData(CallBack callBack);
    }
}
