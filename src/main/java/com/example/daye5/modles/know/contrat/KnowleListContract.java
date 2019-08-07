package com.example.daye5.modles.know.contrat;


import com.example.daye5.modles.project.bean.ProjectListDataBean;

public interface KnowleListContract {
    interface KnowleListView{
        void showSuccess(ProjectListDataBean knowDataBeans);

        void showError(String error);
    }

    interface KnowListPresenter{
        void gethttp(int page,int cid);
    }

    interface KnowListModle{
        interface KonwListCallBack{
            void showSuccess(ProjectListDataBean knowDataBeans);

            void showError(String error);
        }

        void getData(KonwListCallBack callBack,int page,int cid);
    }
}
