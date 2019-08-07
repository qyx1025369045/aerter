package com.example.daye5.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.daye5.R;
import com.example.daye5.base.BaseActivity;
import com.example.daye5.modles.search.adapter.SearchAdapter;
import com.example.daye5.modles.search.bean.SeachBean;
import com.example.daye5.modles.search.contrat.SeachContract;
import com.example.daye5.modles.search.presenter.SeachPresenter;
import com.example.daye5.utils.MainUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity<SeachContract.SeachView,SeachPresenter<SeachContract.SeachView>> implements SeachContract.SeachView {
    @BindView(R.id.seatb)
    Toolbar seatb;
    @BindView(R.id.hot_search_flow_layout)
    TagFlowLayout hotSearchFlowLayout;
    @BindView(R.id.searchrecy)
    RecyclerView searchrecy;
    private List<SeachBean> beans;
    private SearchAdapter adapter;

    @Override
    protected void initView() {
        mPresenter.getseach();

        initToolBar();

        initRecycler();
    }

    private void initRecycler() {
        searchrecy.setLayoutManager(new LinearLayoutManager(this));
        searchrecy.setHasFixedSize(true);
        adapter = new SearchAdapter(this);
        searchrecy.setAdapter(adapter);

    }

    private void initToolBar() {
        setSupportActionBar(seatb);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowCustomEnabled(false);
        }
        seatb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void loginSuccess(final List<SeachBean> seachBeans) {
        beans = seachBeans;
        hotSearchFlowLayout.setAdapter(new TagAdapter<SeachBean>(seachBeans) {
            @Override
            public View getView(final FlowLayout parent, final int position, SeachBean seachBean) {
                TextView titl = View.inflate(SearchActivity.this, R.layout.flow_layout_tv, null).findViewById(R.id.common_title_tv);
                if (seachBean != null) {
                    titl.setText(seachBean.getName());
                }

                titl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SearchActivity.this, CommonActivity.class);
                        intent.putExtra(MainUtils.TYPE_FRAGMENT_KEY, MainUtils.TYPE_SEARCH_RESULT);
                        intent.putExtra(MainUtils.SEARCH_KEY, beans.get(position).getName().trim());
                        startActivity(intent);
                    }
                });
                return titl;
            }
        });


    }

    @Override
    public void loginFiler(String error) {

    }

    @Override
    protected SeachPresenter<SeachContract.SeachView> createPresenter() {
        return new SeachPresenter<>();
    }

    @OnClick(R.id.search_history_clear_all_tv)
    public void onClick() {

    }
}
