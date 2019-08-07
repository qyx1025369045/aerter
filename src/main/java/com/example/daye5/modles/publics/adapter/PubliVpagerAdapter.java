package com.example.daye5.modles.publics.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.daye5.modles.project.bean.ProjectBean;
import com.example.daye5.modles.project.presenter.ProjectListFragment;
import com.example.daye5.modles.publics.bean.PublicBean;
import com.example.daye5.modles.publics.presenters.PublicListFragment;

import java.util.List;

public class PubliVpagerAdapter extends FragmentStatePagerAdapter {
    private List<PublicBean> list;
    private List<PublicListFragment> fragmentlist;

    public PubliVpagerAdapter(FragmentManager fm, List<PublicBean> list, List<PublicListFragment> fragmentlist) {
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
