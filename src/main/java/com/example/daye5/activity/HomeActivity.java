package com.example.daye5.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daye5.R;
import com.example.daye5.base.SimpleActivity;
import com.example.daye5.fragment.HomeFragment;
import com.example.daye5.fragment.KnowFragment;
import com.example.daye5.fragment.NavgFragment;
import com.example.daye5.fragment.ProjectFragment;
import com.example.daye5.fragment.PublicFragment;
import com.example.daye5.utils.Constants;
import com.example.daye5.utils.HttpUtils;
import com.example.daye5.utils.MainUtils;

import java.util.List;

import butterknife.BindView;

public class HomeActivity extends SimpleActivity {

    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.nv)
    NavigationView nv;
    @BindView(R.id.dl)
    DrawerLayout dl;
    @BindView(R.id.fabt)
    FloatingActionButton fabt;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;

    private HomeFragment homeFragment;
    private KnowFragment knowFragment;
    private NavgFragment navgFragment;
    private PublicFragment publicFragment;
    private ProjectFragment projectFragment;
    private ActionBarDrawerToggle toggle;
    private int mFragmentIndex = 0;
    private int mLastFgIndx = -1;
    public static final int TYPE_HOME_PAGER = 0;
    public static final int TYPE_KNOWLEDGE = 1;
    public static final int TYPE_NAVIGATION = 2;
    public static final int TYPE_WX_ARTICLE = 3;
    public static final int TYPE_PROJECT = 4;
    private TextView name;
    private SharedPreferences sp;
    private String usname;
    private String ciod;

    @Override
    protected void viewCread() {

    }

    @Override
    protected void initView() {
        initTooBlar();

        showFragment(mFragmentIndex);

        initNavigationView();

        initBottomNavigationView();
    }

    private void initBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab_main_pager:
                        showFragment(TYPE_HOME_PAGER);
                        break;
                    case R.id.tab_knowledge_hierarchy:
                        showFragment(TYPE_KNOWLEDGE);
                        break;
                    case R.id.tab_navigation:
                        showFragment(TYPE_NAVIGATION);
                        break;
                    case R.id.tab_wx_article:
                        showFragment(TYPE_WX_ARTICLE);
                        break;
                    case R.id.tab_project:
                        showFragment(TYPE_PROJECT);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void initNavigationView() {
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nv_collection:
                        if (ciod.isEmpty()){
                            HttpUtils.startFragmentInCommonActivity(HomeActivity.this, Constants.TYPE_COLLECT);
                        }else {
                            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        }
                        break;
                    case R.id.nv_todo:
                        if (ciod.isEmpty()){
                            HttpUtils.startFragmentInCommonActivity(HomeActivity.this, Constants.TYPE_SETTING);
                        }else {
                            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        }
                        break;
                    case R.id.nv_night:
                        setarnight();
                        break;
                    case R.id.nv_setup:
                        HttpUtils.startFragmentInCommonActivity(HomeActivity.this, Constants.TYPE_PROJECT);
                        break;
                    case R.id.nv_about:
                        break;
                    case R.id.nv_logout:

                        break;
                }
                return false;
            }
        });

        name = nv.getHeaderView(0).findViewById(R.id.nv_name);
        sp = getSharedPreferences("login", Context.MODE_PRIVATE);

        usname = sp.getString("usname", "");
        ciod = sp.getString("ciod", "");
        String cookie = sp.getString("cookie", null);
        Toast.makeText(this,cookie,Toast.LENGTH_LONG).show();
        if (ciod.isEmpty()) {

            if (usname.isEmpty()){
                name.getText();

            }else {

                name.setText(usname);
            }
        } else {
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                }
            });
        }
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });
    }

    private void setarnight() {
        //获取所有fragment
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        //获取fragment长度
        int size = getSupportFragmentManager().getFragments().size();

        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        //循环fragment
        for (int i = 0; i < size; i++) {
            //删除所有fragment
            fragmentTransaction1.remove(fragments.get(i));
        }
        //执行
        fragmentTransaction1.commit();

        //UIModeUtil.changeModeUI(HomeActivity.this);
        int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (mode == Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (mode == Configuration.UI_MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        recreate();
    }

    private void showFragment(int index) {
        mFragmentIndex = index;
        FragmentTransaction tran = getSupportFragmentManager().beginTransaction();
        hideFragments(tran);
        mLastFgIndx = index;
        switch (index) {
            case TYPE_HOME_PAGER:
                tb.setTitle("主页");
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    tran.add(R.id.fragment_group, homeFragment);
                }
                tran.show(homeFragment);
                break;
            case TYPE_KNOWLEDGE:
                tb.setTitle("知识体系");
                if (knowFragment == null) {
                    knowFragment = new KnowFragment();
                    tran.add(R.id.fragment_group, knowFragment);
                }
                tran.show(knowFragment);
                break;
            case TYPE_NAVIGATION:
                tb.setTitle("导航");
                if (navgFragment == null) {
                    navgFragment = new NavgFragment();
                    tran.add(R.id.fragment_group, navgFragment);
                }
                tran.show(navgFragment);
                break;
            case TYPE_WX_ARTICLE:
                tb.setTitle("公众号");
                if (publicFragment == null) {
                    publicFragment = new PublicFragment();
                    tran.add(R.id.fragment_group, publicFragment);
                }
                tran.show(publicFragment);
                break;
            case TYPE_PROJECT:
                tb.setTitle("项目");
                if (projectFragment == null) {
                    projectFragment = new ProjectFragment();
                    tran.add(R.id.fragment_group, projectFragment);
                }
                tran.show(projectFragment);
                break;
            default:
                break;
        }
        tran.commit();
    }

    private void hideFragments(FragmentTransaction tran) {
        switch (mLastFgIndx) {
            case TYPE_HOME_PAGER:
                if (homeFragment != null) {
                    tran.hide(homeFragment);
                }
                break;
            case TYPE_KNOWLEDGE:
                if (knowFragment != null) {
                    tran.hide(knowFragment);
                }
                break;
            case TYPE_NAVIGATION:
                if (navgFragment != null) {
                    tran.hide(navgFragment);
                }
                break;
            case TYPE_WX_ARTICLE:
                if (publicFragment != null) {
                    tran.hide(publicFragment);
                }
                break;
            case TYPE_PROJECT:
                if (projectFragment != null) {
                    tran.hide(projectFragment);
                }
                break;
            default:
                break;
        }
    }

    private void initTooBlar() {
        tb.setTitle("主页");
        tb.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(tb);
        //返回键可用

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowCustomEnabled(true);
        }

        toggle = new ActionBarDrawerToggle(this, dl, tb, R.string.app_name, R.string.app_name);
        dl.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_home;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_usage:
                intent = new Intent(HomeActivity.this, CommonActivity.class);
                intent.putExtra(MainUtils.TYPE_FRAGMENT_KEY, MainUtils.TYPE_USEFULSITES);
                startActivity(intent);
                break;
            case R.id.action_search:
                intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
