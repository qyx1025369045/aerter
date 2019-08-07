package com.example.daye5.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.daye5.R;
import com.example.daye5.activity.ArticleDetailActivity;
import com.example.daye5.base.BaseMvpFragment;
import com.example.daye5.modles.navgi.adapter.NavgAdapter;
import com.example.daye5.modles.navgi.bean.ArticlesBean;
import com.example.daye5.modles.navgi.bean.NavightBean;
import com.example.daye5.modles.navgi.contrat.NavgerContract;
import com.example.daye5.modles.navgi.presenter.NavgerPresenters;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;


public class NavgFragment extends BaseMvpFragment<NavgerContract.NavgerView, NavgerPresenters<NavgerContract.NavgerView>> implements NavgerContract.NavgerView {
    @BindView(R.id.nvg_rec)
    RecyclerView nvgRec;
    @BindView(R.id.nvg_tab)
    VerticalTabLayout nvgTab;
    private NavgAdapter adapter;
    private LinearLayoutManager layoutManager;
    private int index;
    private boolean needScroll;
    private boolean isClickTab;

    @Override
    protected void createView() {
        mPresenter.gethttp();

        layoutManager = new LinearLayoutManager(getActivity());
        nvgRec.setLayoutManager(layoutManager);
        nvgRec.setHasFixedSize(true);
        adapter = new NavgAdapter(getContext());
        nvgRec.setAdapter(adapter);


    }


    @Override
    protected int createLayout() {
        return R.layout.fragment_navgen_pager;
    }

    @Override
    public void showSuccess(final List<NavightBean> navlist) {

        nvgTab.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return navlist == null ? 0 : navlist.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new ITabView.TabTitle.Builder()
                        .setContent(navlist.get(position).getName())
                        .setTextColor(ContextCompat.getColor(getActivity(),R.color.colorAccent),ContextCompat.getColor(getActivity(),R.color.Grey500))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return -1;
            }
        });
        adapter.initDatas(navlist);
        leftRightLinkage();
    }

    private void leftRightLinkage() {
        nvgRec.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (needScroll && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                    scrollRecyclerView();
                }
                rightLinkageLeft(newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (needScroll){
                    scrollRecyclerView();
                }
            }
        });

        nvgTab.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                isClickTab = true;
                selectTag(position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
    }

    private void selectTag(int position) {
        index = position;
        nvgRec.stopScroll();
        smoothScrollToPosition(position);
    }

    private void smoothScrollToPosition(int position) {
        int firstPosition = layoutManager.findFirstVisibleItemPosition();
        int lastPosition = layoutManager.findLastVisibleItemPosition();
        if (position <= firstPosition) {
            nvgRec.smoothScrollToPosition(position);
        } else if (position <= lastPosition) {
            int top = nvgRec.getChildAt(position - firstPosition).getTop();
            nvgRec.smoothScrollBy(0, top);
        } else {
            nvgRec.smoothScrollToPosition(position);
            needScroll = true;
        }
    }

    private void rightLinkageLeft(int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (isClickTab) {
                isClickTab = false;
                return;
            }
            int firstPosition = layoutManager.findFirstVisibleItemPosition();
            if (index != firstPosition) {
                index = firstPosition;
                setChecked(index);
            }
        }
    }

    private void setChecked(int position) {
        if (isClickTab) {
            isClickTab = false;
        } else {
            if (nvgTab == null) {
                return;
            }
            nvgTab.setTabSelected(index);
        }
        index = position;
    }

    private void scrollRecyclerView() {
        needScroll = false;
        int indexDistance = index - layoutManager.findFirstVisibleItemPosition();
        if (indexDistance >= 0 && indexDistance < nvgRec.getChildCount()) {
            int top = nvgRec.getChildAt(indexDistance).getTop();
            nvgRec.smoothScrollBy(0, top);
        }
    }

    @Override
    public void showError(String error) {
        Log.e("qiao", "showError: " + error);
    }

    @Override
    protected NavgerPresenters<NavgerContract.NavgerView> createPresenter() {
        return new NavgerPresenters<>();
    }

}
