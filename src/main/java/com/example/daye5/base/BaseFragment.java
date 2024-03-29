package com.example.daye5.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(createLayout(), null);

        ButterKnife.bind(this,view);
        initPresenter();
        createView();
        return view;
    }

    protected abstract void initPresenter();

    protected abstract void createView();

    protected abstract int createLayout();
}
