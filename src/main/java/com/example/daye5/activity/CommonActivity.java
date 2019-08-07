package com.example.daye5.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.example.daye5.R;
import com.example.daye5.base.SimpleActivity;
import com.example.daye5.fragment.CollectFragment;
import com.example.daye5.fragment.SearchFragment;
import com.example.daye5.fragment.SettingFragment;
import com.example.daye5.fragment.UsefulFragment;
import com.example.daye5.utils.MainUtils;

import butterknife.BindView;

public class CommonActivity extends SimpleActivity {

    @BindView(R.id.comtb)
    Toolbar comtb;
    @BindView(R.id.common_frame_layout)
    FrameLayout commonFrameLayout;
    private FragmentManager fm;
    Fragment mFragment;


    @Override
    protected void viewCread() {

    }

    @Override
    protected void initView() {
        initToobar();

        int frage = getIntent().getIntExtra(MainUtils.TYPE_FRAGMENT_KEY, -1);
        Bundle extras = getIntent().getExtras();
        String title = "";

        fm = getSupportFragmentManager();

        switch (frage) {
            case MainUtils.TYPE_USEFULSITES:
                FragmentTransaction tran = fm.beginTransaction();
                UsefulFragment usefulFragment = new UsefulFragment();
                tran.add(R.id.common_frame_layout, usefulFragment);
                tran.show(usefulFragment);
                title = getString(R.string.useful_sites);
                tran.commit();
                break;
            case MainUtils.TYPE_SEARCH_RESULT:
                FragmentTransaction tran1 = fm.beginTransaction();
                SearchFragment searchFragment = new SearchFragment();
                tran1.add(R.id.common_frame_layout,searchFragment);
                String extra = getIntent().getStringExtra(MainUtils.SEARCH_KEY);
                Bundle bundle = new Bundle();
                bundle.putString("east",extra);
                searchFragment.setArguments(bundle);
                tran1.commit();
                break;
            case MainUtils.TYPE_COLLECT:
                mFragment = CollectFragment.newInstance();
                break;
            case MainUtils.TYPE_PROJECT:
                mFragment = SettingFragment.newInstance();
                break;
            default:
                break;
        }
        if (mFragment == null){
            finish();
        }else {
            fm.beginTransaction().replace(R.id.common_frame_layout,mFragment).commit();
        }
    }

    private void initToobar() {
        setSupportActionBar(comtb);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowCustomEnabled(false);
        }
        comtb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_common;
    }

}
