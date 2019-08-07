package com.example.daye5.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebBackForwardList;
import android.webkit.WebView;

import com.example.daye5.R;
import com.example.daye5.base.SimpleActivity;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.NestedScrollAgentWebView;
import com.just.agentweb.WebChromeClient;

import butterknife.BindView;

public class ArticleDetailActivity extends SimpleActivity {


    @BindView(R.id.art_tb)
    Toolbar artTb;
    @BindView(R.id.content_layout)
    CoordinatorLayout contentLayout;
    private String url;
    private AgentWeb agentWeb;
    private MenuItem collect;
    private int id;
    private String title;
    private boolean collected;
    private boolean img;
    private int items;
    private String evet;
    private WebBackForwardList forwardList;
    private NestedScrollAgentWebView webView;

    @Override
    protected void onResume() {
        agentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        agentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        agentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    @Override
    protected void viewCread() {

    }

    @Override
    protected void initView() {

        url = getIntent().getStringExtra("url");

        initToolbar();
        initEventent();
    }

    private void initEventent() {
        WebChromeClient webChromeClient = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                artTb.setTitle(Html.fromHtml(title));
            }
        };

        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(-1, -1);
        layoutParams.setBehavior(new AppBarLayout.ScrollingViewBehavior());
        webView = new NestedScrollAgentWebView(this);
        forwardList = webView.copyBackForwardList();
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(contentLayout, layoutParams)
                .useDefaultIndicator()
                .setWebView(webView)
                .setWebChromeClient(webChromeClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .createAgentWeb()
                .ready()
                .go(url);
    }

    private void initToolbar() {
        getBundleData();
        artTb.setTitle(title);
        setSupportActionBar(artTb);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowCustomEnabled(false);
        }

        artTb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_article_detail;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_acticle_detail, menu);
        collect = menu.findItem(R.id.item_collect);
        collect.setVisible(img);
        collect.setIcon(collected ? R.drawable.ic_like_not_white : R.drawable.ic_like_not_white);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_share:
                break;
            case R.id.item_collect:
                break;
            case R.id.item_system_browser:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getBundleData() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        id = bundle.getInt("id");
        title = bundle.getString("title");
        collected = bundle.getBoolean("collected");
        img = bundle.getBoolean("img");
        items = bundle.getInt("items");
        evet = bundle.getString("evet");
    }

}
