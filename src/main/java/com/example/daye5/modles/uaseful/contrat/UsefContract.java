package com.example.daye5.modles.uaseful.contrat;


import com.example.daye5.modles.uaseful.bean.UsefulBean;

import java.util.List;

public interface UsefContract {
    interface UsefView{
        void showSuccess(List<UsefulBean> useful);

        void showError(String error);
    }

    interface UsefPresenter{
        void gethttp();
    }

    interface UsefModle{
        interface UsefCallBack{
            void showSuccess(List<UsefulBean> useful);

            void showError(String error);
        }

        void getData(UsefCallBack callBack);
    }
}
