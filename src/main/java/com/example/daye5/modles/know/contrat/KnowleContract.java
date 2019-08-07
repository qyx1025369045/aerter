package com.example.daye5.modles.know.contrat;


import com.example.daye5.modles.know.bean.KnowDataBean;

import java.util.List;

public interface KnowleContract {
    interface KnowleView{
        void showSuccess(List<KnowDataBean> knowDataBeans);

        void showError(String error);
    }

    interface KnowPresenter{
        void gethttp();
    }

    interface KnowModle{
        interface CallBack{
            void showSuccess(List<KnowDataBean> knowDataBeans);

            void showError(String error);
        }

        void getData(CallBack callBack);
    }
}
