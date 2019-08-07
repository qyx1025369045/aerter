package com.example.daye5.modles.project.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daye5.R;
import com.example.daye5.activity.ArticleDetailActivity;
import com.example.daye5.base.BaseMvpFragment;
import com.example.daye5.base.BasePresenter;
import com.example.daye5.modles.home.bean.HomeDatasBean;
import com.example.daye5.modles.project.adapter.ProjectListAdapter;
import com.example.daye5.modles.project.bean.ProjectListDataBean;
import com.example.daye5.modles.project.bean.ProjectListDatasBean;
import com.example.daye5.modles.project.contrat.ProjectListContract;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProjectListFragment extends BaseMvpFragment<ProjectListContract.ProjectListView, ProjectListPresenter<ProjectListContract.ProjectListView>> implements ProjectListContract.ProjectListView {
    @BindView(R.id.recyview)
    RecyclerView recyview;
    @BindView(R.id.sml)
    SmartRefreshLayout sml;
    private int cid;
    private int pag = 1;
    private ProjectListAdapter adapter;

    @Override
    protected void createView() {
        cid = getArguments().getInt("cid");

        mPresenter.gethttp(pag, cid);

        recyview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyview.setHasFixedSize(true);
        adapter = new ProjectListAdapter(getActivity());
        recyview.setAdapter(adapter);

        sml.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pag = 1;
                createView();
            }
        });
        sml.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pag++;
                createView();
            }
        });
        sml.setEnableRefresh(true);
        sml.setEnableLoadMore(true);
        sml.finishRefresh();
        sml.finishLoadMore();

        adapter.setOnClickListener(new ProjectListAdapter.OnClickListener() {
            @Override
            public void onclickes(ProjectListDatasBean bean) {
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
        adapter.initDatar(navlist.getDatas());
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected ProjectListPresenter<ProjectListContract.ProjectListView> createPresenter() {
        return new ProjectListPresenter<>();
    }
}
