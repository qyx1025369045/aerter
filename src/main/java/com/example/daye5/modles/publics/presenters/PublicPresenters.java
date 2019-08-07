package com.example.daye5.modles.publics.presenters;

import com.example.daye5.base.BasePresenter;
import com.example.daye5.modles.publics.bean.PublicBean;
import com.example.daye5.modles.publics.contrat.PublicContract;
import com.example.daye5.modles.publics.modle.PublicModlers;

import java.util.List;

public class PublicPresenters<V extends PublicContract.PublicView> extends BasePresenter<V> implements PublicContract.PublicPresenter, PublicContract.PublicModle.PublicCallBack {
    private PublicContract.PublicModle modle = new PublicModlers();

    @Override
    public void gethttp() {
        if (mView != null) {
            modle.getData(this);
        }
    }

    @Override
    public void showSuccess(List<PublicBean> navlist) {
        mView.showSuccess(navlist);
    }

    @Override
    public void showError(String error) {
        mView.showError(error);
    }
}
