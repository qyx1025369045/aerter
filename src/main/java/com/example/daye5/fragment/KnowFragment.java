package com.example.daye5.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.daye5.R;
import com.example.daye5.base.BaseMvpFragment;
import com.example.daye5.modles.know.adapter.KnowTreeAdapter;
import com.example.daye5.modles.know.bean.KnowDataBean;
import com.example.daye5.modles.know.contrat.KnowleContract;
import com.example.daye5.modles.know.presenter.KnowledgePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;

public class KnowFragment extends BaseMvpFragment<KnowleContract.KnowleView,KnowledgePresenter<KnowleContract.KnowleView>> implements KnowleContract.KnowleView {
    @BindView(R.id.recyview)
    RecyclerView recyview;
    @BindView(R.id.sml)
    SmartRefreshLayout sml;
    private KnowTreeAdapter adapter;

    @Override
    protected KnowledgePresenter<KnowleContract.KnowleView> createPresenter() {
        return new KnowledgePresenter<>();
    }

    @Override
    protected void createView() {
        mPresenter.gethttp();

        recyview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyview.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        adapter = new KnowTreeAdapter(getActivity());
        recyview.setAdapter(adapter);

    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_home_pager;
    }

    @Override
    public void showSuccess(List<KnowDataBean> knowDataBeans) {
        adapter.initKnowData(knowDataBeans);
    }

    @Override
    public void showError(String error) {
        Log.e("qiao", "showError: " + error);
    }

}
