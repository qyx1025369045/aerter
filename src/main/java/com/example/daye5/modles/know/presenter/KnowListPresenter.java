package com.example.daye5.modles.know.presenter;

import com.example.daye5.base.BasePresenter;
import com.example.daye5.modles.know.contrat.KnowleListContract;
import com.example.daye5.modles.know.model.KnowListModle;
import com.example.daye5.modles.project.bean.ProjectListDataBean;

public class KnowListPresenter<V extends KnowleListContract.KnowleListView> extends BasePresenter<V> implements KnowleListContract.KnowListPresenter, KnowleListContract.KnowListModle.KonwListCallBack {
    private KnowleListContract.KnowListModle modle = new KnowListModle();


    @Override
    public void showSuccess(ProjectListDataBean knowDataBeans) {
        mView.showSuccess(knowDataBeans);
    }

    @Override
    public void showError(String error) {
        mView.showError(error);
    }

    @Override
    public void gethttp(int page, int cid) {
        if (mView != null) {
            modle.getData(this, page, cid);
        }
    }
}
