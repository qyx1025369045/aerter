package com.example.daye5.modles.home.contract;

import com.example.daye5.modles.home.bean.BannerData;
import com.example.daye5.modles.home.bean.HomeDatasBean;

import java.util.List;

public interface MainContract {

    interface HomeView{
        void showArtSuccess(List<HomeDatasBean> list);
        void showBannSuccess(List<BannerData> bannerData);

        void showError(String error);
    }

    interface HomePresenter{
        void gethttp(int pum);
    }

    interface HomeModle{
        interface CallBack{
            void showArtSuccess(List<HomeDatasBean> list);
            void showBannSuccess(List<BannerData> bannerData);

            void showError(String error);
        }

        void getData(CallBack callBack,int pum);
    }
}
