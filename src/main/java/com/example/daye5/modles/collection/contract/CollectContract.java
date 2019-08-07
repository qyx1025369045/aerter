package com.example.daye5.modles.collection.contract;

import com.example.daye5.modles.collection.bean.CollDataBean;

import java.util.List;

public interface CollectContract {

    interface CollectView{
        void showArtSuccess(List<CollDataBean> list);
        void showError(String error);
    }

    interface CollectPresenter{
        void gethttp(int pum);
    }

    interface CollectModle{
        interface CollectCallBack{
            void showArtSuccess(List<CollDataBean> list);
            void showError(String error);
        }

        void getData(CollectCallBack callBack, int pum);
    }
}
