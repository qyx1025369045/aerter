package com.example.daye5.modles.know.presenter;

import com.example.daye5.base.BasePresenter;
import com.example.daye5.modles.know.bean.KnowDataBean;
import com.example.daye5.modles.know.contrat.KnowleContract;
import com.example.daye5.modles.know.model.KnowModle;

import java.util.List;

public class KnowledgePresenter<V extends KnowleContract.KnowleView> extends BasePresenter<V> implements KnowleContract.KnowPresenter, KnowleContract.KnowModle.CallBack {
    private KnowleContract.KnowModle modle = new KnowModle();

    @Override
    public void gethttp() {
        if (mView != null) {
            modle.getData(this);
        }
    }

    @Override
    public void showSuccess(List<KnowDataBean> knowDataBeans) {
        mView.showSuccess(knowDataBeans);
    }

    @Override
    public void showError(String error) {
        mView.showError(error);
    }


}
