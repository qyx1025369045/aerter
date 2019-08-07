package com.example.daye5.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daye5.R;
import com.example.daye5.activity.ArticleDetailActivity;
import com.example.daye5.base.BaseMvpFragment;
import com.example.daye5.base.BasePresenter;
import com.example.daye5.modles.home.adapter.HomeAdapter;
import com.example.daye5.modles.home.bean.BannerData;
import com.example.daye5.modles.home.bean.HomeDatasBean;
import com.example.daye5.modles.home.contract.MainContract;
import com.example.daye5.modles.home.presenter.HomePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseMvpFragment<MainContract.HomeView, HomePresenter<MainContract.HomeView>> implements MainContract.HomeView {
    @BindView(R.id.recyview)
    RecyclerView recyview;
    @BindView(R.id.sml)
    SmartRefreshLayout sml;
    int pum = 0;
    private HomeAdapter adapter;

    @Override
    protected HomePresenter<MainContract.HomeView> createPresenter() {
        return new HomePresenter<>();
    }

    @Override
    protected void createView() {
        mPresenter.gethttp(pum);

        recyview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyview.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        adapter = new HomeAdapter(getContext());
        recyview.setAdapter(adapter);

        sml.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pum=0;
                createView();
            }
        });
        sml.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pum++;
                createView();
            }
        });

        sml.finishRefresh();
        sml.finishLoadMore();
        sml.setEnableLoadMore(true);
        sml.setEnableRefresh(true);

        adapter.setOnClickListener(new HomeAdapter.OnClickListener() {
            @Override
            public void onclicker(HomeDatasBean bean) {
                Intent intent = new Intent(getActivity(),ArticleDetailActivity.class);
                intent.putExtra("url",bean.getLink());
                startActivity(intent);
            }
        });
    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_home_pager;
    }

    @Override
    public void showArtSuccess(List<HomeDatasBean> list) {
        adapter.initArtData(list);
    }

    @Override
    public void showBannSuccess(List<BannerData> bannerData) {
        adapter.initBannerData(bannerData);
    }

    @Override
    public void showError(String error) {
        Log.e("qiao", "showError: " + error);
    }


}
