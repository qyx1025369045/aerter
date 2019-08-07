package com.example.daye5.modles.project.contrat;


import com.example.daye5.modles.navgi.bean.NavightBean;
import com.example.daye5.modles.project.bean.ProjectBean;

import java.util.List;

public interface ProjectContract {
    interface ProjectView{
        void showSuccess(List<ProjectBean> navlist);

        void showError(String error);
    }

    interface ProjectPresenter{
        void gethttp();
    }

    interface ProjectModle{
        interface CallBack{
            void showSuccess(List<ProjectBean> navlist);

            void showError(String error);
        }

        void getData(CallBack callBack);
    }
}
