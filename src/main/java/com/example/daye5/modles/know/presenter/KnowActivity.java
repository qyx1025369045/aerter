package com.example.daye5.modles.know.presenter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.daye5.R;
import com.example.daye5.base.SimpleActivity;
import com.example.daye5.modles.know.adapter.KnowVpagerAdapter;
import com.example.daye5.modles.know.bean.KnowDataBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class KnowActivity extends SimpleActivity {

    @BindView(R.id.knowtb)
    Toolbar knowtb;
    @BindView(R.id.knowledge_tablayout)
    TabLayout knowledgeTablayout;
    @BindView(R.id.knowledge_viewpager)
    ViewPager knowledgeViewpager;
    @BindView(R.id.knowledge_floating_action_btn)
    FloatingActionButton knowledgeFloatingActionBtn;
    private ArrayList<KnowDataBean> list;
    private ArrayList<KnowListFragment> fragments;
    private KnowVpagerAdapter adapter;
    private List<KnowDataBean> children;
    private KnowListFragment knowListFragment;


    @Override
    protected void viewCread() {

    }

    @Override
    protected void initView() {
        KnowDataBean know = (KnowDataBean) getIntent().getSerializableExtra("know");
        if (know == null || know.getName() == null) {
            return;
        }

        children = know.getChildren();
        if (children == null) {
            return;
        }

        knowtb.setTitle(know.getName());
        setSupportActionBar(knowtb);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowCustomEnabled(false);
        }
        initPager();

    }

    private void initPager() {
        list = new ArrayList<>();
        list.addAll(children);

        fragments = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            KnowListFragment fragment = new KnowListFragment();

            Bundle bundle = new Bundle();
            bundle.putInt("id", list.get(i).getId());
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        adapter = new KnowVpagerAdapter(getSupportFragmentManager(), list, fragments);
        knowledgeViewpager.setAdapter(adapter);
        knowledgeTablayout.setupWithViewPager(knowledgeViewpager);
        knowledgeTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                knowledgeViewpager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_know;
    }

    @OnClick(R.id.knowledge_floating_action_btn)
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.knowledge_floating_action_btn:
               jumpToTheTop();
               break;
       }
    }

    private void jumpToTheTop() {
        knowListFragment = fragments.get(knowledgeViewpager.getCurrentItem());
        if (knowListFragment != null){
            knowListFragment.jumpToTheTop();
        }

    }

    @Override
    public void onDestroy() {
        if (fragments != null) {
            fragments.clear();
            fragments = null;
        }
        if (list != null) {
            list.clear();
            list = null;
        }
        super.onDestroy();
    }
}
