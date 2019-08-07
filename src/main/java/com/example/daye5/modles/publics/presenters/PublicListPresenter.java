package com.example.daye5.modles.publics.presenters;

import com.example.daye5.base.BasePresenter;
import com.example.daye5.modles.project.bean.ProjectListDataBean;
import com.example.daye5.modles.publics.contrat.PublicListContract;
import com.example.daye5.modles.publics.modle.PublicListModlers;

public class PublicListPresenter<V extends PublicListContract.PublicListView> extends BasePresenter<V> implements PublicListContract.PublicListPresenter,PublicListContract.PublicListModle.PubliCallBack {
    private PublicListContract.PublicListModle modle = new PublicListModlers();


    @Override
    public void showSuccess(ProjectListDataBean navlist, int id, int pag) {
        mView.showSuccess(navlist);
    }

    @Override
    public void showError(String error) {
        mView.showError(error);
    }

    @Override
    public void gethttp(int id, int pag) {
        if (mView != null){
            modle.getData(this,id,pag);
        }
    }
}
