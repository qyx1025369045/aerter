package com.example.daye5.modles.publics.contrat;


import com.example.daye5.modles.project.bean.ProjectListDataBean;

public interface PublicListContract {
    interface PublicListView{
        void showSuccess(ProjectListDataBean navlist);

        void showError(String error);
    }

    interface PublicListPresenter{
        void gethttp(int id, int pag);
    }

    interface PublicListModle{
        interface PubliCallBack{
            void showSuccess(ProjectListDataBean navlist,int id, int pag);

            void showError(String error);
        }

        void getData(PubliCallBack callBack,int id, int pag);
    }
}
