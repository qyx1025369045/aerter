package com.example.daye5.modles.project.presenter;

import com.example.daye5.base.BasePresenter;
import com.example.daye5.modles.home.bean.HomeDatasBean;
import com.example.daye5.modles.project.bean.ProjectListDataBean;
import com.example.daye5.modles.project.bean.ProjectListDatasBean;
import com.example.daye5.modles.project.contrat.ProjectListContract;
import com.example.daye5.modles.project.modle.ProjectListModlers;
import java.util.List;

public class ProjectListPresenter<V extends ProjectListContract.ProjectListView> extends BasePresenter<V> implements ProjectListContract.ProjectListPresenter, ProjectListContract.ProjectListModle.ProCallBack {
    private ProjectListContract.ProjectListModle modle = new ProjectListModlers();

    @Override
    public void showSuccess(ProjectListDataBean navlist, int pag, int cid) {
        mView.showSuccess(navlist);
    }

    @Override
    public void showError(String error) {
        mView.showError(error);
    }

    @Override
    public void gethttp(int pag, int cid) {
        if (mView != null){
            modle.getData(this,pag,cid);
        }
    }
}
