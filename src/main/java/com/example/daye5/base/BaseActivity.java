package com.example.daye5.base;

public abstract class BaseActivity<V,P extends BasePresenter<V>> extends SimpleActivity {

    public P mPresenter;

    @Override
    protected void viewCread() {
        mPresenter = createPresenter();
        if (mPresenter != null){
            mPresenter.attach((V) this);
        }
    }

    public void showProgressBar(){}

    public void hideProgressBar(){}

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.detachView();
        }
    }
}
