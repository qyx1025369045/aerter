package com.example.daye5.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.daye5.R;
import com.example.daye5.base.BaseMvpFragment;
import com.example.daye5.modles.home.bean.HomeDatasBean;
import com.example.daye5.modles.search.adapter.SearchFragAdapter;
import com.example.daye5.modles.search.contrat.SeachFragContract;
import com.example.daye5.modles.search.presenter.SeachFragPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;

public class SearchFragment extends BaseMvpFragment<SeachFragContract.SeachFragView, SeachFragPresenter<SeachFragContract.SeachFragView>> implements SeachFragContract.SeachFragView {
    @BindView(R.id.recyview)
    RecyclerView recyview;
    @BindView(R.id.sml)
    SmartRefreshLayout sml;
    private int page = 0;
    private String sear;
    private SearchFragAdapter adapter;

    @Override
    protected SeachFragPresenter<SeachFragContract.SeachFragView> createPresenter() {
        return new SeachFragPresenter<>();
    }

    @Override
    protected void createView() {
        sear = getArguments().getString("east");
        if (sear == null){
            return;
        }


        recyview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyview.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyview.setHasFixedSize(true);
        adapter = new SearchFragAdapter(getActivity());
        recyview.setAdapter(adapter);

        mPresenter.getseach(page, sear);
    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_home_pager;
    }

    @Override
    public void loginSuccess(List<HomeDatasBean> seachBeans) {
        adapter.initData(seachBeans);
    }

    @Override
    public void loginFiler(String error) {
        Log.e("qiao", "loginFiler: " + error);
    }

}
