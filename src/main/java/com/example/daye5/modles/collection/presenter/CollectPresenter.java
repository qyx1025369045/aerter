package com.example.daye5.modles.collection.presenter;

import com.example.daye5.base.BasePresenter;
import com.example.daye5.modles.collection.bean.CollDataBean;
import com.example.daye5.modles.collection.contract.CollectContract;
import com.example.daye5.modles.collection.modle.CollectModle;

import java.util.List;

public class CollectPresenter<V extends CollectContract.CollectView> extends BasePresenter<V> implements CollectContract.CollectPresenter, CollectContract.CollectModle.CollectCallBack {
    private CollectContract.CollectModle modle = new CollectModle();

    @Override
    public void gethttp(int pum) {
        if (mView != null) {
            modle.getData(this, pum);
        }
    }

    @Override
    public void showArtSuccess(List<CollDataBean> list) {
        mView.showArtSuccess(list);
    }

    @Override
    public void showError(String error) {
        mView.showError(error);
    }
}
