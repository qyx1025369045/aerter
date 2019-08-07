package com.example.daye5.modles.know.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.daye5.modles.know.bean.KnowDataBean;
import com.example.daye5.modles.know.presenter.KnowListFragment;

import java.util.List;

public class KnowVpagerAdapter extends FragmentStatePagerAdapter {
    private List<KnowDataBean> list;
    private List<KnowListFragment> fragmentlist;

    public KnowVpagerAdapter(FragmentManager fm, List<KnowDataBean> list, List<KnowListFragment> fragmentlist) {
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
