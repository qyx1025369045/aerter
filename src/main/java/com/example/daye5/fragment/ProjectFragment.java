package com.example.daye5.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daye5.R;
import com.example.daye5.base.BaseFragment;
import com.example.daye5.base.BaseMvpFragment;
import com.example.daye5.base.BasePresenter;
import com.example.daye5.modles.project.adapter.VpagerAdapter;
import com.example.daye5.modles.project.bean.ProjectBean;
import com.example.daye5.modles.project.contrat.ProjectContract;
import com.example.daye5.modles.project.presenter.ProjectListFragment;
import com.example.daye5.modles.project.presenter.ProjectPresenters;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProjectFragment extends BaseMvpFragment<ProjectContract.ProjectView, ProjectPresenters<ProjectContract.ProjectView>> implements ProjectContract.ProjectView {

    @BindView(R.id.proj_tab)
    TabLayout projTab;
    @BindView(R.id.proje_vp)
    ViewPager projeVp;
    private List<ProjectBean> list;
    private List<ProjectListFragment> fragmentlist;
    private VpagerAdapter adapter;


    @Override
    protected ProjectPresenters<ProjectContract.ProjectView> createPresenter() {
        return new ProjectPresenters<>();
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
    public void showSuccess(List<ProjectBean> navlist) {
        list = new ArrayList<>();
        list.addAll(navlist);

        fragmentlist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ProjectListFragment projectListFragment = new ProjectListFragment();

            Bundle bundle = new Bundle();
            bundle.putInt("cid", list.get(i).getId());
            projectListFragment.setArguments(bundle);
            fragmentlist.add(projectListFragment);
        }
        adapter = new VpagerAdapter(getChildFragmentManager(), list, fragmentlist);
        projeVp.setAdapter(adapter);
        projTab.setupWithViewPager(projeVp);
    }

    @Override
    public void showError(String error) {
        Log.e("qiao", "showError: " + error);
    }

    @Override
    public void onDestroyView() {
        if (fragmentlist != null) {
            fragmentlist.clear();
            fragmentlist = null;
        }
        if (fragmentlist != null) {
            fragmentlist.clear();
            fragmentlist = null;
        }
        super.onDestroyView();
    }

}
