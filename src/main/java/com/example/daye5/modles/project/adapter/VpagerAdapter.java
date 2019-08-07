package com.example.daye5.modles.project.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;

import com.example.daye5.modles.project.bean.ProjectBean;
import com.example.daye5.modles.project.presenter.ProjectListFragment;

import java.util.ArrayList;
import java.util.List;

public class VpagerAdapter extends FragmentStatePagerAdapter {
    private List<ProjectBean> list;
    private List<ProjectListFragment> fragmentlist;

    public VpagerAdapter(FragmentManager fm,List<ProjectBean> list,List<ProjectListFragment> fragmentlist) {
        super(fm);
        this.list = list;
        this.fragmentlist = fragmentlist;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentlist.get(i);
    }

    @Override
    public int getCount() {
        return fragmentlist.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getName();
    }
}
