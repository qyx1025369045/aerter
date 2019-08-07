package com.example.daye5.modles.project.presenter;

import com.example.daye5.base.BasePresenter;
import com.example.daye5.modles.project.bean.ProjectBean;
import com.example.daye5.modles.project.contrat.ProjectContract;
import com.example.daye5.modles.project.modle.ProjectModles;

import java.util.List;

public class ProjectPresenters<V extends ProjectContract.ProjectView> extends BasePresenter<V> implements ProjectContract.ProjectPresenter, ProjectContract.ProjectModle.CallBack {
    private ProjectContract.ProjectModle modle = new ProjectModles();

    @Override
    public void showSuccess(List<ProjectBean> navlist) {
        mView.showSuccess(navlist);
    }

    @Override
    public void showError(String error) {
        mView.showError(error);
    }

    @Override
    public void gethttp() {
        if (mView != null) {
            modle.getData(this);
        }
    }
}
