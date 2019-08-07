package com.example.daye5.modles.project.contrat;


import com.example.daye5.modles.home.bean.HomeDataBean;
import com.example.daye5.modles.home.bean.HomeDatasBean;
import com.example.daye5.modles.project.bean.ProjectListDataBean;
import com.example.daye5.modles.project.bean.ProjectListDatasBean;

import java.util.List;

public interface ProjectListContract {
    interface ProjectListView{
        void showSuccess(ProjectListDataBean navlist);

        void showError(String error);
    }

    interface ProjectListPresenter{
        void gethttp(int cid,int pag);
    }

    interface ProjectListModle{
        interface ProCallBack{
            void showSuccess(ProjectListDataBean navlist, int pag, int cid);

            void showError(String error);
        }

        void getData(ProCallBack callBack,int pag,int cid);
    }
}
