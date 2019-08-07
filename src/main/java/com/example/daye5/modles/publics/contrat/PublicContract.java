package com.example.daye5.modles.publics.contrat;


import com.example.daye5.modles.publics.bean.PublicBean;

import java.util.List;

public interface PublicContract {
    interface PublicView{
        void showSuccess(List<PublicBean> navlist);

        void showError(String error);
    }

    interface PublicPresenter{
        void gethttp();
    }

    interface PublicModle{
        interface PublicCallBack{
            void showSuccess(List<PublicBean> navlist);

            void showError(String error);
        }

        void getData(PublicCallBack callBack);
    }
}
