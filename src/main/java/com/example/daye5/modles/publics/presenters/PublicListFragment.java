package com.example.daye5.modles.publics.presenters;

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
import com.example.daye5.modles.project.bean.ProjectListDataBean;
import com.example.daye5.modles.project.bean.ProjectListDatasBean;
import com.example.daye5.modles.publics.adapter.PublicListAdapter;
import com.example.daye5.modles.publics.contrat.PublicListContract;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PublicListFragment extends BaseMvpFragment<PublicListContract.PublicListView, PublicListPresenter<PublicListContract.PublicListView>> implements PublicListContract.PublicListView {
    @BindView(R.id.recyview)
    RecyclerView recyview;
    @BindView(R.id.sml)
    SmartRefreshLayout sml;
    private int page = 1;
    private int id;
    private PublicListAdapter adapter;

    @Override
    protected PublicListPresenter<PublicListContract.PublicListView> createPresenter() {
        return new PublicListPresenter<>();
    }

    @Override
    protected void createView() {
        id = getArguments().getInt("id");
        mPresenter.gethttp(id, page);

        recyview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        adapter = new PublicListAdapter(getActivity());
        recyview.setAdapter(adapter);

        sml.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                createView();
            }
        });

        sml.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page++;
                createView();
            }
        });
        sml.setEnableLoadMore(true);
        sml.setEnableRefresh(true);
        sml.finishLoadMore();
        sml.finishRefresh();

        adapter.setOnClickListener(new PublicListAdapter.OnClickListener() {
            @Override
            public void onclicker(ProjectListDatasBean bean) {
                Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
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
    public void showSuccess(ProjectListDataBean navlist) {
        List<ProjectListDatasBean> datas = navlist.getDatas();
        adapter.initDate(datas);
    }

    @Override
    public void showError(String error) {
        Log.e("qiao", "showError: " + error);
    }

}
