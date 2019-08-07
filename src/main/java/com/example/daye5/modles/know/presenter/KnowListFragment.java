package com.example.daye5.modles.know.presenter;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.daye5.R;
import com.example.daye5.activity.ArticleDetailActivity;
import com.example.daye5.base.BaseMvpFragment;
import com.example.daye5.modles.know.contrat.KnowleListContract;
import com.example.daye5.modles.project.bean.ProjectListDataBean;
import com.example.daye5.modles.project.bean.ProjectListDatasBean;
import com.example.daye5.modles.publics.adapter.PublicListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import androidx.annotation.NonNull;
import butterknife.BindView;

public class KnowListFragment extends BaseMvpFragment<KnowleListContract.KnowleListView, KnowListPresenter<KnowleListContract.KnowleListView>> implements KnowleListContract.KnowleListView {
    @BindView(R.id.recyview)
    RecyclerView recyview;
    @BindView(R.id.sml)
    SmartRefreshLayout sml;
    private int page = 1;
    private int id;
    private PublicListAdapter adapter;

    @Override
    protected void createView() {
        id = getArguments().getInt("id");

        mPresenter.gethttp(page, id);

        recyview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyview.setHasFixedSize(true);
        adapter = new PublicListAdapter(getActivity());
        recyview.setAdapter(adapter);

        sml.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=0;
                createView();
            }
        });
        sml.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                createView();
            }
        });

        sml.finishRefresh();
        sml.finishLoadMore();
        sml.setEnableLoadMore(true);
        sml.setEnableRefresh(true);

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
    public void showSuccess(ProjectListDataBean knowDataBeans) {
        adapter.initDate(knowDataBeans.getDatas());
    }

    @Override
    public void showError(String error) {
        Log.e("qiao", "showError: " + error);
    }

    @Override
    protected KnowListPresenter<KnowleListContract.KnowleListView> createPresenter() {
        return new KnowListPresenter<>();
    }

    public void jumpToTheTop() {
        if (recyview != null) {
            recyview.smoothScrollToPosition(0);
        }
    }
}
