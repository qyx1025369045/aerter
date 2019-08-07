package com.example.daye5.base;

public abstract class BaseMvpFragment<V,P extends BasePresenter<V>> extends BaseFragment {

    public P mPresenter;

    @Override
    protected void initPresenter() {
        mPresenter = createPresenter();
        if (mPresenter != null){
            mPresenter.attach((V) this);
        }
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mPresenter = null;
    }
}
