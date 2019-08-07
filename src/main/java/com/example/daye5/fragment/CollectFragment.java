package com.example.daye5.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.daye5.R;
import com.example.daye5.base.BaseMvpFragment;
import com.example.daye5.modles.collection.adapter.CollectAdapter;
import com.example.daye5.modles.collection.bean.CollDataBean;
import com.example.daye5.modles.collection.contract.CollectContract;
import com.example.daye5.modles.collection.presenter.CollectPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;

public class CollectFragment extends BaseMvpFragment<CollectContract.CollectView, CollectPresenter<CollectContract.CollectView>> implements CollectContract.CollectView {

    private static int id;

    public static CollectFragment newInstance() {
        return new CollectFragment();
    }

    @BindView(R.id.recyview)
    RecyclerView recyview;
    @BindView(R.id.sml)
    SmartRefreshLayout sml;
    private CollectAdapter adapter;


    public static class BroadReceive extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            id = intent.getIntExtra("id", -1);
        }
    }


    @Override
    protected void createView() {
        mPresenter.gethttp(id);

        recyview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new CollectAdapter(getActivity());
        recyview.setAdapter(adapter);

    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_home_pager;
    }

    @Override
    public void showArtSuccess(List<CollDataBean> list) {
        adapter.initData(list);
    }

    @Override
    public void showError(String error) {
        Log.e("qiao", "showError: " + error);
    }

    @Override
    protected CollectPresenter<CollectContract.CollectView> createPresenter() {
        return new CollectPresenter<>();
    }

}
