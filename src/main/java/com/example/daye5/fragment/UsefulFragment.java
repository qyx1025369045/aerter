package com.example.daye5.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.daye5.R;
import com.example.daye5.activity.ArticleDetailActivity;
import com.example.daye5.base.BaseMvpFragment;
import com.example.daye5.modles.uaseful.bean.UsefulBean;
import com.example.daye5.modles.uaseful.contrat.UsefContract;
import com.example.daye5.modles.uaseful.presenter.UseafulPresenter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class UsefulFragment extends BaseMvpFragment<UsefContract.UsefView, UseafulPresenter<UsefContract.UsefView>> implements UsefContract.UsefView {

    @BindView(R.id.usetag)
    TagFlowLayout usetag;
    private ArrayList<UsefulBean> list;


    @Override
    protected UseafulPresenter<UsefContract.UsefView> createPresenter() {
        return new UseafulPresenter<>();
    }

    @Override
    protected void createView() {
        mPresenter.gethttp();
        list = new ArrayList<>();
    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_useful;
    }

    @Override
    public void showSuccess(final List<UsefulBean> useful) {
        list.addAll(useful);
        usetag.setAdapter(new TagAdapter<UsefulBean>(useful) {
            @Override
            public View getView(final FlowLayout parent, final int position, final UsefulBean usefulBean) {
                TextView usetitle = View.inflate(getContext(), R.layout.flow_layout_tv, null).findViewById(R.id.common_title_tv);
                if (usefulBean != null) {
                    usetitle.setText(usefulBean.getName());
                }
                usetitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
                        intent.putExtra("url",usefulBean.getLink());
                        startActivity(intent);
                    }
                });
                return usetitle;
            }
        });

    }

    @Override
    public void showError(String error) {

    }

}
