package com.example.daye5.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daye5.R;
import com.example.daye5.base.BaseMvpFragment;
import com.example.daye5.base.BasePresenter;
import com.example.daye5.modles.publics.adapter.PubliVpagerAdapter;
import com.example.daye5.modles.publics.bean.PublicBean;
import com.example.daye5.modles.publics.contrat.PublicContract;
import com.example.daye5.modles.publics.presenters.PublicListFragment;
import com.example.daye5.modles.publics.presenters.PublicPresenters;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PublicFragment extends BaseMvpFragment<PublicContract.PublicView,PublicPresenters<PublicContract.PublicView>> implements PublicContract.PublicView {
    @BindView(R.id.proj_tab)
    TabLayout projTab;
    @BindView(R.id.proje_vp)
    ViewPager projeVp;
    private List<PublicBean> list;
    private List<PublicListFragment> fragments;
    private PubliVpagerAdapter adapter;


    @Override
    protected PublicPresenters<PublicContract.PublicView> createPresenter() {
        return new PublicPresenters<>();
    }

    @Override
    protected void createView() {
        mPresenter.gethttp();
    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_project;
    }

    @Override
    public void showSuccess(List<PublicBean> navlist) {
        list = new ArrayList<>();
        list.addAll(navlist);

        fragments = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            PublicListFragment publicListFragment = new PublicListFragment();

            Bundle bundle = new Bundle();
            bundle.putInt("id",list.get(i).getId());
            publicListFragment.setArguments(bundle);
            fragments.add(publicListFragment);
        }
        adapter = new PubliVpagerAdapter(getChildFragmentManager(), list, fragments);
        projeVp.setAdapter(adapter);
        projTab.setupWithViewPager(projeVp);
    }

    @Override
    public void showError(String error) {
        Log.e("qiao", "showError: " + error);
    }

    @Override
    public void onDestroyView() {
        if (fragments != null) {
            fragments.clear();
            fragments = null;
        }
        if (fragments != null) {
            fragments.clear();
            fragments = null;
        }
        super.onDestroyView();
    }
}
